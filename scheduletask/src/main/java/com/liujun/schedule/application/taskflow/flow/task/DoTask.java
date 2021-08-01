package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.ddd.common.infrastructure.constant.ErrorCodeEnum;
import com.ddd.common.infrastructure.exception.BusiException;
import com.liujun.schedule.application.taskflow.constant.RunTaskConstant;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.application.taskflow.container.TaskContainerMap;
import com.liujun.schedule.application.taskflow.flow.runtask.DoTaskFlow;
import com.liujun.schedule.application.taskflow.graph.ContainData;
import com.liujun.schedule.application.taskflow.retry.RetryFunction;
import com.liujun.schedule.application.taskflow.retry.RetryRsp;
import com.liujun.schedule.domain.task.entity.DcTaskInfoDO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 执行任务信息
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service("doTask")
public class DoTask implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(DoTask.class);

    /**
     * 默认不重试
     */
    private static final String DEFAULT_RUN = "0";

    /**
     * 批次状态状态服务操作
     */
    @Autowired
    private DoTaskFlow doTask;

    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(ThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        if (null != finishFlag && finishFlag) {
            logger.info("thread task job flow update  status finsi  start ...");

            Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
            Long taskId = context.getObject(ThreadTaskEnum.INPUT_TASK_ID.name());
            Long runFlag = context.getObject(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name());

            boolean rsp = doTaskAndRetry(batchId, taskId, runFlag);
            context.put(ThreadTaskEnum.PROC_TASK_RSP_FLAG.name(), rsp);

            logger.info("thread task job flow update  status finish  finish. upd ");
        }
        return true;
    }

    /**
     * 执行任务
     *
     * @param batchId
     * @param taskId
     * @param runFlag
     * @return
     */
    public boolean doTaskAndRetry(Long batchId, Long taskId, Long runFlag) {
        Map<String, Object> paramMap = new HashMap<>(3, 1);
        paramMap.put(ThreadTaskEnum.INPUT_BATCH_ID.name(), batchId);
        paramMap.put(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name(), runFlag);

        //获取当前任务信息
        ContainData conData = TaskContainerMap.INSTANCE.get(batchId);
        DcTaskInfoDO taskInfo = conData.getTaskMap().get(taskId);
        //任务的信息
        paramMap.put(ThreadTaskEnum.PROC_INPUT_TASK_INFO.name(), taskInfo);

        String retryCfg = taskInfo.getTaskRetry();

        //如果未配制，则默认不重试
        if (StringUtils.isEmpty(retryCfg)) {
            retryCfg = DEFAULT_RUN;
        }
        //执行重试
        RetryRsp rsp = RetryFunction.INSTANCE.apply(this::doTask, paramMap, retryCfg);

        //获取结果
        return rsp.getResult();
    }

    /**
     * 执行任务信息
     *
     * @param input 输入
     * @return 结果
     * @throws BusiException 异常
     */
    private Object doTask(Map<String, Object> input) throws BusiException {

        Long batchId = (Long) input.get(ThreadTaskEnum.INPUT_BATCH_ID.name());
        Long runFlag = (Long) input.get(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name());
        DcTaskInfoDO taskInfo = (DcTaskInfoDO) input.get(ThreadTaskEnum.PROC_INPUT_TASK_INFO.name());
        Integer currRunNum = (Integer) input.get(RunTaskConstant.TRY_NUMBER);

        // 进行批次状态的完成更改操作
        return doTask.runThread(batchId, runFlag, taskInfo, currRunNum);
    }


}

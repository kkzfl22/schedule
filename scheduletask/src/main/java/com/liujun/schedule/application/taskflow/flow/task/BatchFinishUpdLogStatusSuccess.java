package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.application.taskflow.constant.TaskRunStatusEnum;
import com.liujun.schedule.domain.task.entity.DcBatchLogDO;
import com.liujun.schedule.domain.task.service.DcBatchLogDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 当批次相关的任务都执行完成后，则将批次的状态修改为完成
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service("batchFinishUpdLogStatusSuccess")
public class BatchFinishUpdLogStatusSuccess implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(BatchFinishUpdLogStatusSuccess.class);

    /**
     * 记录下任务日志
     */
    @Autowired
    private DcBatchLogDomainService batchLogDomainService;

    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(ThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        // 当前流程的状态为未完成，则继续
        if (null != finishFlag && finishFlag) {

            logger.info("thread task flow update batch status task job log start ...");

            Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
            Long runFlag = context.getObject(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name());

            DcBatchLogDO batchLog = builderBatchSuccess(batchId, runFlag);
            //执行状态的修改操作
            boolean updRspFlag = batchLogDomainService.updateStatus(batchLog);
            logger.info("thread task.job flow update batch status task job log finish  rsp {}", updRspFlag);
        }
        return true;
    }


    /**
     * 批次成功的修改
     *
     * @param batchId 批次的id
     * @param runFlag 当前运行的日志
     * @return
     */
    public DcBatchLogDO builderBatchSuccess(Long batchId, Long runFlag) {
        DcBatchLogDO taskLog = new DcBatchLogDO();

        taskLog.setBatchId(batchId);
        taskLog.setTaskRuntimeFlag(runFlag);
        taskLog.setBatchRunStatus(TaskRunStatusEnum.SUCCESS.getStatus());
        taskLog.setBatchFinishTime(LocalDateTimeUtils.getMilliTime());
        taskLog.setBeforeBatchRunStatus(TaskRunStatusEnum.INIT.getStatus());


        return taskLog;
    }

}

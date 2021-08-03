package com.liujun.schedule.application.taskflow.flow.runtask;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.ddd.common.infrastructure.constant.ErrorCodeEnum;
import com.ddd.common.infrastructure.entity.ErrorInfo;
import com.ddd.common.infrastructure.exception.BusinessException;
import com.liujun.schedule.application.taskflow.constant.TaskErrorEnum;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.infrastructure.utils.GsonUtils;
import com.liujun.task.collect.TaskCollect;
import com.liujun.task.collect.TaskContainer;
import com.liujun.task.collect.TaskInf;
import com.liujun.task.entity.DcTaskInfo;
import com.liujun.task.entity.TaskEntity;
import com.liujun.schedule.domain.task.entity.DcTaskInfoDO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * 执行具体的任务
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service("doTask")
public class DoTask implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(DoTask.class);


    @Override
    public boolean invokeFlow(ContextContainer context) {
        logger.info("thread task job flow update task job status status start ...");

        Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
        Long runFlag = context.getObject(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name());
        DcTaskInfoDO taskInfo = context.getObject(ThreadTaskEnum.PROC_INPUT_TASK_INFO.name());
        Integer runNum = context.getObject(ThreadTaskEnum.PROC_INPUT_RUN_NUM.name());

        //从已经加载的任务中获取当前的任务类型
        TaskInf taskInstance = TaskCollect.INSTANCE.get(taskInfo.getTaskType());

        //如果当前任务不存在，则使用错误码记录下来
        if (null == taskInstance) {
            //记录下当前结果为未执行
            context.put(ThreadTaskEnum.PROC_TASK_RSP_FLAG.name(), Boolean.FALSE);
            context.put(ThreadTaskEnum.PROC_TASK_RSP_ERROR_CODE.name(), TaskErrorEnum.TASK_TYP_NOT_EXISTS.getErrorInfo());
            logger.info("thread task type {} not exists", taskInfo.getTaskType());
            return true;
        }

        TaskContainer container = builderRunTaskParam(batchId, runFlag, taskInfo, runNum);

        boolean runRsp = Boolean.FALSE;
        ErrorInfo errorInfo = null;
        try {
            runRsp = taskInstance.execute(container);
        } catch (BusinessException e) {
            errorInfo = e.getErrorInfo();
            logger.error("thread task BusinessException", e);
        } catch (Exception e) {
            errorInfo = ErrorCodeEnum.ERROR.getErrorInfo();
            logger.error("thread task Exception", e);
        }

        context.put(ThreadTaskEnum.PROC_TASK_RSP_FLAG.name(), runRsp);
        context.put(ThreadTaskEnum.PROC_TASK_RSP_ERROR_CODE.name(), errorInfo);

        logger.info("thread task job flow update task job status status  rsp {}", container, runRsp);

        return true;
    }

    /**
     * 构建任务运行对象
     *
     * @param batchId  批次号
     * @param runFlag  运行标识
     * @param taskInfo 任务信息
     * @param runNum   运行次数
     * @return
     */
    public TaskContainer builderRunTaskParam(Long batchId, Long runFlag, DcTaskInfoDO taskInfo, Integer runNum) {
        Map<String, String> cfgMap = null;
        if (StringUtils.isNotEmpty(taskInfo.getTaskCfg())) {
            cfgMap = GsonUtils.fromJson(taskInfo.getTaskCfg(), GsonUtils.MAP_STRING_TYPE);
        } else {
            cfgMap = Collections.EMPTY_MAP;
        }
        DcTaskInfo taskData = DcTaskInfo.builder()
                .taskId(taskInfo.getTaskId())
                .taskCfg(taskInfo.getTaskCfg())
                .status(taskInfo.getStatus())
                .taskDirId(taskInfo.getTaskDirId())
                .taskMsg(taskInfo.getTaskMsg())
                .taskName(taskInfo.getTaskName())
                .taskRetry(taskInfo.getTaskRetry())
                .taskType(taskInfo.getTaskType())
                .cfgMap(cfgMap)
                .build();
        TaskEntity taskEntity = new TaskEntity(batchId, runFlag, taskData, runNum);
        return new TaskContainer(taskEntity);

    }


}

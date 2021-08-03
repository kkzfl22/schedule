package com.liujun.schedule.application.taskflow.flow.runtask;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.application.taskflow.constant.TaskRunStatusEnum;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.domain.task.entity.DcTaskInfoDO;
import com.liujun.schedule.domain.task.entity.DcTaskLogDO;
import com.liujun.schedule.domain.task.service.DcTaskLogDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 当前任务还未运行完成，将当前任务的状态改为运行中
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service("addTaskStateRunning")
public class AddTaskStateRunning implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(AddTaskStateRunning.class);

    /**
     * 具体的任务的日志
     */
    @Autowired
    private DcTaskLogDomainService taskLogDomainService;


    @Override
    public boolean invokeFlow(ContextContainer context) {
        logger.info("thread task job flow update task job status status start ...");

        Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
        Long runFlag = context.getObject(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name());
        DcTaskInfoDO taskInfo = context.getObject(ThreadTaskEnum.PROC_INPUT_TASK_INFO.name());
        Integer runNum = context.getObject(ThreadTaskEnum.PROC_INPUT_RUN_NUM.name());

        //任务批次日志
        DcTaskLogDO addBatchLog = this.taskLog(batchId, runFlag, taskInfo, runNum);

        // 进行批次状态的完成更改操作
        boolean updTaskStatusRsp = taskLogDomainService.insert(addBatchLog);

        logger.info("thread task job flow update task job status status  rsp {}", updTaskStatusRsp);
        return true;
    }

    /**
     * 添加任务日志
     *
     * @param batchId  批次号
     * @param runFlag  当前运行标识
     * @param taskInfo 任务信息
     * @param runNum   当前运行的次数
     * @return 日志对象
     */
    private DcTaskLogDO taskLog(Long batchId, Long runFlag, DcTaskInfoDO taskInfo, Integer runNum) {
        DcTaskLogDO insertData = new DcTaskLogDO();

        insertData.setBatchId(batchId);
        insertData.setTaskId(taskInfo.getTaskId());
        insertData.setTaskRuntimeFlag(runFlag);
        insertData.setTaskCfg(taskInfo.getTaskCfg());
        insertData.setTaskRunNum(runNum);
        insertData.setTaskName(taskInfo.getTaskName());
        insertData.setTaskStartTime(LocalDateTimeUtils.getMilliTime());
        insertData.setTaskStatus(TaskRunStatusEnum.INIT.getStatus());

        return insertData;
    }
}

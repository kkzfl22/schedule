package com.liujun.schedule.application.taskflow.flow.runtask;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.domain.task.entity.DcTaskLogDO;
import com.liujun.schedule.domain.task.service.DcTaskLogDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 具体的任务的日志
     */
    @Autowired
    private DcTaskLogDomainService taskLogDomainService;


    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(ThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        // 当前流程的状态为未完成，则继续
        if (null != finishFlag && !finishFlag) {

            logger.info("thread task job flow update task job status status start ...");

            Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
            Long taskId = context.getObject(ThreadTaskEnum.INPUT_TASK_ID.name());
            Long runFlag = context.getObject(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name());
            Boolean rspFlag = context.getObject(ThreadTaskEnum.PROC_TASK_RSP_FLAG.name());

            //DcEtlTaskStatusDTO taskStatus = new DcEtlTaskStatusDTO();
            //
            //taskStatus.setBatchId(batchId);
            //taskStatus.setTaskId(taskId);
            //taskStatus.setTaskStartTime(System.currentTimeMillis());
            //taskStatus.setTaskRuntimeFlag(runFlag);
            //// 状态设置为修改中
            //taskStatus.setTaskStatus(TaskStatusEnum.RUNNING.getStatus());
            //
            //// 进行批次状态的完成更改操作
            boolean updTaskStatusRsp = false; //taskLogDomainService.insert()


            logger.info("thread task job flow update task job status status  rsp {}", updTaskStatusRsp);
        }
        return true;
    }

    /**
     * 任务日志
     *
     * @param batchId 批次号
     * @param taskId  任务号
     * @param runFlag 运行标识
     * @return
     */
    private DcTaskLogDO taskLog(Long batchId, Long taskId, Long runFlag) {
        DcTaskLogDO insertData = new DcTaskLogDO();

        insertData.setBatchId(batchId);
        insertData.setTaskId(taskId);
        insertData.setTaskRuntimeFlag(runFlag);


        return insertData;
    }
}

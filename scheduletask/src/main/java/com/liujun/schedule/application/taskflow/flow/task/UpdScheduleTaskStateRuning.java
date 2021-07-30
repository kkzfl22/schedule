package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.EtlThreadTaskEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 当前任务还未运行完成，将当前任务的状态改为运行中
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service
public class UpdScheduleTaskStateRuning implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(UpdScheduleTaskStateRuning.class);

    /**
     * 任务的状态操作
     */
    //@Autowired
    //private DcEtlTaskStatusService taskStatusService;
    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(EtlThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        // 当前流程的状态为未完成，则继续
        if (null != finishFlag && !finishFlag) {

            logger.info("thread sqlmap.sqlmap.job flow update sqlmap.sqlmap.job status status start ...");

            Long batchId = context.getObject(EtlThreadTaskEnum.INPUT_BATCH_ID.name());
            Long taskId = context.getObject(EtlThreadTaskEnum.INPUT_TASK_ID.name());
            Long runFlag = context.getObject(EtlThreadTaskEnum.INPUT_RUNTIME_FLAG.name());

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
            //boolean updTaskStatusRsp = taskStatusService.update(taskStatus);
            boolean updTaskStatusRsp = false;

            logger.info("thread sqlmap.sqlmap.job flow update sqlmap.sqlmap.job status status  rsp {}", updTaskStatusRsp);
        }
        return true;
    }
}

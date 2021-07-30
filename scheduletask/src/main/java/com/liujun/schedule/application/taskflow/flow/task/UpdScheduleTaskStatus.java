package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.EtlThreadTaskEnum;
import com.liujun.schedule.application.taskflow.constant.TaskRunStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 根据任务的运行状态，修改流程的状态操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service
public class UpdScheduleTaskStatus implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(UpdScheduleTaskStatus.class);

    /**
     * 任务的状态操作
     */
    //@Autowired private DcEtlTaskStatusService taskStatusService;
    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(EtlThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        // 当前流程的状态为未完成，则继续
        if (null != finishFlag && !finishFlag) {

            logger.info("thread sqlmap.sqlmap.job flow update sqlmap.sqlmap.job finish status status start ...");

            boolean runRsp = context.getObject(EtlThreadTaskEnum.PROC_RUN_TASK_RSP.name());

            int rspCode = TaskRunStatusEnum.FAIL.getStatus();

            if (runRsp) {
                rspCode = TaskRunStatusEnum.SUCCESS.getStatus();
            }

            Long batchId = context.getObject(EtlThreadTaskEnum.INPUT_BATCH_ID.name());
            Long taskId = context.getObject(EtlThreadTaskEnum.INPUT_TASK_ID.name());
            Long runFlag = context.getObject(EtlThreadTaskEnum.INPUT_RUNTIME_FLAG.name());

            //DcEtlTaskStatusDTO taskStatus = new DcEtlTaskStatusDTO();
            //
            //taskStatus.setBatchId(batchId);
            //taskStatus.setTaskId(taskId);
            //taskStatus.setTaskFinishTime(System.currentTimeMillis());
            //taskStatus.setTaskRuntimeFlag(runFlag);
            //// 根据结果过量行修改的修改操作
            //taskStatus.setTaskStatus(rspCode);
            //
            //// 进行批次状态的完成更改操作
            //boolean updTaskStatusRsp = taskStatusService.update(taskStatus);
            boolean updTaskStatusRsp = false;

            logger.info("thread sqlmap.sqlmap.job flow update sqlmap.sqlmap.job finish status status  rsp {}", updTaskStatusRsp);
        }
        return true;
    }
}

package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.EtlThreadTaskEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 当执行任务时发生错误，则将批次日志状态修改为错误，并记录下结束时间
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service
public class UpdBatchLogStatusFail implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(UpdBatchLogStatusFail.class);

    /**
     * 记录下任务日志
     */
    // @Autowired private DcEtlTaskLogService etlTaskLogService;
    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(EtlThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        // 当前流程的状态为未完成，则继续
        if (null != finishFlag && !finishFlag) {

            // 当状态为失败，则修改批次状态为失败，退出业务流程
            boolean runRsp = context.getObject(EtlThreadTaskEnum.PROC_RUN_TASK_RSP.name());
            if (!runRsp) {

                logger.info("thread sqlmap.sqlmap.job flow update batch status sqlmap.sqlmap.job log start ...");

                Long batchId = context.getObject(EtlThreadTaskEnum.INPUT_BATCH_ID.name());
                Long taskId = 0L;
                Long runFlag = context.getObject(EtlThreadTaskEnum.INPUT_RUNTIME_FLAG.name());


                //DcEtlTaskLogDTO taskLog = new DcEtlTaskLogDTO();
                //
                //taskLog.setTaskId(taskId);
                //taskLog.setBatchId(batchId);
                //taskLog.setTaskStartTime(System.currentTimeMillis());
                //taskLog.setTaskRuntimeFlag(runFlag);
                //taskLog.setTaskStatus(TaskStatusEnum.FAIL.getStatus());
                //taskLog.setTaskFinishTime(System.currentTimeMillis());
                //
                //
                //boolean updRspFlag = etlTaskLogService.update(taskLog);
                boolean updRspFlag = false;
                logger.info("thread sqlmap.sqlmap.job flow update batch status sqlmap.sqlmap.job log finish  rsp {}", updRspFlag);
            }
        }
        return true;
    }
}

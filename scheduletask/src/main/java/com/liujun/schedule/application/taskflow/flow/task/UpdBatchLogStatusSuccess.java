package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.EtlThreadTaskEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 当批次相关的任务都执行完成后，则将批次的状态修改为完成
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service
public class UpdBatchLogStatusSuccess implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(UpdBatchLogStatusSuccess.class);

    /**
     * 记录下任务日志
     */
    //@Autowired private DcEtlTaskLogService etlTaskLogService;
    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(EtlThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        // 当前流程的状态为未完成，则继续
        if (null != finishFlag && finishFlag) {

            logger.info("thread task flow update batch status sqlmap.sqlmap.job log start ...");

            Long batchId = context.getObject(EtlThreadTaskEnum.INPUT_BATCH_ID.name());
            Long taskId = 0L;
            Long runFlag = context.getObject(EtlThreadTaskEnum.INPUT_RUNTIME_FLAG.name());

            //DcEtlTaskLogDTO taskLog = new DcEtlTaskLogDTO();
            //
            //taskLog.setTaskId(taskId);
            //taskLog.setBatchId(batchId);
            //taskLog.setTaskStartTime(System.currentTimeMillis());
            //taskLog.setTaskRuntimeFlag(runFlag);
            //taskLog.setTaskStatus(TaskStatusEnum.SUCCESS.getStatus());
            //taskLog.setTaskFinishTime(System.currentTimeMillis());
            //
            //boolean updRspFlag = etlTaskLogService.update(taskLog);
            boolean updRspFlag = false;
            logger.info("thread task.job flow update batch status sqlmap.sqlmap.job log finish  rsp {}", updRspFlag);
        }
        return true;
    }
}

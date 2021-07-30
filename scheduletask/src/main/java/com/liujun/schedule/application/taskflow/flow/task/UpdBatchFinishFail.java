package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.EtlThreadTaskEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 检查当前的任务是否失败失败，如果失败，则将批次也修改为失败
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service
public class UpdBatchFinishFail implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(UpdBatchFinishFail.class);

    /**
     * 批次状态状态服务操作
     */
    // @Autowired private DcEtlBatchStatusService batchStatusService;
    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(EtlThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        // 当前的状态需在进行中才能继续
        if (null != finishFlag && !finishFlag) {

            // 当状态为失败，则修改批次状态为失败，退出业务流程
            boolean runRsp = context.getObject(EtlThreadTaskEnum.PROC_RUN_TASK_RSP.name());
            if (!runRsp) {
                logger.info("thread sqlmap.sqlmap.job flow update batch status finish  start ...");

                //DcEtlBatchStatusDTO batchStatus = new DcEtlBatchStatusDTO();
                //
                //Long batchId = context.getObject(EtlThreadTaskEnum.INPUT_BATCH_ID.name());
                //
                //batchStatus.setBatchId(batchId);
                //batchStatus.setBatchStatusBefore(BatchStatusEnum.RUNNING.getStatus());
                //batchStatus.setBatchStatus(BatchStatusEnum.FAIL.getStatus());
                //batchStatus.setBatchFinishTime(System.currentTimeMillis());
                //
                //// 进行批次状态的完成更改操作
                //boolean updRsp = batchStatusService.update(batchStatus);
                boolean updRsp = false;

                logger.info("thread sqlmap.sqlmap.job flow update batch status finish  finish. upd rsp {}", updRsp);
            }
        }
        return true;
    }
}

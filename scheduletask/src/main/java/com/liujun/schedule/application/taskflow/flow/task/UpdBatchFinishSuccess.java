package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.EtlThreadTaskEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 将当前批次的状态改为完成
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service
public class UpdBatchFinishSuccess implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(UpdBatchFinishSuccess.class);

    /**
     * 批次状态状态服务操作
     */
    //@Autowired private DcEtlBatchStatusService batchStatusService;
    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(EtlThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        if (null != finishFlag && finishFlag) {

            logger.info("thread sqlmap.sqlmap.job flow update  status finsi  start ...");

            //DcEtlBatchStatusDTO batchStatus = new DcEtlBatchStatusDTO();
            //
            //Long batchId = context.getObject(EtlThreadTaskEnum.INPUT_BATCH_ID.name());
            //
            //batchStatus.setBatchId(batchId);
            //batchStatus.setBatchStatusBefore(BatchStatusEnum.RUNNING.getStatus());
            //batchStatus.setBatchStatus(BatchStatusEnum.SUCCESS.getStatus());
            //batchStatus.setBatchFinishTime(System.currentTimeMillis());
            //
            //// 进行批次状态的完成更改操作
            //boolean updRsp = batchStatusService.update(batchStatus);
            boolean updRsp = false;

            logger.info("thread sqlmap.sqlmap.job flow update  status finsi  finish. upd rsp {}", updRsp);
        }
        return true;
    }
}

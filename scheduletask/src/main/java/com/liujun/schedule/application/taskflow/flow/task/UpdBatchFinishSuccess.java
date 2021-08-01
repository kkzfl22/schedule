package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.domain.task.constant.BatchRunStatusEnum;
import com.liujun.schedule.domain.task.entity.DcBatchInfoDO;
import com.liujun.schedule.domain.task.service.DcBatchInfoDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 将当前批次的状态改为完成
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service("updBatchFinishSuccess")
public class UpdBatchFinishSuccess implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(UpdBatchFinishSuccess.class);

    /**
     * 批次状态状态服务操作
     */
    @Autowired
    private DcBatchInfoDomainService batchStatusService;

    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(ThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        if (null != finishFlag && finishFlag) {
            logger.info("thread task job flow update  status finsi  start ...");

            Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
            DcBatchInfoDO runBatchStatus = builderUpdateBatch(batchId);

            // 进行批次状态的完成更改操作
            boolean updRsp = batchStatusService.updateRunStatus(runBatchStatus);

            logger.info("thread task job flow update  status finsi  finish. upd rsp {}", updRsp);
        }
        return true;
    }

    /**
     * 构建修改批次的数据
     *
     * @param batchId
     * @return
     */
    private DcBatchInfoDO builderUpdateBatch(Long batchId) {
        DcBatchInfoDO batchRunStatus = new DcBatchInfoDO();

        batchRunStatus.setBatchId(batchId);
        batchRunStatus.setBatchRunStatusBefore(BatchRunStatusEnum.RUNNING.getStatus());
        batchRunStatus.setBatchStatus(BatchRunStatusEnum.FINISH.getStatus());

        return batchRunStatus;
    }

}

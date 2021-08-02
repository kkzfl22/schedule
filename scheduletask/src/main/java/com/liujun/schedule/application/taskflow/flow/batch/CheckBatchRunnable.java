package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.task.task.constant.BatchRunStatusEnum;
import com.liujun.task.task.entity.DcBatchInfoDO;
import com.liujun.task.task.service.DcBatchInfoDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 检查批次是否正在运行中
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/11
 */
@Service("checkBatchRunnable")
public class CheckBatchRunnable implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(CheckBatchRunnable.class);

    /**
     * 批次操作服务
     */
    @Autowired
    private DcBatchInfoDomainService batchService;

    @Override
    public boolean invokeFlow(ContextContainer context) {

        Long batchId = context.getObject(BatchFLowEnum.INPUT_BATCH_ID.name());
        Long runtime = context.getObject(BatchFLowEnum.PROC_RUNTIME_FLAG.name());

        while (true) {
            // 1,获取批次的id操作
            DcBatchInfoDO batchInfo = this.getBatchInfo(batchId);
            // 如果当前的状态为空
            if (null != batchInfo) {
                // 检查当前批次的状态信息,如果在运行中，则退出
                if (batchInfo.getBatchRunStatus() == BatchRunStatusEnum.RUNNING.getStatus()) {
                    logger.info("batch {} curr running already , curr exists", batchId);
                    return false;
                }
                // 其他状态则需要将任务改为执行中
                else {
                    // 进行数据的更新状态操作
                    boolean updStatusRsp = this.updateStatus(batchInfo, runtime);

                    // 当数据更新成功时，则继续
                    if (updStatusRsp) {
                        context.put(BatchFLowEnum.PROC_BATCH_INFO.name(), batchInfo);
                        return true;
                    }

                    // 当流程未更新成功说明状态已经非之前的状态，则再次进行检查状态，进行流程操作
                }
            }

            logger.error("check batch info {} not exists ", batchId);
            return false;
        }
    }

    private DcBatchInfoDO getBatchInfo(Long batchId) {
        DcBatchInfoDO batchQuery = new DcBatchInfoDO();
        batchQuery.setBatchId(batchId);
        return batchService.detail(batchQuery);
    }

    /**
     * 更新状态信息
     *
     * @param batchState  批次状态信息
     * @param runtimeFlag 运行的标识
     * @return 数据更新的结果
     */
    private boolean updateStatus(DcBatchInfoDO batchState, Long runtimeFlag) {
        DcBatchInfoDO updateData = new DcBatchInfoDO();

        // 设置之前的状态
        updateData.setBatchRunStatusBefore(batchState.getBatchRunStatus());
        updateData.setBatchId(batchState.getBatchId());
        // 修改当前的状态，并添加开始时间
        updateData.setBatchRunStatus(BatchRunStatusEnum.RUNNING.getStatus());
        // 添加每个批次运行的标识
        updateData.setTaskRuntimeFlag(runtimeFlag);

        return batchService.updateRunStatus(updateData);
    }
}

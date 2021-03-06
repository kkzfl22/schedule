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
 * 当任务执行失败时，标记当前任务为执行完成
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service("updBatchStatusRsp")
public class UpdBatchLogStatusRsp implements FlowInf {

  private Logger logger = LoggerFactory.getLogger(UpdBatchLogStatusRsp.class);

  /** 批次操作服务 */
  @Autowired private DcBatchInfoDomainService batchService;

  @Override
  public boolean invokeFlow(ContextContainer context) {

    Boolean finishFlag = context.getObject(ThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

    // 当前流程的状态为未完成，则继续
    if (!finishFlag) {

      // 当状态为失败，则修改批次状态为失败，退出业务流程
      boolean runRsp = context.getObject(ThreadTaskEnum.PROC_TASK_RSP_FLAG.name());

      logger.info("thread task job flow update batch status task job log start ...");

      Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
      Long runFlag = context.getObject(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name());

      boolean updRspFlag = this.updateStatus(batchId, runFlag, runRsp);

      logger.info(
          "thread task job flow update batch status task job log finish  rsp {}", updRspFlag);
    }
    return true;
  }

  /**
   * 更新状态信息
   *
   * @param batchId 批次号
   * @param runtimeFlag 运行的标识
   * @return 数据更新的结果
   */
  private boolean updateStatus(Long batchId, Long runtimeFlag, boolean runRsp) {
    DcBatchInfoDO updateData = new DcBatchInfoDO();

    // 设置之前的状态
    updateData.setBatchRunStatusBefore(BatchRunStatusEnum.RUNNING.getStatus());
    updateData.setBatchId(batchId);
    // 修改当前的状态
    if (runRsp) {
      updateData.setBatchRunStatus(BatchRunStatusEnum.SUCCESS.getStatus());
    } else {
      updateData.setBatchRunStatus(BatchRunStatusEnum.FAIL.getStatus());
    }

    // 添加每个批次运行的标识
    updateData.setTaskRuntimeFlag(runtimeFlag);

    return batchService.updateRunStatus(updateData);
  }
}

package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.domain.task.constant.BatchRunStatusEnum;
import com.liujun.schedule.domain.task.entity.DcBatchInfoDO;
import com.liujun.schedule.domain.task.entity.DcBatchLogDO;
import com.liujun.schedule.domain.task.service.DcBatchLogDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 添加批次运行日志
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service("addBatchRunLog")
public class AddBatchRunLog implements FlowInf {

  private Logger logger = LoggerFactory.getLogger(AddBatchRunLog.class);

  /** 记录下任务日志 */
  @Autowired private DcBatchLogDomainService batchLogDomainService;

  @Override
  public boolean invokeFlow(ContextContainer context) {

    logger.info("thread task flow update batch status task job log start ...");
    Long runFlag = context.getObject(BatchFLowEnum.PROC_RUNTIME_FLAG.name());

    // 批次信息
    DcBatchInfoDO batchInfo = context.getObject(BatchFLowEnum.PROC_BATCH_INFO.name());
    DcBatchLogDO batchLog = batchLog(batchInfo, runFlag);
    boolean updRspFlag = batchLogDomainService.insert(batchLog);
    logger.info("thread task.job flow update batch status task job log finish  rsp {}", updRspFlag);

    return true;
  }

  /**
   * 添加批次运行任务的开始日志
   *
   * @param batchInfo 批次信息
   * @param taskRunFlow 结束信息
   * @return
   */
  private DcBatchLogDO batchLog(DcBatchInfoDO batchInfo, Long taskRunFlow) {
    DcBatchLogDO taskLog = new DcBatchLogDO();

    taskLog.setBatchId(batchInfo.getBatchId());
    taskLog.setBatchConcurrent(batchInfo.getBatchConcurrent());
    taskLog.setTaskRuntimeFlag(taskRunFlow);
    taskLog.setBatchRunStatus(BatchRunStatusEnum.RUNNING.getStatus());
    taskLog.setBatchStartTime(LocalDateTimeUtils.getMilliTime());
    taskLog.setBatchName(batchInfo.getBatchName());

    return taskLog;
  }
}

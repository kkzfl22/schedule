package com.liujun.schedule.application.taskflow.flow.runtask;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.ddd.common.infrastructure.constant.ErrorCodeEnum;
import com.ddd.common.infrastructure.entity.ErrorInfo;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.application.taskflow.constant.TaskRunStatusEnum;
import com.liujun.schedule.domain.task.entity.DcTaskInfoDO;
import com.liujun.schedule.domain.task.entity.DcTaskLogDO;
import com.liujun.schedule.domain.task.service.DcTaskLogDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 根据任务的运行状态，修改流程的状态操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service("updScheduleTaskStatus")
public class UpdScheduleTaskStatus implements FlowInf {

  private Logger logger = LoggerFactory.getLogger(UpdScheduleTaskStatus.class);

  /** 具体的任务的日志 */
  @Autowired private DcTaskLogDomainService taskLogDomainService;

  @Override
  public boolean invokeFlow(ContextContainer context) {
    logger.info("thread task job flow update task job finish status status start ...");

    boolean runRsp = context.getObject(ThreadTaskEnum.PROC_TASK_RSP_FLAG.name());
    Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
    Long runFlag = context.getObject(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name());
    DcTaskInfoDO taskInfo = context.getObject(ThreadTaskEnum.PROC_INPUT_TASK_INFO.name());

    DcTaskLogDO taskLog = null;
    if (runRsp) {
      taskLog = this.updTaskLogSuccess(batchId, runFlag, taskInfo);
    } else {
      ErrorInfo errorCode = context.getObject(ThreadTaskEnum.PROC_TASK_RSP_ERROR_CODE.name());
      taskLog = this.updTaskLogFail(batchId, runFlag, taskInfo, errorCode);
    }

    // 进行批次状态的完成更改操作
    boolean updTaskStatusRsp = taskLogDomainService.updateStatus(taskLog);

    logger.info(
        "thread task job flow update task job finish status status  rsp {}", updTaskStatusRsp);

    return true;
  }

  /**
   * 修改为成功的任务日志
   *
   * @param batchId 批次号
   * @param runFlag 当前运行标识
   * @param taskInfo 任务信息
   * @return 日志对象
   */
  private DcTaskLogDO updTaskLogSuccess(Long batchId, Long runFlag, DcTaskInfoDO taskInfo) {
    DcTaskLogDO insertData = this.builderTaskLogRsp(batchId, runFlag, taskInfo);

    insertData.setTaskStatus(TaskRunStatusEnum.SUCCESS.getStatus());
    insertData.setTaskMsg(ErrorCodeEnum.SUCCESS.getErrorInfo().getMsg());

    return insertData;
  }

  /**
   * 构建任务日志的响应
   *
   * @param batchId 批次号
   * @param runFlag 运行标识
   * @param taskInfo 任务信息
   * @return
   */
  private DcTaskLogDO builderTaskLogRsp(Long batchId, Long runFlag, DcTaskInfoDO taskInfo) {
    DcTaskLogDO insertData = new DcTaskLogDO();
    insertData.setBatchId(batchId);
    insertData.setTaskId(taskInfo.getTaskId());
    insertData.setTaskRuntimeFlag(runFlag);
    insertData.setTaskFinishTime(LocalDateTimeUtils.getMilliTime());
    insertData.setBeforeStatus(TaskRunStatusEnum.INIT.getStatus());

    return insertData;
  }

  /**
   * 修改为失败的
   *
   * @param batchId 批次号
   * @param runFlag 当前运行标识
   * @param taskInfo 任务信息
   * @return 日志对象
   */
  private DcTaskLogDO updTaskLogFail(
      Long batchId, Long runFlag, DcTaskInfoDO taskInfo, ErrorInfo error) {
    DcTaskLogDO insertData = this.builderTaskLogRsp(batchId, runFlag, taskInfo);
    insertData.setTaskStatus(TaskRunStatusEnum.FAIL.getStatus());
    insertData.setTaskMsg(error.getMsg());
    return insertData;
  }
}

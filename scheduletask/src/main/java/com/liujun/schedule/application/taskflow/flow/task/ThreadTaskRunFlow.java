package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.EtlThreadTaskEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 进行的运行设置操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/14
 */
public class ThreadTaskRunFlow {

  private Logger logger = LoggerFactory.getLogger(ThreadTaskRunFlow.class);

  /** 任务的运行流程 */
  private List<FlowInf> threadThreadFlow;

  /**
   * 运行线程池的任务流程
   *
   * @param batchId
   * @param taskId
   * @param runTimeFlag
   */
  public void runThread(Long batchId, Long taskId, Long runTimeFlag) {

    logger.info("thread run sqlmap.sqlmap.job batchid {} taskId {} runflag {}", batchId, taskId, runTimeFlag);

    ContextContainer context = new ContextContainer();

    context.put(EtlThreadTaskEnum.INPUT_TASK_ID.name(), taskId);
    context.put(EtlThreadTaskEnum.INPUT_BATCH_ID.name(), batchId);
    context.put(EtlThreadTaskEnum.INPUT_RUNTIME_FLAG.name(), runTimeFlag);

    boolean success = true;

    for (FlowInf flow : threadThreadFlow) {
      if (!flow.invokeFlow(context)) {
        success = false;
        break;
      }
    }

    context = null;

    logger.info(
        "thread run sqlmap.sqlmap.job batchid {} taskId {} runflag {} rsp {}",
        batchId,
        taskId,
        runTimeFlag,
        success);
  }

  public void setThreadThreadFlow(List<FlowInf> threadThreadFlow) {
    this.threadThreadFlow = threadThreadFlow;
  }
}

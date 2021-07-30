package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.EtlThreadTaskEnum;
import com.liujun.schedule.application.taskflow.container.TaskContainerMap;
import com.liujun.schedule.application.taskflow.graph.ContainData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 检查当前流程是否已经结束
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service
public class CheckBatchFail implements FlowInf {

  private Logger logger = LoggerFactory.getLogger(CheckBatchFail.class);

  @Override
  public boolean invokeFlow(ContextContainer context) {

    Boolean finishFlag = context.getObject(EtlThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

    // 当前流程的状态为未完成，则继续
    if (null != finishFlag && !finishFlag) {

      // 检查批次是否已经失败
      Long batchId = context.getObject(EtlThreadTaskEnum.INPUT_BATCH_ID.name());
      // 1,从内存中获取当前的任务信息
      ContainData containDataData = TaskContainerMap.INSTANCE.get(batchId);

      if (containDataData == null) {
        logger.info("batch id {} not exists,curr exists !", batchId);
        return false;
      }
    }

    return true;
  }
}

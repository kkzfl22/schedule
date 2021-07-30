package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.EtlThreadTaskEnum;
import com.liujun.schedule.application.taskflow.container.TaskContainerMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 当执行任务时发生错误，则将批次日志状态修改为错误，并记录下结束时间
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service
public class TaskStatusFailClean implements FlowInf {

  private Logger logger = LoggerFactory.getLogger(TaskStatusFailClean.class);

  @Override
  public boolean invokeFlow(ContextContainer context) {

    Boolean finishFlag = context.getObject(EtlThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

    // 当前流程的状态为未完成，则继续
    if (null != finishFlag && !finishFlag) {

      // 当状态为失败，则修改批次状态为失败，退出业务流程
      boolean runRsp = context.getObject(EtlThreadTaskEnum.PROC_RUN_TASK_RSP.name());
      if (!runRsp) {

        logger.info("thread sqlmap.sqlmap.job flow  batch fail status sqlmap.sqlmap.job clean start ...");

        Long batchId = context.getObject(EtlThreadTaskEnum.INPUT_BATCH_ID.name());
        // 将批次相关的信息进行移除操作
        TaskContainerMap.INSTANCE.remove(batchId);

        logger.info("thread sqlmap.sqlmap.job flow  batch fail  status sqlmap.sqlmap.job clean finish");
      }
    }
    return true;
  }
}

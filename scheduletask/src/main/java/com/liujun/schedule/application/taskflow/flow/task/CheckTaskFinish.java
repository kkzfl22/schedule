package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.application.taskflow.constant.GraphPointEnum;
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
@Service("checkTaskFinish")
public class CheckTaskFinish implements FlowInf {

  private Logger logger = LoggerFactory.getLogger(CheckTaskFinish.class);

  @Override
  public boolean invokeFlow(ContextContainer context) {

    logger.info("thread task job flow check finish start ...");

    Long taskId = context.getObject(ThreadTaskEnum.INPUT_TASK_ID.name());

    boolean finish = false;
    // 当前流程已经结束，则启动任务结束的流程
    if (GraphPointEnum.VIRTUAL_FINISH_POINT.getPoint().equals(taskId)) {
      finish = true;
    }

    context.put(ThreadTaskEnum.PROC_RUN_FINISH_FLAG.name(), finish);
    logger.info("thread task job flow check finish rsp {}", finish);

    return true;
  }
}

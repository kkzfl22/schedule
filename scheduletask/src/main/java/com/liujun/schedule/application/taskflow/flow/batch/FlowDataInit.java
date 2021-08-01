package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 批次数据初始化
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/11
 */
@Service("flowDataInit")
public class FlowDataInit implements FlowInf {

  private Logger logger = LoggerFactory.getLogger(FlowDataInit.class);

  @Override
  public boolean invokeFlow(ContextContainer context) {

    logger.info("flow data init start");

    // 设置每次执行的标识信息
    context.put(BatchFLowEnum.PROC_RUNTIME_FLAG.name(), System.currentTimeMillis());

    logger.info("flow data init finish");
    return true;
  }
}

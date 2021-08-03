package com.liujun.schedule.application.taskflow.flow.batch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 测试整个流程的运行
 *
 * @author liujun
 * @since 2021/8/2
 */
@Configuration
public class TestBatchRunFlowGet {

  /** 批次任务运行 */
  @Autowired private BatchRunFlow batchRunFlow;

  @Test
  public void runBatch() {
    // 进行任务的执行操作
    batchRunFlow.runBatch(1001L);
  }
}

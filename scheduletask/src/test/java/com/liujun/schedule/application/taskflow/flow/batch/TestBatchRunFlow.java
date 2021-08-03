package com.liujun.schedule.application.taskflow.flow.batch;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.liujun.schedule.application.taskflow.flow.batch.task.TaskRun;
import com.liujun.schedule.application.taskflow.flow.batch.task.TaskRunRetry;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.task.collect.TaskCollect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

/**
 * 测试整个流程的运行
 *
 * @author liujun
 * @since 2021/8/2
 */
@SpringBootTest(classes = {DruidDataSourceAutoConfigure.class, MybatisAutoConfiguration.class})
@ComponentScan(
    basePackages = {
      "com.liujun.schedule.application.taskflow.flow",
      "com.liujun.schedule.domain",
      "com.liujun.schedule.infrastructure"
    })
@TestPropertySource("classpath:application.yml")
@Import(MyBatisScanConfiguration.class)
@Slf4j
public class TestBatchRunFlow {

  /** 批次任务运行 */
  @Autowired private BatchRunFlow batchRunFlow;

  @Test
  public void runBatch() throws InterruptedException {

    // 注入任务
    TaskCollect.INSTANCE.put(new TaskRun());

    // 进行任务的执行操作
    batchRunFlow.runBatch(1001L);
    Thread.sleep(120000);
  }

  @Test
  public void runBatchRetry() throws InterruptedException {

    // 注入重试任务
    TaskCollect.INSTANCE.put(new TaskRunRetry());

    // 进行任务的执行操作
    batchRunFlow.runBatch(1001L);
    Thread.sleep(120000);
  }
}

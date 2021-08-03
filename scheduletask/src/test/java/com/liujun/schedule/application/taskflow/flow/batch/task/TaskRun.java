/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package com.liujun.schedule.application.taskflow.flow.batch.task;

import com.liujun.task.collect.TaskContainer;
import com.liujun.task.collect.TaskInf;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author liujun
 * @since 2021/8/3
 */
public class TaskRun implements TaskInf {

  @Override
  public String getType() {
    return "test";
  }

  @Override
  public boolean execute(TaskContainer container) throws InterruptedException {
    int rsp = RandomUtils.nextInt(500, 2500);
    Thread.sleep(rsp);

    return true;
  }
}

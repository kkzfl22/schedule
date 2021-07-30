package com.liujun.schedule.application.taskflow.retry;

import com.ddd.common.infrastructure.exception.BusiException;

import java.util.Map;

/**
 * 重试的接口定义
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/06/01
 */
@FunctionalInterface
public interface RetryInf {

  /**
   * 重试的接口设置
   *
   * @param input
   * @return
   * @throws Exception
   */
  Object retry(Map<String, Object> input) throws BusiException;
}

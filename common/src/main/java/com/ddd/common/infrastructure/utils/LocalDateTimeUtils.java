/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package com.ddd.common.infrastructure.utils;

import java.time.LocalDateTime;

/**
 * 时间运行
 *
 * @author liujun
 * @since 2021/7/29
 */
public class LocalDateTimeUtils {

  private LocalDateTimeUtils() {}

  /** 时间标识 */
  private static final int TIME_CFG = 1000000000;

  /**
   * 获取当前时间
   *
   * @return
   */
  public static LocalDateTime getDatabaseLocalDateTime() {
    LocalDateTime time1 = LocalDateTime.now();
    return time1.withNano((time1.getNano() / TIME_CFG) * TIME_CFG);
  }
}
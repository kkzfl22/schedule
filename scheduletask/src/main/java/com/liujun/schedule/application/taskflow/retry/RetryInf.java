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
     * @param input 入参数
     * @return 返回的参数
     * @throws BusiException 异常信息
     */
    Object reTry(Map<String, Object> input) throws BusiException;
}

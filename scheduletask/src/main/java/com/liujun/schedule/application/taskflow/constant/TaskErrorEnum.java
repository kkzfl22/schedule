/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package com.liujun.schedule.application.taskflow.constant;

import com.ddd.common.infrastructure.entity.ErrorInfo;

/**
 * 错误枚举信息
 *
 * @author liujun
 * @since 2021/8/2
 */
public enum TaskErrorEnum {


    /**
     * 任务类型不存在
     */
    TASK_TYP_NOT_EXISTS(-1001, "任务类型不存在"),


    ;

    /**
     * 错误对象
     */
    private ErrorInfo errorInfo;


    TaskErrorEnum(int code, String msg) {
        this.errorInfo = new ErrorInfo(String.valueOf(code), msg);
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaskErrorEnum{");
        sb.append("errorInfo=").append(errorInfo);
        sb.append('}');
        return sb.toString();
    }
}

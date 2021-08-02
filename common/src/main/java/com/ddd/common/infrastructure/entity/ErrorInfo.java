/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package com.ddd.common.infrastructure.entity;

/**
 * 错误信息对象
 *
 * @author liujun
 * @since 2021/8/2
 */
public class ErrorInfo {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误内容
     */
    private String msg;

    public ErrorInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorInfo{");
        sb.append("code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.ddd.common.infrastructure.constant;

import com.ddd.common.infrastructure.entity.ErrorInfo;

/**
 * 错误码信息枚举
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/08/27
 */
public enum ErrorCodeEnum {
    /**
     * 成功的错误码信息
     */
    SUCCESS(0, "操作成功"),


    /**
     * 失败的错误码标识
     */
    ERROR(-1, "执行发生错误"),


    ;

    /**
     * 错误对象
     */
    private ErrorInfo errorInfo;


    ErrorCodeEnum(int code, String msg) {
        this.errorInfo = new ErrorInfo(String.valueOf(code), msg);
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorCodeEnum{");
        sb.append("errorInfo=").append(errorInfo);
        sb.append('}');
        return sb.toString();
    }
}

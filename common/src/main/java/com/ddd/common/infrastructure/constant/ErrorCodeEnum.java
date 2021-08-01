package com.ddd.common.infrastructure.constant;

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

    FAIL(-1, "操作失败,请联系管理员"),

    ERROR(-11, "系统错误"),


    ;

    private int code;

    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorCodeEnum{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

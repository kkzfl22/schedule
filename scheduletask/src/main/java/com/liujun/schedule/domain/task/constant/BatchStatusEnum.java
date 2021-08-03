package com.liujun.schedule.domain.task.constant;

/**
 * 批次的状态信息
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/11
 */
public enum BatchStatusEnum {


    /**
     * 禁用
     */
    DISABLE(0),


    /**
     * 启用
     */
    ENABLE(1),

    ;

    /**
     * 状态信息
     */
    private int status;

    BatchStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BatchStatusEnum{");
        sb.append("status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}

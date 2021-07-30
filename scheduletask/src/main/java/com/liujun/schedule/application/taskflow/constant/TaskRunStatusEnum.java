package com.liujun.schedule.application.taskflow.constant;

/**
 * 任务的运行状态信息
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/11
 */
public enum TaskRunStatusEnum {

    /**
     * 1:成功
     */
    SUCCESS(1),

    /**
     * 2:任务执行中
     */
    RUNNING(2),

    /**
     * 0:初始化状态
     */
    INIT(0),

    /**
     * -1：失败
     */
    FAIL(-1),
    ;

    /**
     * 状态信息
     */
    private int status;

    TaskRunStatusEnum(int status) {
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

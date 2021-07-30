package com.liujun.schedule.domain.task.constant;

/**
 * 批次的运行状态信息
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/11
 */
public enum BatchRunStatusEnum {


    /**
     * 0:初始化状态
     */
    INIT(0),

    /**
     * 2:任务执行中
     */
    RUNNING(1),


    /**
     * 2 完成，可能成功，可能失败
     */
    FINISH(2),

    ;

    /**
     * 状态信息
     */
    private int status;

    BatchRunStatusEnum(int status) {
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

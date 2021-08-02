package com.liujun.schedule.application.taskflow.retry;

/**
 * 重试次数对象信息
 *
 * @author liujun
 * @since 2021/8/1
 */
public class RetryNumData {

    /**
     * 重试第几次
     */
    private Integer numIndex;

    /**
     * 等待时间
     */
    private Integer waitTime;

    public RetryNumData(Integer numIndex, Integer waitTime) {
        this.numIndex = numIndex;
        this.waitTime = waitTime;
    }

    public Integer getNumIndex() {
        return numIndex;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RetryNumData{");
        sb.append("numIndex=").append(numIndex);
        sb.append(", waitTime=").append(waitTime);
        sb.append('}');
        return sb.toString();
    }
}

package com.liujun.schedule.application.taskflow.retry;

/**
 * 重试的执行结果数据
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/15
 */
public class RetryRsp {

    /**
     * 执行了重试的次数
     */
    private int retryNum;

    /**
     * 执行的结果
     */
    private Object result;

    public RetryRsp(int retryNum, Object result) {
        this.retryNum = retryNum;
        this.result = result;
    }

    public int getRetryNum() {
        return retryNum;
    }

    public Object getResult() {
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RetryRsp{");
        sb.append("retryNum=").append(retryNum);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}

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
     * 重试的执行结果
     */
    private Boolean result;

    /**
     * 执行的结果
     */
    private Object response;


    public RetryRsp(int retryNum, boolean result, Object response) {
        this.retryNum = retryNum;
        this.result = result;
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public int getRetryNum() {
        return retryNum;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RetryRsp{");
        sb.append("retryNum=").append(retryNum);
        sb.append(", result=").append(result);
        sb.append(", response=").append(response);
        sb.append('}');
        return sb.toString();
    }
}

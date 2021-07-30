package com.liujun.schedule.application.taskflow.retry;

import com.ddd.common.infrastructure.exception.BusiException;
import com.liujun.schedule.application.taskflow.constant.RunTaskConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 进行重试的方法的函数式编程
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/06/01
 */
public class RetryFunctionImpl {

    /**
     * 休眠的时间单位，秒
     */
    private static final long TIME_SECOND = 1000;

    /**
     * 休眠的时间
     */
    private static final long SLEEP_TIME = 2;

    public static final RetryFunctionImpl INSTANCE = new RetryFunctionImpl();

    private Logger logger = LoggerFactory.getLogger(RetryFunctionImpl.class);

    /**
     * 进行重试的逻辑操作
     *
     * @param reInstance
     * @param dataInput
     * @return
     * @throws Exception
     */
    public RetryRsp apply(RetryInf reInstance, Map<String, Object> dataInput, int retryMaxNum)
            throws BusiException {

        BusiException exception = null;

        if (retryMaxNum < 1) {
            retryMaxNum = 1;
        } else {
            retryMaxNum = retryMaxNum + 1;
        }

        // 进行重试操作
        for (int i = 0; i < retryMaxNum; i++) {
            try {
                dataInput.put(RunTaskConstant.TRY_NUMBER, i + 1);
                Object result = reInstance.retry(dataInput);

                RetryRsp rsp = new RetryRsp(i, result);

                return rsp;

            } catch (BusiException e) {
                e.printStackTrace();
                logger.error(
                        "RetryFunctionImpl data {} exception: num: {} maxretry {}",
                        dataInput,
                        i,
                        retryMaxNum,
                        e);
                exception = e;
            }

            try {
                Thread.sleep(SLEEP_TIME * TIME_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (null != exception) {
            throw exception;
        } else {
            throw new BusiException(
                    "retry " + retryMaxNum + ",object:" + reInstance + ",param " + dataInput + " exception ");
        }
    }

    /**
     * 进行重试的逻辑操作,看我重试不返回异常
     *
     * @param reInstance
     * @param dataInput
     * @return
     * @throws Exception
     */
    public RetryRsp applyRun(RetryInf reInstance, Map<String, Object> dataInput, int retryMaxNum) {
        RetryRsp rsp = null;
        try {
            rsp = this.apply(reInstance, dataInput, retryMaxNum);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("RetryFunctionImpl applyNext exception: maxretry {}", retryMaxNum, e);
        }

        if (null == rsp) {
            rsp = new RetryRsp(retryMaxNum, null);
        }

        return rsp;
    }
}

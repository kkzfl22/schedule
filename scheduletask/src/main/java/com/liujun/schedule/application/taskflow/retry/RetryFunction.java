package com.liujun.schedule.application.taskflow.retry;

import com.liujun.schedule.application.taskflow.constant.RunTaskConstant;
import com.liujun.schedule.infrastructure.constant.Symbol;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 进行重试的方法的函数式编程
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/06/01
 */
public class RetryFunction {

    /**
     * 休眠的时间单位，秒
     */
    private static final long TIME_SECOND = 1000;

    /**
     * 休眠的时间
     */
    private static final long SLEEP_TIME = 2;

    /**
     * 无限次重试
     */
    private static final String NOT_MAX = "-1";


    /**
     * 重试的实例
     */
    public static final RetryFunction INSTANCE = new RetryFunction();

    private Logger logger = LoggerFactory.getLogger(RetryFunction.class);

    /**
     * 进行重试的逻辑操作,重试带有异常返回
     *
     * @param reInstance 实例信息
     * @param param      参数信息
     * @param retryCfg   参数配制
     * @return 结果信息
     * @throws Exception
     */
    public RetryRsp apply(RetryInf reInstance, Map<String, Object> param, String retryCfg) {

        //默认为最小次数
        int runMax;
        //如果配制为无限重试
        if (NOT_MAX.equals(retryCfg)) {
            runMax = Integer.MAX_VALUE;
            return this.executeRetry(reInstance, runMax, param);
        }
        //如果为正整数，则直接配制
        else if (StringUtils.isNumeric(retryCfg)) {
            runMax = Integer.parseInt(retryCfg);
            return this.executeRetry(reInstance, runMax, param);
        }
        //分隔符的配制执行
        else if (retryCfg.contains(Symbol.SEMICOLON)) {
            List<RetryNumData> retryList = retryDataList(retryCfg);
            return this.executeRetry(reInstance, retryList, param);
        }

        return new RetryRsp(0, false, null);
    }

    /**
     * 配制加载
     *
     * @param retryCfg
     * @return
     */
    private List<RetryNumData> retryDataList(String retryCfg) {
        String[] retryNum = retryCfg.split(Symbol.SEMICOLON);
        if (null != retryNum && retryNum.length > 0) {
            List<RetryNumData> result = new ArrayList<>(retryNum.length);
            for (int i = 0; i < retryNum.length; i++) {
                String dataCfg = retryNum[i];
                result.add(new RetryNumData(i, Integer.parseInt(dataCfg)));
            }
            return result;
        }

        return Collections.emptyList();

    }


    /**
     * 执行重试操作
     *
     * @param reInstance 重试的实例对象
     * @param runMax     最大重试次数
     * @param param      参数信息
     * @return
     */
    private RetryRsp executeRetry(RetryInf reInstance, int runMax, Map<String, Object> param) {
        // 进行重试操作
        for (int i = 0; i < runMax; i++) {

            try {
                param.put(RunTaskConstant.TRY_NUMBER, i + 1);
                Object result = reInstance.reTry(param);
                //标识当前为成功
                return new RetryRsp(i, true, result);
            } catch (Exception e) {
                logger.error(
                        "RetryFunctionImpl data {} exception: num: {} maxretry {}",
                        param,
                        i,
                        runMax,
                        e);
            }

            try {
                Thread.sleep(SLEEP_TIME * TIME_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RetryRsp(runMax, false, null);
    }


    /**
     * 执行重试操作
     *
     * @param reInstance   重试的实例对象
     * @param retryNumList 重试的配制信息
     * @param param        参数信息
     * @return
     */
    private RetryRsp executeRetry(RetryInf reInstance, List<RetryNumData> retryNumList, Map<String, Object> param) {
        // 进行重试操作
        for (RetryNumData retryInfo : retryNumList) {

            try {
                param.put(RunTaskConstant.TRY_NUMBER, retryInfo.getNumIndex() + 1);
                Object result = reInstance.reTry(param);
                //标识当前为成功
                return new RetryRsp(retryInfo.getNumIndex(), true, result);
            } catch (Exception e) {
                logger.error(
                        "RetryFunctionImpl data {} exception: num: {} maxretry {}",
                        param,
                        retryInfo.getNumIndex(),
                        retryNumList.size(),
                        e);
            }

            try {
                Thread.sleep(retryInfo.getWaitTime() * TIME_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RetryRsp(retryNumList.size(), false, null);
    }


}

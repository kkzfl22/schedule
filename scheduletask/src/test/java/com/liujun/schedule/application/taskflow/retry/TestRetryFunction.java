package com.liujun.schedule.application.taskflow.retry;

import com.ddd.common.infrastructure.constant.ErrorCodeEnum;
import com.ddd.common.infrastructure.exception.BusinessException;
import com.liujun.schedule.application.taskflow.constant.RunTaskConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试重试
 *
 * @author liujun
 * @since 2021/8/1
 */
public class TestRetryFunction {


    Object runTask(Map<String, Object> input) throws BusinessException {
        System.out.println("Run:" + input);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        throw new BusinessException(ErrorCodeEnum.ERROR);
    }

    Object runTaskSuccess(Map<String, Object> input) throws BusinessException {
        Integer value = (int) input.get(RunTaskConstant.TRY_NUMBER);

        if (value < 2) {
            System.out.println("Run:" + input);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            throw new BusinessException(ErrorCodeEnum.ERROR);
        } else {
            return true;
        }
    }

    @Test
    public void runTask() {
        try {
            Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
                        RetryFunction.INSTANCE.apply(this::runTask, new HashMap<>(2), "-1");
                    }
            );

            Assertions.assertTrue(false);
        } catch (Error e) {
            e.printStackTrace();
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void runTaskNum() {
        RetryRsp rsp = RetryFunction.INSTANCE.apply(this::runTask, new HashMap<>(2), "1");
        Assertions.assertEquals(rsp.getResult(), false);
        Assertions.assertEquals(rsp.getRetryNum(), 1);
    }

    @Test
    public void runTaskNumSuccess() {
        RetryRsp rsp = RetryFunction.INSTANCE.apply(this::runTaskSuccess, new HashMap<>(2), "2");
        Assertions.assertEquals(rsp.getResult(), true);
        Assertions.assertEquals(rsp.getRetryNum(), 1);
    }


    @Test
    public void runTaskCfg() {
        RetryRsp rsp = RetryFunction.INSTANCE.apply(this::runTask, new HashMap<>(2), "1;1");
        Assertions.assertEquals(rsp.getResult(), false);
        Assertions.assertEquals(rsp.getRetryNum(), 2);
    }


    @Test
    public void runTaskCfgNot() {
        RetryRsp rsp = RetryFunction.INSTANCE.apply(this::runTask, new HashMap<>(2), "1,1");
        Assertions.assertEquals(rsp.getResult(), false);
        Assertions.assertEquals(rsp.getRetryNum(), 0);
    }

}

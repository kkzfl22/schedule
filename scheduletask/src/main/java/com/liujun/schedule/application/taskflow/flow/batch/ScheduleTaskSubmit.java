package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.application.taskflow.flow.task.ThreadTaskRunFlow;
import com.liujun.schedule.application.taskflow.thread.EtlScheduleTaskThreadPool;
import com.liujun.schedule.application.taskflow.thread.RunTaskThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

/**
 * 提交任务至线程池中运行
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/11
 */
@Service
public class ScheduleTaskSubmit implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(ScheduleTaskSubmit.class);

    /**
     * 通过xml配制的文件流程进行注入
     */
    @Autowired
    private ThreadTaskRunFlow taskRunFlow;

    @Override
    public boolean invokeFlow(ContextContainer context) {

        logger.info("builder container start ");

        Long batchId = context.getObject(BatchFLowEnum.INPUT_BATCH_ID.name());
        Long runTimeFlag = context.getObject(BatchFLowEnum.PROC_RUNTIME_FLAG.name());

        // 将初始化要启的顶点找出来
        List<Long> firstInVertex =
                context.getObject(BatchFLowEnum.PROC_DATA_FIRST_IN_VERTEX.name());

        int concurrent = 4;
        /** 将首批任务提交线程池 */
        for (Long vertexData : firstInVertex) {
            RunTaskThread runTask = new RunTaskThread(batchId, vertexData, runTimeFlag, taskRunFlow, concurrent);

            boolean loopFlag = true;
            while (loopFlag) {
                // 当执行过一次后就改为false，不再执行
                loopFlag = false;
                try {
                    // 将任务提交线程池
                    EtlScheduleTaskThreadPool.INSTANCE.submit(runTask);
                } catch (RejectedExecutionException e) {
                    logger.info(
                            "ScheduleTaskSubmit run sqlmap.sqlmap.job {} RejectedExecutionException retry submit pool",
                            runTask,
                            e);
                    e.printStackTrace();
                    // 当线程池提交不成功，则再次等待然后执行
                    loopFlag = true;
                }

                // 每次提交不了，等待30秒后重试
                // todo 重试的时间可配
                if (loopFlag) {
                    try {
                        Thread.sleep(30 * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        logger.info("builder container finish ");

        return true;
    }
}

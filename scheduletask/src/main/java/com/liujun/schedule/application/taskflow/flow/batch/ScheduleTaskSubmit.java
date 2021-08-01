package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.application.taskflow.container.TaskContainerMap;
import com.liujun.schedule.application.taskflow.flow.task.ThreadTaskRunFlow;
import com.liujun.schedule.application.taskflow.graph.ContainData;
import com.liujun.schedule.application.taskflow.thread.EtlScheduleTaskThreadPool;
import com.liujun.schedule.application.taskflow.thread.RunTaskThread;
import com.liujun.schedule.application.taskflow.thread.SubmitTask;
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
@Service("scheduleTaskSubmit")
public class ScheduleTaskSubmit implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(ScheduleTaskSubmit.class);


    @Override
    public boolean invokeFlow(ContextContainer context) {

        logger.info("builder container start ");

        Long batchId = context.getObject(BatchFLowEnum.INPUT_BATCH_ID.name());
        Long runTimeFlag = context.getObject(BatchFLowEnum.PROC_RUNTIME_FLAG.name());

        // 将初始化要启的顶点找出来
        List<Long> firstInVertex =
                context.getObject(BatchFLowEnum.PROC_DATA_FIRST_IN_VERTEX.name());


        /** 将首批任务提交线程池 */
        for (Long vertexData : firstInVertex) {
            SubmitTask.INSTANCE.submit(batchId, vertexData, runTimeFlag);
        }

        logger.info("builder container finish ");

        return true;
    }


}

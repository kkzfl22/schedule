package com.liujun.schedule.application.taskflow.thread;

import com.liujun.schedule.application.taskflow.container.TaskContainerMap;
import com.liujun.schedule.application.taskflow.flow.task.ThreadTaskRunFlow;
import com.liujun.schedule.application.taskflow.graph.ContainData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;

/**
 * 任务提交操作
 *
 * @author liujun
 * @since 2021/8/1
 */
public class SubmitTask {

    private SubmitTask() {
    }

    private Logger logger = LoggerFactory.getLogger(SubmitTask.class);


    /**
     * 实例
     */
    public static final SubmitTask INSTANCE = new SubmitTask();

    /**
     * 提交任务至线程池中运行
     *
     * @param batchId     批次号
     * @param vertexData  运行的顶点
     * @param runTimeFlag 运行标识
     */
    public void submit(long batchId, Long vertexData, Long runTimeFlag) {
        //获取当前的容器对象
        ContainData contain = TaskContainerMap.INSTANCE.get(batchId);
        boolean acquireFlag = false;
        try {
            contain.getConcurrent().acquire();
            acquireFlag = true;
            //执行提交线程
            this.submitThread(batchId, vertexData, runTimeFlag, contain.getDoTaskFlow());
        } catch (InterruptedException e) {
            logger.error(
                    "ScheduleTaskSubmit run task job {} RejectedExecutionException retry submit pool",
                    e);
        } finally {
            if (acquireFlag) {
                contain.getConcurrent().release();
            }
        }
    }


    /**
     * 提交线程操作
     *
     * @param batchId     批次号
     * @param vertexData  当前的顶点的id,也是taskId
     * @param runTimeFlag 运行标识
     */
    private void submitThread(long batchId, Long vertexData, Long runTimeFlag, ThreadTaskRunFlow taskRunFlow) {
        RunTaskThread runTask = new RunTaskThread(batchId, vertexData, runTimeFlag, taskRunFlow);
        boolean loopFlag = true;
        while (loopFlag) {
            // 当执行过一次后就改为false，不再执行
            loopFlag = false;
            try {
                // 将任务提交线程池
                EtlScheduleTaskThreadPool.INSTANCE.submit(runTask);
            } catch (RejectedExecutionException e) {
                logger.info(
                        "ScheduleTaskSubmit run task job {} RejectedExecutionException retry submit pool",
                        runTask,
                        e);
                // 当线程池提交不成功，则再次等待然后执行
                loopFlag = true;
            }

            // 每次提交不了，等待5秒后重新提交线程池
            if (loopFlag) {
                try {
                    Thread.sleep(5 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

package com.liujun.schedule.application.taskflow.thread;

import com.liujun.schedule.application.taskflow.flow.task.ThreadTaskRunFlow;

import java.util.concurrent.Semaphore;

/**
 * 运行任务线程
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
public class RunTaskThread implements Runnable {

    /**
     * 批次号的id
     */
    private final Long batchId;

    /**
     * 任务的id
     */
    private final Long taskId;

    /**
     * 运行时流程标识
     */
    private final long runTimeFlag;

    /**
     * 运行线程的任务
     */
    private final ThreadTaskRunFlow threadTaskRun;


    /**
     * 创建一个线程运行器，
     *
     * @param batchId       批量号
     * @param taskId        任务的id
     * @param runTimeFlag   任务运行号
     * @param threadTaskRun 运行流程
     */
    public RunTaskThread(
            Long batchId, Long taskId, long runTimeFlag, ThreadTaskRunFlow threadTaskRun) {
        this.batchId = batchId;
        this.taskId = taskId;
        this.runTimeFlag = runTimeFlag;
        this.threadTaskRun = threadTaskRun;
    }


    @Override
    public void run() {
        // 进行任务的执行操作
        threadTaskRun.runThread(batchId, taskId, runTimeFlag);

    }
}

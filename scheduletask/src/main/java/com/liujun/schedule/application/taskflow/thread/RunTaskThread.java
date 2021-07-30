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
     * 并行控制，主要是防止一个任务占用了全部资源池，而其他任务完全用不到线程池
     */
    private final Semaphore concurrent;


    /**
     * 创建一个线程运行器，
     *
     * @param batchId       批量号
     * @param taskId        任务的id
     * @param runTimeFlag   任务运行号
     * @param threadTaskRun 运行流程
     * @param concurrent    线程池中当前任务的最大并行度
     */
    public RunTaskThread(
            Long batchId, Long taskId, long runTimeFlag, ThreadTaskRunFlow threadTaskRun, int concurrent) {
        this.batchId = batchId;
        this.taskId = taskId;
        this.runTimeFlag = runTimeFlag;
        this.threadTaskRun = threadTaskRun;
        this.concurrent = new Semaphore(concurrent);
    }


    @Override
    public void run() {

        boolean currRun = false;
        try {
            concurrent.acquire();
            currRun = true;
            // 进行任务的执行操作
            threadTaskRun.runThread(batchId, taskId, runTimeFlag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //当使用完毕后，则对资源进行释放
            if (currRun) {
                concurrent.release();
            }
        }


    }
}

package com.liujun.schedule.application.taskflow.thread;

import com.liujun.schedule.application.taskflow.flow.batch.BatchRunFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 批次任务的执行操作
 *
 * @author liujun
 * @version 0.0.1
 * @since 2020/02/17
 */
public class BatchTaskRunnable implements Runnable {

    /**
     * 当前待执行的批次号
     */
    private long batchId;

    /**
     * 运行批次任务的具体的业务流程
     */
    private BatchRunFlow batchRunTask;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(BatchTaskRunnable.class);

    public BatchTaskRunnable(long batchId, BatchRunFlow batchRunTask) {
        this.batchId = batchId;
        this.batchRunTask = batchRunTask;
    }

    @Override
    public void run() {
        logger.info("batch run sqlmap.sqlmap.job start info batch object {} batchId {}", batchRunTask, batchId);
        batchRunTask.runBatch(batchId);
        logger.info("batch run sqlmap.sqlmap.job finish info batch object {} batchId {}", batchRunTask, batchId);
    }
}

package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 进行批次的运行流程操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/14
 */
public class BatchRunFlow {


    private Logger logger = LoggerFactory.getLogger(BatchRunFlow.class);

    /**
     * 任务的运行流程
     */
    private List<FlowInf> batchFlow;

    /**
     * 进行批次下的任务初始调用流程
     *
     * @param batchId
     */
    public void runBatch(Long batchId) {

        logger.info("thread run batch  batchid {} start ", batchId);

        ContextContainer context = new ContextContainer();

        context.put(BatchFLowEnum.INPUT_BATCH_ID.name(), batchId);

        boolean success = true;

        for (FlowInf flow : batchFlow) {
            if (!flow.invokeFlow(context)) {
                success = false;
                break;
            }
        }

        logger.info("thread run task job batchid {}  rsp {}", batchId, success);
    }

    public void setBatchFlow(List<FlowInf> batchFlow) {
        this.batchFlow = batchFlow;
    }
}

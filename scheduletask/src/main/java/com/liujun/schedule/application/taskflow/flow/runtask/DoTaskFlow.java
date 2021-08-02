package com.liujun.schedule.application.taskflow.flow.runtask;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.task.task.entity.DcTaskInfoDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 任务具代的执行的流程
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/14
 */
public class DoTaskFlow {

    private Logger logger = LoggerFactory.getLogger(DoTaskFlow.class);

    /**
     * 任务的运行流程
     */
    private List<FlowInf> doTaskFlow;

    /**
     * @param batchId     批次号
     * @param runTimeFlag 运行任务标识
     * @param taskInfo    任务信息
     * @param runNum      当前运行的次数
     * @return
     */
    public boolean runThread(Long batchId, Long runTimeFlag, DcTaskInfoDO taskInfo, int runNum) {

        logger.info("thread run task job batchId {} taskId {} runFlag {}", batchId, taskInfo.getTaskId(), runTimeFlag);
        ContextContainer context = new ContextContainer();

        context.put(ThreadTaskEnum.INPUT_TASK_ID.name(), taskInfo.getTaskId());
        context.put(ThreadTaskEnum.INPUT_BATCH_ID.name(), batchId);
        context.put(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name(), runTimeFlag);
        context.put(ThreadTaskEnum.PROC_INPUT_TASK_INFO.name(), taskInfo);
        context.put(ThreadTaskEnum.PROC_INPUT_RUN_NUM.name(), runNum);

        boolean success = Boolean.TRUE;
        for (FlowInf flow : doTaskFlow) {
            if (!flow.invokeFlow(context)) {
                success = false;
                break;
            }
        }

        context = null;

        logger.info(
                "thread run task job batchid {} taskId {} runflag {} rsp {}",
                batchId,
                taskInfo.getTaskId(),
                runTimeFlag,
                success);

        //如果被成功执行，则返回true,否则返回false
        if (success) {
            return true;
        }

        return false;

    }

    public void setDoTaskFlow(List<FlowInf> doTaskFlow) {
        this.doTaskFlow = doTaskFlow;
    }
}

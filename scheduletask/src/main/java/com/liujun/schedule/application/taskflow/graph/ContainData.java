package com.liujun.schedule.application.taskflow.graph;

import com.liujun.schedule.application.taskflow.flow.task.ThreadTaskRunFlow;
import com.liujun.task.task.entity.DcBatchInfoDO;
import com.liujun.task.task.entity.DcTaskInfoDO;
import com.liujun.task.task.entity.DcTaskTypeDO;

import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * 用于构建任务计算所依赖的内存数据
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
public class ContainData {

    /**
     * 当前的批次号
     */
    private final Long batchId;

    /**
     * 任务的依赖关系
     */
    private final Map<Long, GraphPointData> graphTaskPointMap;

    /**
     * 任务信息
     */
    private final Map<Long, DcTaskInfoDO> taskMap;

    /**
     * 任务运行时的类型信息
     */
    private final Map<String, DcTaskTypeDO> taskTypeData;


    /**
     * 批次信息
     */
    private final DcBatchInfoDO batchInfo;


    /**
     * 执行任务流程
     */
    private final ThreadTaskRunFlow doTaskFlow;

    /**
     * 用于控制并行
     */
    private final Semaphore concurrent;


    public ContainData(
            Map<Long, DcTaskInfoDO> taskMap,
            Map<Long, GraphPointData> graphTaskPointMap,
            Map<String, DcTaskTypeDO> taskTypeData,
            DcBatchInfoDO batchInfo,
            ThreadTaskRunFlow doTaskFlow
    ) {
        this.batchId = batchInfo.getBatchId();
        this.taskMap = taskMap;
        this.graphTaskPointMap = graphTaskPointMap;
        this.taskTypeData = taskTypeData;
        this.batchInfo = batchInfo;
        this.doTaskFlow = doTaskFlow;
        //初始化并行度
        this.concurrent = new Semaphore(batchInfo.getBatchConcurrent());
    }

    public Long getBatchId() {
        return batchId;
    }

    public Map<Long, GraphPointData> getGraphTaskPointMap() {
        return graphTaskPointMap;
    }

    public Map<String, DcTaskTypeDO> getTaskTypeData() {
        return taskTypeData;
    }

    public Map<Long, DcTaskInfoDO> getTaskMap() {
        return taskMap;
    }

    public DcBatchInfoDO getBatchInfo() {
        return batchInfo;
    }

    public Semaphore getConcurrent() {
        return concurrent;
    }

    public ThreadTaskRunFlow getDoTaskFlow() {
        return doTaskFlow;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContainData{");
        sb.append("batchId=").append(batchId);
        sb.append(", graphTaskPointMap=").append(graphTaskPointMap);
        sb.append(", scheduleTaskMap=").append(taskMap);
        sb.append(", taskTypeData=").append(taskTypeData);
        sb.append(", batchInfo=").append(batchInfo);
        sb.append('}');
        return sb.toString();
    }
}

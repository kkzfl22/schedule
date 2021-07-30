package com.liujun.schedule.application.taskflow.graph;

import com.liujun.schedule.application.taskflow.constant.TaskCategoryEnum;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDO;
import com.liujun.schedule.domain.task.entity.DcTaskInfoDO;
import com.liujun.schedule.domain.task.entity.DcTaskTypeDO;

import java.util.Map;

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
    private final Map<Long, DcTaskInfoDO> scheduleTaskMap;

    /**
     * 任务运行时的类型信息
     */
    private final Map<String, DcTaskTypeDO> taskTypeData;


    public ContainData(
            Long batchId,
            Map<Long, DcTaskInfoDO> scheduleTaskMap,
            Map<Long, GraphPointData> graphTaskPointMap,
            Map<String, DcTaskTypeDO> taskTypeData) {
        this.batchId = batchId;
        this.scheduleTaskMap = scheduleTaskMap;
        this.graphTaskPointMap = graphTaskPointMap;
        this.taskTypeData = taskTypeData;
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

    public Map<Long, DcTaskInfoDO> getScheduleTaskMap() {
        return scheduleTaskMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContainData{");
        sb.append("batchId=").append(batchId);
        sb.append(", graphTaskPointMap=").append(graphTaskPointMap);
        sb.append(", taskTypeData=").append(taskTypeData);
        sb.append('}');
        return sb.toString();
    }
}

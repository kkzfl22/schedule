package com.liujun.schedule.domain.task.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 批次中任务依赖关系-的领域实体信息
 *
 * @version 0.0.1
 * @author liujun
 */
@Getter
@Setter
@ToString
public class DcBatchTaskDependDO {


    /**
     * 批次的ID
     */
    private Long batchId;

    /**
     * 调度任务信息表(DC_TASK_INFO)中的任务的ID
     */
    private Long taskId;

    /**
     * 依赖的任务的ID
     */
    private Long prevTaskId;

}
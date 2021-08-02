package com.liujun.schedule.infrastructure.repository.task.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 批次任务信息表(dc_batch_task)的数据库存储实体信息
 *
 * @version 0.0.1
 * @author liujun
 */
@Getter
@Setter
@ToString
public class DcBatchTaskPO {


    /**
     * 批次的ID
     */
    private Long batchId;

    /**
     * 调度任务信息表(DC_TASK_INFO)中的任务的ID
     */
    private Long taskId;

}
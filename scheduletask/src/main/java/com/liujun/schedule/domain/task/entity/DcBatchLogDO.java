package com.liujun.schedule.domain.task.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 批次的日志批次状态-的领域实体信息
 *
 * @version 0.0.1
 * @author liujun
 */
@Getter
@Setter
@ToString
public class DcBatchLogDO {


    /**
     * 日志ID
     */
    private Long logId;

    /**
     * 批次号
     */
    private Long batchId;

    /**
     * 任务的名称
     */
    private String batchName;

    /**
     * 批次中任务最大并行度，防止一个批次占用过多的线程
     */
    private Integer batchConcurrent;

    /**
     * 任务的状态:, 1:成功, 2:任务执行中, 0:初始化状态 -1：失败
     */
    private Integer batchStatus;

    /**
     * 任务日志，成功为空，失败时记录下失败信息
     */
    private String batchMsg;

    /**
     * 批次的开始时间
     */
    private Long batchStartTime;

    /**
     * 批次的结束时间
     */
    private Long batchFinishTime;

    /**
     * 每个批次执行时的标识
     */
    private Long taskRuntimeFlag;

}
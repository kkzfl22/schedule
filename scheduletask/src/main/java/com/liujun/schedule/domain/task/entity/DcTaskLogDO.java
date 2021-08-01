package com.liujun.schedule.domain.task.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 任务的日志信息-的领域实体信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class DcTaskLogDO {


    /**
     * 日志ID
     */
    private Long logId;

    /**
     * 批次号
     */
    private Long batchId;

    /**
     * 任务的ID
     */
    private Long taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务的状态:, 1:成功, 2:任务执行中, -1：失败
     */
    private Integer taskStatus;

    /**
     * 任务的运行次数次数, 从1开始，任务最少执行一次
     */
    private Integer taskRunNum;

    /**
     * 任务的的配制
     */
    private String taskCfg;

    /**
     * 日志信息
     */
    private String taskMsg;

    /**
     * 任务的开始时间
     */
    private Long taskStartTime;

    /**
     * 任务的结束时间
     */
    private Long taskFinishTime;

    /**
     * 当前任务在任务依赖链中的顺序
     */
    private Integer taskOrder;

    /**
     * 每次执行任务时的一个标识
     */
    private Long taskRuntimeFlag;


    /**
     * 任务之前的的状态
     */
    private Integer beforeStatus;

}
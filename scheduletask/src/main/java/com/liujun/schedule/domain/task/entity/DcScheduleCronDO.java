package com.liujun.schedule.domain.task.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 调度的CRON-达式信息-的领域实体信息
 *
 * @version 0.0.1
 * @author liujun
 */
@Getter
@Setter
@ToString
public class DcScheduleCronDO {


    /**
     * 使用算法生成
     */
    private Long taskId;

    /**
     * 当前任务的类型, 1:按任务调度,即, DC_TASK中的任务ID, 2:按批次调用，即DC_BATCH_INFO中的批次的ID
     */
    private Integer taskType;

    /**
     * 类型(月:MONTH,周:WEEK,日:DAY)
     */
    private String scheduleType;

    /**
     * 值(月:1,2,3 周:MON,SUN)
     */
    private String scheduleValue;

    /**
     * CRON的表达式
     */
    private String scheduleCron;

    /**
     * UI显示和配置的时间
     */
    private String uiTime;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 更新者
     */
    private String updater;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
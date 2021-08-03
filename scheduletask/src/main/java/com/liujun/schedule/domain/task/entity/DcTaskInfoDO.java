package com.liujun.schedule.domain.task.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调度任务信息-的领域实体信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class DcTaskInfoDO {


    /**
     * 任务的ID,
     */
    private Long taskId;

    /**
     * 目录的id
     */
    private Long taskDirId;

    /**
     * 任务的名称
     */
    private String taskName;

    /**
     * 任务的类型:关联DC_TASK_TYPE表
     */
    private String taskType;

    /**
     * 任务的状态, 1:正常状态,0，停用状态
     */
    private Integer status;

    /**
     * 任务的的配制
     */
    private String taskCfg;


    /**
     * 重试配制:-1,无限重试;5,15,30.执行后5秒重试，以此类推,成功则不再重试。
     */
    private String taskRetry;

    /**
     * 任务的描述
     */
    private String taskMsg;

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


    /**
     * 任务的集合
     */
    private List<Long> taskList;
}
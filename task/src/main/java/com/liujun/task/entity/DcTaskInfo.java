package com.liujun.task.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * 调度任务信息-的领域实体信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Builder
@Getter
@ToString
public class DcTaskInfo {


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
     * 将配制转换的配制的map
     */
    private Map<String, String> cfgMap;

    /**
     * 重试配制:-1,无限重试;5,15,30.执行后5秒重试，以此类推,成功则不再重试。
     */
    private String taskRetry;

    /**
     * 任务的描述
     */
    private String taskMsg;


}
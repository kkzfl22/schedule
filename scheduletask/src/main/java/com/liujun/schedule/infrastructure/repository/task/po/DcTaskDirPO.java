package com.liujun.schedule.infrastructure.repository.task.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 任务目录信息表(dc_task_dir)的数据库存储实体信息
 *
 * @version 0.0.1
 * @author liujun
 */
@Getter
@Setter
@ToString
public class DcTaskDirPO {


    /**
     * 任务目录的ID
     */
    private Long taskDirId;

    /**
     * 父任务目录的ID
     */
    private Long parentDirId;

    /**
     * 任务目录名称
     */
    private String taskDirName;

    /**
     * 任务目录描述
     */
    private String taskDirDescription;

    /**
     * 排序号
     */
    private Integer taskOrder;

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
package com.liujun.schedule.infrastructure.repository.task.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 批次信息表(dc_batch_info)的数据库存储实体信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class DcBatchInfoPO {


    /**
     * 批次的ID
     */
    private Long batchId;

    /**
     * 所属任务目录ID
     */
    private Long taskDirId;

    /**
     * 批次的任务名称
     */
    private String batchName;

    /**
     * 批次任务的描述信息
     */
    private String batchMsg;

    /**
     * 批次任务的状态，1，启用，0，停用
     */
    private Integer batchStatus;

    /**
     * 批次的运行状态,0,初始化，1：运行中，2，运行完成
     */
    private Integer batchRunStatus;

    /**
     * 每个批次执行时的标识
     */
    private Long taskRuntimeFlag;

    /**
     * 批次中任务最大并行度，防止一个批次占用过多的线程
     */
    private Integer batchConcurrent;

    /**
     * 用于修改批次时，确定之前的状态
     */
    private Integer batchRunStatusBefore;


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
     * 批次的ID集合
     */
    private List<Long> batchList;

}
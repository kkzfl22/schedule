package com.liujun.task.task.entity;

import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.task.task.constant.BatchRunStatusEnum;
import com.liujun.task.task.constant.BatchStatusEnum;
import com.liujun.schedule.infrastructure.comm.uid.UidGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 批次信息-的领域实体信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
public class DcBatchInfoDO {


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


    public void batchAdd(UidGenerator uid) {
        //生成当前的id
        this.batchId = uid.getUid();
        //如果初始化未填写状态，则默认为init状态
        if (null == batchStatus) {
            //状态标识为运行状态
            this.batchRunStatus = BatchRunStatusEnum.INIT.getStatus();
        }
        //禁用状态
        this.batchStatus = BatchStatusEnum.ENABLE.getStatus();
        //创建时间
        this.createTime = LocalDateTimeUtils.getDatabaseLocalDateTime();
    }


}
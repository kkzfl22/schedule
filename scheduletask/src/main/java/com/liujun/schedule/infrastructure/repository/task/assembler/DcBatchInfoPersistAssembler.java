package com.liujun.schedule.infrastructure.repository.task.assembler;

import com.liujun.task.task.entity.DcBatchInfoDO;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchInfoPO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 领域实体与存储实体的转换
 *
 * @author liujun
 * @version 0.0.1
 */
public class DcBatchInfoPersistAssembler {


    /**
     * 将领域对象转换为存储对象
     *
     * @param src 批次信息-的领域实体信息
     * @return 批次信息表(dc_batch_info)的数据库存储实体信息
     */
    public static DcBatchInfoPO toPersistObject(DcBatchInfoDO src) {
        if (null == src) {
            return null;
        }
        DcBatchInfoPO target = new DcBatchInfoPO();
        //批次的ID
        target.setBatchId(src.getBatchId());
        //所属任务目录ID
        target.setTaskDirId(src.getTaskDirId());
        //批次的任务名称
        target.setBatchName(src.getBatchName());
        //批次任务的描述信息
        target.setBatchMsg(src.getBatchMsg());
        //批次任务的状态，1，启用，0，停用
        target.setBatchStatus(src.getBatchStatus());
        //批次的运行状态,0,初始化，1：运行中，2，运行完成
        target.setBatchRunStatus(src.getBatchRunStatus());
        //每个批次执行时的标识
        target.setTaskRuntimeFlag(src.getTaskRuntimeFlag());
        //批次中任务最大并行度，防止一个批次占用过多的线程
        target.setBatchConcurrent(src.getBatchConcurrent());
        //创建者
        target.setCreator(src.getCreator());
        //更新者
        target.setUpdater(src.getUpdater());
        //创建时间
        target.setCreateTime(src.getCreateTime());
        //更新时间
        target.setUpdateTime(src.getUpdateTime());
        //之前的状态设置
        target.setBatchRunStatusBefore(src.getBatchRunStatusBefore());
        //批次操作的集合
        target.setBatchList(src.getBatchList());
        return target;
    }

    /**
     * 将存储对象转换为领域对象
     *
     * @param src 批次信息表(dc_batch_info)的数据库存储实体信息
     * @return 批次信息-的领域实体信息
     */
    public static DcBatchInfoDO toDomainEntity(DcBatchInfoPO src) {
        if (null == src) {
            return null;
        }
        DcBatchInfoDO target = new DcBatchInfoDO();
        //批次的ID
        target.setBatchId(src.getBatchId());
        //所属任务目录ID
        target.setTaskDirId(src.getTaskDirId());
        //批次的任务名称
        target.setBatchName(src.getBatchName());
        //批次任务的描述信息
        target.setBatchMsg(src.getBatchMsg());
        //批次任务的状态，1，启用，0，停用
        target.setBatchStatus(src.getBatchStatus());
        //批次的运行状态,0,初始化，1：运行中，2，运行完成
        target.setBatchRunStatus(src.getBatchRunStatus());
        //每个批次执行时的标识
        target.setTaskRuntimeFlag(src.getTaskRuntimeFlag());
        //批次中任务最大并行度，防止一个批次占用过多的线程
        target.setBatchConcurrent(src.getBatchConcurrent());
        //创建者
        target.setCreator(src.getCreator());
        //更新者
        target.setUpdater(src.getUpdater());
        //创建时间
        target.setCreateTime(src.getCreateTime());
        //更新时间
        target.setUpdateTime(src.getUpdateTime());
        return target;
    }

    /**
     * 将领域集合对象转换为存储集合对象
     *
     * @param srcList 批次信息-的领域实体信息
     * @return 批次信息表(dc_batch_info)的数据库存储实体信息
     */
    public static List<DcBatchInfoPO> toListPersistObject(List<DcBatchInfoDO> srcList) {
        if (srcList == null || srcList.isEmpty()) {
            return Collections.emptyList();
        }

        List<DcBatchInfoPO> targetList = new ArrayList<>(srcList.size());
        for (DcBatchInfoDO dataItemTmp : srcList) {
            targetList.add(toPersistObject(dataItemTmp));
        }
        return targetList;
    }

    /**
     * 将存储集合对象转换为领域集合对象
     *
     * @param srcList 批次信息表(dc_batch_info)的数据库存储实体信息
     * @return 批次信息-的领域实体信息
     */
    public static List<DcBatchInfoDO> toListDomainEntity(List<DcBatchInfoPO> srcList) {
        if (srcList == null || srcList.isEmpty()) {
            return Collections.emptyList();
        }

        List<DcBatchInfoDO> targetList = new ArrayList<>(srcList.size());
        for (DcBatchInfoPO dataItemTmp : srcList) {
            targetList.add(toDomainEntity(dataItemTmp));
        }
        return targetList;
    }

}
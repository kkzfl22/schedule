package com.liujun.schedule.infrastructure.repository.task.assembler;

import com.liujun.task.task.entity.DcBatchTaskDO;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchTaskPO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 领域实体与存储实体的转换
 *
 * @version 0.0.1
 * @author liujun
 */
public class DcBatchTaskPersistAssembler {


    /**
     * 将领域对象转换为存储对象
     *
     * @param src 批次任务信息-的领域实体信息
     * @return 批次任务信息表(dc_batch_task)的数据库存储实体信息
     */
    public static DcBatchTaskPO toPersistObject(DcBatchTaskDO src){
        if  (null == src){
            return null;
        }
        DcBatchTaskPO target = new DcBatchTaskPO();
        //批次的ID
        target.setBatchId(src.getBatchId());
        //调度任务信息表(DC_TASK_INFO)中的任务的ID
        target.setTaskId(src.getTaskId());
        return target;
    }

    /**
     * 将存储对象转换为领域对象
     *
     * @param src 批次任务信息表(dc_batch_task)的数据库存储实体信息
     * @return 批次任务信息-的领域实体信息
     */
    public static DcBatchTaskDO toDomainEntity(DcBatchTaskPO src){
        if  (null == src){
            return null;
        }
        DcBatchTaskDO target = new DcBatchTaskDO();
        //批次的ID
        target.setBatchId(src.getBatchId());
        //调度任务信息表(DC_TASK_INFO)中的任务的ID
        target.setTaskId(src.getTaskId());
        return target;
    }

    /**
     * 将领域集合对象转换为存储集合对象
     *
     * @param srcList 批次任务信息-的领域实体信息
     * @return 批次任务信息表(dc_batch_task)的数据库存储实体信息
     */
    public static List<DcBatchTaskPO> toListPersistObject(List<DcBatchTaskDO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcBatchTaskPO> targetList = new ArrayList<>(srcList.size());
        for (DcBatchTaskDO dataItemTmp : srcList){
            targetList.add(toPersistObject(dataItemTmp));
        }
        return targetList;
    }

    /**
     * 将存储集合对象转换为领域集合对象
     *
     * @param srcList 批次任务信息表(dc_batch_task)的数据库存储实体信息
     * @return 批次任务信息-的领域实体信息
     */
    public static List<DcBatchTaskDO> toListDomainEntity(List<DcBatchTaskPO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcBatchTaskDO> targetList = new ArrayList<>(srcList.size());
        for (DcBatchTaskPO dataItemTmp : srcList){
            targetList.add(toDomainEntity(dataItemTmp));
        }
        return targetList;
    }

}
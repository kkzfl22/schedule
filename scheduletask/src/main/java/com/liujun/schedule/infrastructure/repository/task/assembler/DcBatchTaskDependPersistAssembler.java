package com.liujun.schedule.infrastructure.repository.task.assembler;

import com.liujun.schedule.domain.task.entity.DcBatchTaskDependDO;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchTaskDependPO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 领域实体与存储实体的转换
 *
 * @version 0.0.1
 * @author liujun
 */
public class DcBatchTaskDependPersistAssembler {


    /**
     * 将领域对象转换为存储对象
     *
     * @param src 批次中任务依赖关系-的领域实体信息
     * @return 批次中任务依赖关系表(dc_batch_task_depend)的数据库存储实体信息
     */
    public static DcBatchTaskDependPO toPersistObject(DcBatchTaskDependDO src){
        if  (null == src){
            return null;
        }
        DcBatchTaskDependPO target = new DcBatchTaskDependPO();
        //批次的ID
        target.setBatchId(src.getBatchId());
        //调度任务信息表(DC_TASK_INFO)中的任务的ID
        target.setTaskId(src.getTaskId());
        //依赖的任务的ID
        target.setPrevTaskId(src.getPrevTaskId());
        return target;
    }

    /**
     * 将存储对象转换为领域对象
     *
     * @param src 批次中任务依赖关系表(dc_batch_task_depend)的数据库存储实体信息
     * @return 批次中任务依赖关系-的领域实体信息
     */
    public static DcBatchTaskDependDO toDomainEntity(DcBatchTaskDependPO src){
        if  (null == src){
            return null;
        }
        DcBatchTaskDependDO target = new DcBatchTaskDependDO();
        //批次的ID
        target.setBatchId(src.getBatchId());
        //调度任务信息表(DC_TASK_INFO)中的任务的ID
        target.setTaskId(src.getTaskId());
        //依赖的任务的ID
        target.setPrevTaskId(src.getPrevTaskId());
        return target;
    }

    /**
     * 将领域集合对象转换为存储集合对象
     *
     * @param srcList 批次中任务依赖关系-的领域实体信息
     * @return 批次中任务依赖关系表(dc_batch_task_depend)的数据库存储实体信息
     */
    public static List<DcBatchTaskDependPO> toListPersistObject(List<DcBatchTaskDependDO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcBatchTaskDependPO> targetList = new ArrayList<>(srcList.size());
        for (DcBatchTaskDependDO dataItemTmp : srcList){
            targetList.add(toPersistObject(dataItemTmp));
        }
        return targetList;
    }

    /**
     * 将存储集合对象转换为领域集合对象
     *
     * @param srcList 批次中任务依赖关系表(dc_batch_task_depend)的数据库存储实体信息
     * @return 批次中任务依赖关系-的领域实体信息
     */
    public static List<DcBatchTaskDependDO> toListDomainEntity(List<DcBatchTaskDependPO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcBatchTaskDependDO> targetList = new ArrayList<>(srcList.size());
        for (DcBatchTaskDependPO dataItemTmp : srcList){
            targetList.add(toDomainEntity(dataItemTmp));
        }
        return targetList;
    }

}
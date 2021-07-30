package com.liujun.schedule.infrastructure.repository.task.assembler;

import com.liujun.schedule.domain.task.entity.DcBatchLogDO;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchLogPO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 领域实体与存储实体的转换
 *
 * @version 0.0.1
 * @author liujun
 */
public class DcBatchLogPersistAssembler {


    /**
     * 将领域对象转换为存储对象
     *
     * @param src 批次的日志批次状态-的领域实体信息
     * @return 批次的日志批次状态表(dc_batch_log)的数据库存储实体信息
     */
    public static DcBatchLogPO toPersistObject(DcBatchLogDO src){
        if  (null == src){
            return null;
        }
        DcBatchLogPO target = new DcBatchLogPO();
        //日志ID
        target.setLogId(src.getLogId());
        //批次号
        target.setBatchId(src.getBatchId());
        //任务的名称
        target.setBatchName(src.getBatchName());
        //批次中任务最大并行度，防止一个批次占用过多的线程
        target.setBatchConcurrent(src.getBatchConcurrent());
        //任务的状态:, 1:成功, 2:任务执行中, 0:初始化状态 -1：失败
        target.setBatchStatus(src.getBatchStatus());
        //任务日志，成功为空，失败时记录下失败信息
        target.setBatchMsg(src.getBatchMsg());
        //批次的开始时间
        target.setBatchStartTime(src.getBatchStartTime());
        //批次的结束时间
        target.setBatchFinishTime(src.getBatchFinishTime());
        //每个批次执行时的标识
        target.setTaskRuntimeFlag(src.getTaskRuntimeFlag());
        return target;
    }

    /**
     * 将存储对象转换为领域对象
     *
     * @param src 批次的日志批次状态表(dc_batch_log)的数据库存储实体信息
     * @return 批次的日志批次状态-的领域实体信息
     */
    public static DcBatchLogDO toDomainEntity(DcBatchLogPO src){
        if  (null == src){
            return null;
        }
        DcBatchLogDO target = new DcBatchLogDO();
        //日志ID
        target.setLogId(src.getLogId());
        //批次号
        target.setBatchId(src.getBatchId());
        //任务的名称
        target.setBatchName(src.getBatchName());
        //批次中任务最大并行度，防止一个批次占用过多的线程
        target.setBatchConcurrent(src.getBatchConcurrent());
        //任务的状态:, 1:成功, 2:任务执行中, 0:初始化状态 -1：失败
        target.setBatchStatus(src.getBatchStatus());
        //任务日志，成功为空，失败时记录下失败信息
        target.setBatchMsg(src.getBatchMsg());
        //批次的开始时间
        target.setBatchStartTime(src.getBatchStartTime());
        //批次的结束时间
        target.setBatchFinishTime(src.getBatchFinishTime());
        //每个批次执行时的标识
        target.setTaskRuntimeFlag(src.getTaskRuntimeFlag());
        return target;
    }

    /**
     * 将领域集合对象转换为存储集合对象
     *
     * @param srcList 批次的日志批次状态-的领域实体信息
     * @return 批次的日志批次状态表(dc_batch_log)的数据库存储实体信息
     */
    public static List<DcBatchLogPO> toListPersistObject(List<DcBatchLogDO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcBatchLogPO> targetList = new ArrayList<>(srcList.size());
        for (DcBatchLogDO dataItemTmp : srcList){
            targetList.add(toPersistObject(dataItemTmp));
        }
        return targetList;
    }

    /**
     * 将存储集合对象转换为领域集合对象
     *
     * @param srcList 批次的日志批次状态表(dc_batch_log)的数据库存储实体信息
     * @return 批次的日志批次状态-的领域实体信息
     */
    public static List<DcBatchLogDO> toListDomainEntity(List<DcBatchLogPO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcBatchLogDO> targetList = new ArrayList<>(srcList.size());
        for (DcBatchLogPO dataItemTmp : srcList){
            targetList.add(toDomainEntity(dataItemTmp));
        }
        return targetList;
    }

}
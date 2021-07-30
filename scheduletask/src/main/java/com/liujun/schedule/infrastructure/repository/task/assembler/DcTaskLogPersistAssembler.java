package com.liujun.schedule.infrastructure.repository.task.assembler;

import com.liujun.schedule.domain.task.entity.DcTaskLogDO;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskLogPO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 领域实体与存储实体的转换
 *
 * @version 0.0.1
 * @author liujun
 */
public class DcTaskLogPersistAssembler {


    /**
     * 将领域对象转换为存储对象
     *
     * @param src 任务的日志信息-的领域实体信息
     * @return 任务的日志信息表(dc_task_log)的数据库存储实体信息
     */
    public static DcTaskLogPO toPersistObject(DcTaskLogDO src){
        if  (null == src){
            return null;
        }
        DcTaskLogPO target = new DcTaskLogPO();
        //日志ID
        target.setLogId(src.getLogId());
        //批次号
        target.setBatchId(src.getBatchId());
        //任务的ID
        target.setTaskId(src.getTaskId());
        //任务名称
        target.setTaskName(src.getTaskName());
        //任务的状态:, 1:成功, 2:任务执行中, -1：失败
        target.setTaskStatus(src.getTaskStatus());
        //任务的运行次数次数, 从1开始，任务最少执行一次
        target.setTaskRunNum(src.getTaskRunNum());
        //任务的的配制
        target.setTaskCfg(src.getTaskCfg());
        //日志信息
        target.setTaskMsg(src.getTaskMsg());
        //任务的开始时间
        target.setTaskStartTime(src.getTaskStartTime());
        //任务的结束时间
        target.setTaskFinishTime(src.getTaskFinishTime());
        //当前任务在任务依赖链中的顺序
        target.setTaskOrder(src.getTaskOrder());
        //每次执行任务时的一个标识
        target.setTaskRuntimeFlag(src.getTaskRuntimeFlag());
        return target;
    }

    /**
     * 将存储对象转换为领域对象
     *
     * @param src 任务的日志信息表(dc_task_log)的数据库存储实体信息
     * @return 任务的日志信息-的领域实体信息
     */
    public static DcTaskLogDO toDomainEntity(DcTaskLogPO src){
        if  (null == src){
            return null;
        }
        DcTaskLogDO target = new DcTaskLogDO();
        //日志ID
        target.setLogId(src.getLogId());
        //批次号
        target.setBatchId(src.getBatchId());
        //任务的ID
        target.setTaskId(src.getTaskId());
        //任务名称
        target.setTaskName(src.getTaskName());
        //任务的状态:, 1:成功, 2:任务执行中, -1：失败
        target.setTaskStatus(src.getTaskStatus());
        //任务的运行次数次数, 从1开始，任务最少执行一次
        target.setTaskRunNum(src.getTaskRunNum());
        //任务的的配制
        target.setTaskCfg(src.getTaskCfg());
        //日志信息
        target.setTaskMsg(src.getTaskMsg());
        //任务的开始时间
        target.setTaskStartTime(src.getTaskStartTime());
        //任务的结束时间
        target.setTaskFinishTime(src.getTaskFinishTime());
        //当前任务在任务依赖链中的顺序
        target.setTaskOrder(src.getTaskOrder());
        //每次执行任务时的一个标识
        target.setTaskRuntimeFlag(src.getTaskRuntimeFlag());
        return target;
    }

    /**
     * 将领域集合对象转换为存储集合对象
     *
     * @param srcList 任务的日志信息-的领域实体信息
     * @return 任务的日志信息表(dc_task_log)的数据库存储实体信息
     */
    public static List<DcTaskLogPO> toListPersistObject(List<DcTaskLogDO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcTaskLogPO> targetList = new ArrayList<>(srcList.size());
        for (DcTaskLogDO dataItemTmp : srcList){
            targetList.add(toPersistObject(dataItemTmp));
        }
        return targetList;
    }

    /**
     * 将存储集合对象转换为领域集合对象
     *
     * @param srcList 任务的日志信息表(dc_task_log)的数据库存储实体信息
     * @return 任务的日志信息-的领域实体信息
     */
    public static List<DcTaskLogDO> toListDomainEntity(List<DcTaskLogPO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcTaskLogDO> targetList = new ArrayList<>(srcList.size());
        for (DcTaskLogPO dataItemTmp : srcList){
            targetList.add(toDomainEntity(dataItemTmp));
        }
        return targetList;
    }

}
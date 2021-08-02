package com.liujun.schedule.infrastructure.repository.task.assembler;

import com.liujun.task.task.entity.DcScheduleCronDO;
import com.liujun.schedule.infrastructure.repository.task.po.DcScheduleCronPO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 领域实体与存储实体的转换
 *
 * @version 0.0.1
 * @author liujun
 */
public class DcScheduleCronPersistAssembler {


    /**
     * 将领域对象转换为存储对象
     *
     * @param src 调度的CRON-达式信息-的领域实体信息
     * @return 调度的CRON表达式信息表(dc_schedule_cron)的数据库存储实体信息
     */
    public static DcScheduleCronPO toPersistObject(DcScheduleCronDO src){
        if  (null == src){
            return null;
        }
        DcScheduleCronPO target = new DcScheduleCronPO();
        //使用算法生成
        target.setTaskId(src.getTaskId());
        //当前任务的类型, 1:按任务调度,即, DC_TASK中的任务ID, 2:按批次调用，即DC_BATCH_INFO中的批次的ID
        target.setTaskType(src.getTaskType());
        //类型(月:MONTH,周:WEEK,日:DAY)
        target.setScheduleType(src.getScheduleType());
        //值(月:1,2,3 周:MON,SUN)
        target.setScheduleValue(src.getScheduleValue());
        //CRON的表达式
        target.setScheduleCron(src.getScheduleCron());
        //UI显示和配置的时间
        target.setUiTime(src.getUiTime());
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
     * 将存储对象转换为领域对象
     *
     * @param src 调度的CRON表达式信息表(dc_schedule_cron)的数据库存储实体信息
     * @return 调度的CRON-达式信息-的领域实体信息
     */
    public static DcScheduleCronDO toDomainEntity(DcScheduleCronPO src){
        if  (null == src){
            return null;
        }
        DcScheduleCronDO target = new DcScheduleCronDO();
        //使用算法生成
        target.setTaskId(src.getTaskId());
        //当前任务的类型, 1:按任务调度,即, DC_TASK中的任务ID, 2:按批次调用，即DC_BATCH_INFO中的批次的ID
        target.setTaskType(src.getTaskType());
        //类型(月:MONTH,周:WEEK,日:DAY)
        target.setScheduleType(src.getScheduleType());
        //值(月:1,2,3 周:MON,SUN)
        target.setScheduleValue(src.getScheduleValue());
        //CRON的表达式
        target.setScheduleCron(src.getScheduleCron());
        //UI显示和配置的时间
        target.setUiTime(src.getUiTime());
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
     * @param srcList 调度的CRON-达式信息-的领域实体信息
     * @return 调度的CRON表达式信息表(dc_schedule_cron)的数据库存储实体信息
     */
    public static List<DcScheduleCronPO> toListPersistObject(List<DcScheduleCronDO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcScheduleCronPO> targetList = new ArrayList<>(srcList.size());
        for (DcScheduleCronDO dataItemTmp : srcList){
            targetList.add(toPersistObject(dataItemTmp));
        }
        return targetList;
    }

    /**
     * 将存储集合对象转换为领域集合对象
     *
     * @param srcList 调度的CRON表达式信息表(dc_schedule_cron)的数据库存储实体信息
     * @return 调度的CRON-达式信息-的领域实体信息
     */
    public static List<DcScheduleCronDO> toListDomainEntity(List<DcScheduleCronPO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcScheduleCronDO> targetList = new ArrayList<>(srcList.size());
        for (DcScheduleCronPO dataItemTmp : srcList){
            targetList.add(toDomainEntity(dataItemTmp));
        }
        return targetList;
    }

}
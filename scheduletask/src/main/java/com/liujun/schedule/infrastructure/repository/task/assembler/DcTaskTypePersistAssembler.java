package com.liujun.schedule.infrastructure.repository.task.assembler;

import com.liujun.schedule.domain.task.entity.DcTaskTypeDO;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskTypePO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 领域实体与存储实体的转换
 *
 * @version 0.0.1
 * @author liujun
 */
public class DcTaskTypePersistAssembler {


    /**
     * 将领域对象转换为存储对象
     *
     * @param src 调度任务信息-的领域实体信息
     * @return 调度任务信息表(dc_task_type)的数据库存储实体信息
     */
    public static DcTaskTypePO toPersistObject(DcTaskTypeDO src){
        if  (null == src){
            return null;
        }
        DcTaskTypePO target = new DcTaskTypePO();
        //任务的类型,
        target.setType(src.getType());
        //类型的信息
        target.setTypeName(src.getTypeName());
        //类型的描述
        target.setTypeMsg(src.getTypeMsg());
        //任务的配制信息,以JSON格式
        target.setTypeCfg(src.getTypeCfg());
        return target;
    }

    /**
     * 将存储对象转换为领域对象
     *
     * @param src 调度任务信息表(dc_task_type)的数据库存储实体信息
     * @return 调度任务信息-的领域实体信息
     */
    public static DcTaskTypeDO toDomainEntity(DcTaskTypePO src){
        if  (null == src){
            return null;
        }
        DcTaskTypeDO target = new DcTaskTypeDO();
        //任务的类型,
        target.setType(src.getType());
        //类型的信息
        target.setTypeName(src.getTypeName());
        //类型的描述
        target.setTypeMsg(src.getTypeMsg());
        //任务的配制信息,以JSON格式
        target.setTypeCfg(src.getTypeCfg());
        return target;
    }

    /**
     * 将领域集合对象转换为存储集合对象
     *
     * @param srcList 调度任务信息-的领域实体信息
     * @return 调度任务信息表(dc_task_type)的数据库存储实体信息
     */
    public static List<DcTaskTypePO> toListPersistObject(List<DcTaskTypeDO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcTaskTypePO> targetList = new ArrayList<>(srcList.size());
        for (DcTaskTypeDO dataItemTmp : srcList){
            targetList.add(toPersistObject(dataItemTmp));
        }
        return targetList;
    }

    /**
     * 将存储集合对象转换为领域集合对象
     *
     * @param srcList 调度任务信息表(dc_task_type)的数据库存储实体信息
     * @return 调度任务信息-的领域实体信息
     */
    public static List<DcTaskTypeDO> toListDomainEntity(List<DcTaskTypePO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcTaskTypeDO> targetList = new ArrayList<>(srcList.size());
        for (DcTaskTypePO dataItemTmp : srcList){
            targetList.add(toDomainEntity(dataItemTmp));
        }
        return targetList;
    }

}
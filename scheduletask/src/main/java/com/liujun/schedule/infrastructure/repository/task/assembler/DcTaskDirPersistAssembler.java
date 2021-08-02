package com.liujun.schedule.infrastructure.repository.task.assembler;

import com.liujun.task.task.entity.DcTaskDirDO;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskDirPO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 领域实体与存储实体的转换
 *
 * @version 0.0.1
 * @author liujun
 */
public class DcTaskDirPersistAssembler {


    /**
     * 将领域对象转换为存储对象
     *
     * @param src 任务目录信息-的领域实体信息
     * @return 任务目录信息表(dc_task_dir)的数据库存储实体信息
     */
    public static DcTaskDirPO toPersistObject(DcTaskDirDO src){
        if  (null == src){
            return null;
        }
        DcTaskDirPO target = new DcTaskDirPO();
        //任务目录的ID
        target.setTaskDirId(src.getTaskDirId());
        //父任务目录的ID
        target.setParentDirId(src.getParentDirId());
        //任务目录名称
        target.setTaskDirName(src.getTaskDirName());
        //任务目录描述
        target.setTaskDirDescription(src.getTaskDirDescription());
        //排序号
        target.setTaskOrder(src.getTaskOrder());
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
     * @param src 任务目录信息表(dc_task_dir)的数据库存储实体信息
     * @return 任务目录信息-的领域实体信息
     */
    public static DcTaskDirDO toDomainEntity(DcTaskDirPO src){
        if  (null == src){
            return null;
        }
        DcTaskDirDO target = new DcTaskDirDO();
        //任务目录的ID
        target.setTaskDirId(src.getTaskDirId());
        //父任务目录的ID
        target.setParentDirId(src.getParentDirId());
        //任务目录名称
        target.setTaskDirName(src.getTaskDirName());
        //任务目录描述
        target.setTaskDirDescription(src.getTaskDirDescription());
        //排序号
        target.setTaskOrder(src.getTaskOrder());
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
     * @param srcList 任务目录信息-的领域实体信息
     * @return 任务目录信息表(dc_task_dir)的数据库存储实体信息
     */
    public static List<DcTaskDirPO> toListPersistObject(List<DcTaskDirDO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcTaskDirPO> targetList = new ArrayList<>(srcList.size());
        for (DcTaskDirDO dataItemTmp : srcList){
            targetList.add(toPersistObject(dataItemTmp));
        }
        return targetList;
    }

    /**
     * 将存储集合对象转换为领域集合对象
     *
     * @param srcList 任务目录信息表(dc_task_dir)的数据库存储实体信息
     * @return 任务目录信息-的领域实体信息
     */
    public static List<DcTaskDirDO> toListDomainEntity(List<DcTaskDirPO> srcList){
        if  (srcList == null || srcList.isEmpty()){
            return Collections.emptyList();
        }
        
        List<DcTaskDirDO> targetList = new ArrayList<>(srcList.size());
        for (DcTaskDirPO dataItemTmp : srcList){
            targetList.add(toDomainEntity(dataItemTmp));
        }
        return targetList;
    }

}
package com.liujun.schedule.infrastructure.repository.task.assembler;

import com.liujun.task.task.entity.DcTaskInfoDO;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskInfoPO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 领域实体与存储实体的转换
 *
 * @author liujun
 * @version 0.0.1
 */
public class DcTaskInfoPersistAssembler {


    /**
     * 将领域对象转换为存储对象
     *
     * @param src 调度任务信息-的领域实体信息
     * @return 调度任务信息表(dc_task_info)的数据库存储实体信息
     */
    public static DcTaskInfoPO toPersistObject(DcTaskInfoDO src) {
        if (null == src) {
            return null;
        }
        DcTaskInfoPO target = new DcTaskInfoPO();
        //任务的ID,
        target.setTaskId(src.getTaskId());
        //目录的id
        target.setTaskDirId(src.getTaskDirId());
        //任务的名称
        target.setTaskName(src.getTaskName());
        //任务的类型:关联DC_TASK_TYPE表
        target.setTaskType(src.getTaskType());
        //任务的状态, 1:正常状态,0，停用状态
        target.setStatus(src.getStatus());
        //任务的的配制
        target.setTaskCfg(src.getTaskCfg());
        //重试配制:-1,无限重试;5,15,30.执行后5秒重试，以此类推,成功则不再重试。
        target.setTaskRetry(src.getTaskRetry());
        //任务的描述
        target.setTaskMsg(src.getTaskMsg());
        //创建者
        target.setCreator(src.getCreator());
        //更新者
        target.setUpdater(src.getUpdater());
        //创建时间
        target.setCreateTime(src.getCreateTime());
        //更新时间
        target.setUpdateTime(src.getUpdateTime());
        target.setTaskList(src.getTaskList());
        return target;
    }

    /**
     * 将存储对象转换为领域对象
     *
     * @param src 调度任务信息表(dc_task_info)的数据库存储实体信息
     * @return 调度任务信息-的领域实体信息
     */
    public static DcTaskInfoDO toDomainEntity(DcTaskInfoPO src) {
        if (null == src) {
            return null;
        }
        DcTaskInfoDO target = new DcTaskInfoDO();
        //任务的ID,
        target.setTaskId(src.getTaskId());
        //目录的id
        target.setTaskDirId(src.getTaskDirId());
        //任务的名称
        target.setTaskName(src.getTaskName());
        //任务的类型:关联DC_TASK_TYPE表
        target.setTaskType(src.getTaskType());
        //任务的状态, 1:正常状态,0，停用状态
        target.setStatus(src.getStatus());
        //任务的的配制
        target.setTaskCfg(src.getTaskCfg());
        //重试配制:-1,无限重试;5,15,30.执行后5秒重试，以此类推,成功则不再重试。
        target.setTaskRetry(src.getTaskRetry());
        //任务的描述
        target.setTaskMsg(src.getTaskMsg());
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
     * @param srcList 调度任务信息-的领域实体信息
     * @return 调度任务信息表(dc_task_info)的数据库存储实体信息
     */
    public static List<DcTaskInfoPO> toListPersistObject(List<DcTaskInfoDO> srcList) {
        if (srcList == null || srcList.isEmpty()) {
            return Collections.emptyList();
        }

        List<DcTaskInfoPO> targetList = new ArrayList<>(srcList.size());
        for (DcTaskInfoDO dataItemTmp : srcList) {
            targetList.add(toPersistObject(dataItemTmp));
        }
        return targetList;
    }

    /**
     * 将存储集合对象转换为领域集合对象
     *
     * @param srcList 调度任务信息表(dc_task_info)的数据库存储实体信息
     * @return 调度任务信息-的领域实体信息
     */
    public static List<DcTaskInfoDO> toListDomainEntity(List<DcTaskInfoPO> srcList) {
        if (srcList == null || srcList.isEmpty()) {
            return Collections.emptyList();
        }

        List<DcTaskInfoDO> targetList = new ArrayList<>(srcList.size());
        for (DcTaskInfoPO dataItemTmp : srcList) {
            targetList.add(toDomainEntity(dataItemTmp));
        }
        return targetList;
    }

}
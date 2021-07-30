package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.liujun.schedule.infrastructure.repository.task.po.DcTaskInfoPO;

import java.util.List;

/**
 * 调度任务信息表(dc_task_info)的数据库操作
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcTaskInfoMapper {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insert(DcTaskInfoPO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insertList(List<DcTaskInfoPO> param);


    /**
     * 修改方法
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int update(DcTaskInfoPO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int deleteByIds(DcTaskInfoPO param);


    /**
     * 分页查询
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    List<DcTaskInfoPO> queryPage(DcTaskInfoPO param);


    /**
     * 分页查询
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    List<DcTaskInfoPO> getTaskByTaskList(DcTaskInfoPO param);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    DcTaskInfoPO detail(DcTaskInfoPO param);

}
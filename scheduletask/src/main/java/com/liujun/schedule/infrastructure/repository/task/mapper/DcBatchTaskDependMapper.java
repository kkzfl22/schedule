package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.liujun.schedule.infrastructure.repository.task.po.DcBatchTaskDependPO;

import java.util.List;

/**
 * 批次中任务依赖关系表(dc_batch_task_depend)的数据库操作
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcBatchTaskDependMapper {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insert(DcBatchTaskDependPO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insertList(List<DcBatchTaskDependPO> param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int deleteByIds(DcBatchTaskDependPO param);


    /**
     * 分页查询
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    List<DcBatchTaskDependPO> queryPage(DcBatchTaskDependPO param);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    DcBatchTaskDependPO detail(DcBatchTaskDependPO param);

}
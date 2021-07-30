package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.liujun.schedule.infrastructure.repository.task.po.DcTaskTypePO;

import java.util.List;

/**
 * 调度任务信息表(dc_task_type)的数据库操作
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcTaskTypeMapper {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insert(DcTaskTypePO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insertList(List<DcTaskTypePO> param);


    /**
     * 修改方法
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int update(DcTaskTypePO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int deleteByIds(DcTaskTypePO param);


    /**
     * 分页查询
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    List<DcTaskTypePO> queryPage(DcTaskTypePO param);


    /**
     * 查询所有的类型信息
     *
     * @return 数据库查询结果集
     */
    List<DcTaskTypePO> queryAll();


    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    DcTaskTypePO detail(DcTaskTypePO param);

}
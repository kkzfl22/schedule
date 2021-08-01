package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.liujun.schedule.infrastructure.repository.task.po.DcTaskLogPO;

import java.util.List;

/**
 * 任务的日志信息表(dc_task_log)的数据库操作
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcTaskLogMapper {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insert(DcTaskLogPO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insertList(List<DcTaskLogPO> param);


    /**
     * 修改状态的方法
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int updateStatus(DcTaskLogPO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int deleteByIds(DcTaskLogPO param);


    /**
     * 分页查询
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    List<DcTaskLogPO> queryPage(DcTaskLogPO param);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    DcTaskLogPO detail(DcTaskLogPO param);

}
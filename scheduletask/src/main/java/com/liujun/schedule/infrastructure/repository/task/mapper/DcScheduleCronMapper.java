package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.liujun.schedule.infrastructure.repository.task.po.DcScheduleCronPO;

import java.util.List;

/**
 * 调度的CRON表达式信息表(dc_schedule_cron)的数据库操作
 *
 * @version 0.0.1
 * @author liujun
 */
public interface DcScheduleCronMapper {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insert(DcScheduleCronPO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insertList(List<DcScheduleCronPO> param);


    /**
     * 修改方法
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int update(DcScheduleCronPO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int deleteByIds(DcScheduleCronPO param);


    /**
     * 分页查询
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    List<DcScheduleCronPO> queryPage(DcScheduleCronPO param);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    DcScheduleCronPO detail(DcScheduleCronPO param);

}
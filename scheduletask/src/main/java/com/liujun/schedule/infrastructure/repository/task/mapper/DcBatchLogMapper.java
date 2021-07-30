package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.liujun.schedule.infrastructure.repository.task.po.DcBatchLogPO;

import java.util.List;

/**
 * 批次的日志批次状态表(dc_batch_log)的数据库操作
 *
 * @version 0.0.1
 * @author liujun
 */
public interface DcBatchLogMapper {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insert(DcBatchLogPO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insertList(List<DcBatchLogPO> param);


    /**
     * 修改方法
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int update(DcBatchLogPO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int deleteByIds(DcBatchLogPO param);


    /**
     * 分页查询
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    List<DcBatchLogPO> queryPage(DcBatchLogPO param);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    DcBatchLogPO detail(DcBatchLogPO param);

}
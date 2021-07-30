package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.liujun.schedule.infrastructure.repository.task.po.DcBatchInfoPO;

import java.util.List;

/**
 * 批次信息表(dc_batch_info)的数据库操作
 *
 * @version 0.0.1
 * @author liujun
 */
public interface DcBatchInfoMapper {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insert(DcBatchInfoPO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insertList(List<DcBatchInfoPO> param);


    /**
     * 修改方法
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int updateRunStatus(DcBatchInfoPO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int deleteByIds(DcBatchInfoPO param);


    /**
     * 分页查询
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    List<DcBatchInfoPO> queryPage(DcBatchInfoPO param);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    DcBatchInfoPO detail(DcBatchInfoPO param);

}
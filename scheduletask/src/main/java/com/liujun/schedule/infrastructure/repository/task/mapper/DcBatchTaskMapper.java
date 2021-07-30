package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.liujun.schedule.infrastructure.repository.task.po.DcBatchTaskPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 批次任务信息表(dc_batch_task)的数据库操作
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcBatchTaskMapper {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insert(DcBatchTaskPO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int insertList(List<DcBatchTaskPO> param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 数据库影响的行数
     */
    int deleteByIds(DcBatchTaskPO param);


    /**
     * 分页查询
     *
     * @param batchId 参数信息
     * @return 数据库查询结果集
     */
    List<DcBatchTaskPO> getTaskListByBatchId(@Param("batchId") Long batchId);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    DcBatchTaskPO detail(DcBatchTaskPO param);

}
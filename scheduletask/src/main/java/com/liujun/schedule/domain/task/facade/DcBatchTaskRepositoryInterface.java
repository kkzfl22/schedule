package com.liujun.schedule.domain.task.facade;

import com.liujun.schedule.domain.task.entity.DcBatchTaskDO;

import java.util.List;

/**
 * 批次任务信息-的领域存储接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcBatchTaskRepositoryInterface {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insert(DcBatchTaskDO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insertList(List<DcBatchTaskDO> param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean deleteById(DcBatchTaskDO param);


    /**
     * 分页查询
     *
     * @param batchId 批次号
     * @return 分页查询响应
     */
    List<DcBatchTaskDO> getTaskListByBatchId(Long batchId);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数询结果集
     */
    DcBatchTaskDO detail(DcBatchTaskDO param);

}
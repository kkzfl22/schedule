package com.liujun.schedule.domain.task.facade;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDependDO;

import java.util.List;

/**
 * 批次中任务依赖关系-的领域存储接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcBatchTaskDependRepositoryInterface {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insert(DcBatchTaskDependDO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insertList(List<DcBatchTaskDependDO> param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean deleteByIds(DcBatchTaskDependDO param);


    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    DomainPage<List<DcBatchTaskDependDO>> queryPage(DomainPage<DcBatchTaskDependDO> pageReq);


    /**
     * 通过批次号获取依赖关系
     *
     * @param param 参数信息
     * @return 数据库查询结果集
     */
    List<DcBatchTaskDependDO> getDependByBatchId(DcBatchTaskDependDO param);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数询结果集
     */
    DcBatchTaskDependDO detail(DcBatchTaskDependDO param);

}
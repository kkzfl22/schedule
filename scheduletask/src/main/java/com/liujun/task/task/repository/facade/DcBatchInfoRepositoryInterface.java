package com.liujun.task.task.repository.facade;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.task.task.entity.DcBatchInfoDO;

import java.util.List;

/**
 * 批次信息-的领域存储接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcBatchInfoRepositoryInterface {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insert(DcBatchInfoDO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insertList(List<DcBatchInfoDO> param);


    /**
     * 修改运行状态
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean updateRunStatus(DcBatchInfoDO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean deleteByIds(DcBatchInfoDO param);


    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    DomainPage<List<DcBatchInfoDO>> queryPage(DomainPage<DcBatchInfoDO> pageReq);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数询结果集
     */
    DcBatchInfoDO detail(DcBatchInfoDO param);

}
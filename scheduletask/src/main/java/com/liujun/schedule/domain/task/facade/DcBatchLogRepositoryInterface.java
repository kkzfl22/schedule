package com.liujun.schedule.domain.task.facade;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.schedule.domain.task.entity.DcBatchLogDO;

import java.util.List;

/**
 * 批次的日志批次状态-的领域存储接口
 *
 * @version 0.0.1
 * @author liujun
 */
public interface DcBatchLogRepositoryInterface {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insert(DcBatchLogDO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insertList(List<DcBatchLogDO> param);


    /**
     * 修改方法
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean updateStatus(DcBatchLogDO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean deleteByIds(DcBatchLogDO param);


    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    DomainPage<List<DcBatchLogDO>> queryPage(DomainPage<DcBatchLogDO> pageReq);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数询结果集
     */
    DcBatchLogDO detail(DcBatchLogDO param);

}
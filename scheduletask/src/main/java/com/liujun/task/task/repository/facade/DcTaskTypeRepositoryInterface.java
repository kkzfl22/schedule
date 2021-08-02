package com.liujun.task.task.repository.facade;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.task.task.entity.DcTaskTypeDO;

import java.util.List;

/**
 * 调度任务信息-的领域存储接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcTaskTypeRepositoryInterface {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insert(DcTaskTypeDO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insertList(List<DcTaskTypeDO> param);


    /**
     * 修改方法
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean update(DcTaskTypeDO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean deleteByIds(DcTaskTypeDO param);


    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    DomainPage<List<DcTaskTypeDO>> queryPage(DomainPage<DcTaskTypeDO> pageReq);


    /**
     * 查询所有
     *
     * @return 分页查询响应
     */
    List<DcTaskTypeDO> queryAll();


    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数询结果集
     */
    DcTaskTypeDO detail(DcTaskTypeDO param);

}
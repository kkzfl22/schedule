package com.liujun.task.task.repository.facade;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.task.task.entity.DcScheduleCronDO;

import java.util.List;

/**
 * 调度的CRON-达式信息-的领域存储接口
 *
 * @version 0.0.1
 * @author liujun
 */
public interface DcScheduleCronRepositoryInterface {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insert(DcScheduleCronDO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insertList(List<DcScheduleCronDO> param);


    /**
     * 修改方法
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean update(DcScheduleCronDO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean deleteByIds(DcScheduleCronDO param);


    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    DomainPage<List<DcScheduleCronDO>> queryPage(DomainPage<DcScheduleCronDO> pageReq);

    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数询结果集
     */
    DcScheduleCronDO detail(DcScheduleCronDO param);

}
package com.liujun.schedule.domain.task.facade;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.schedule.domain.task.entity.DcTaskInfoDO;

import java.util.List;

/**
 * 调度任务信息-的领域存储接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface DcTaskInfoRepositoryInterface {


    /**
     * 单个添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insert(DcTaskInfoDO param);


    /**
     * 批量添加
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean insertList(List<DcTaskInfoDO> param);


    /**
     * 修改方法
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean update(DcTaskInfoDO param);


    /**
     * 批量删除
     *
     * @param param 参数信息
     * @return 操作是否成功
     */
    boolean deleteByIds(DcTaskInfoDO param);


    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    DomainPage<List<DcTaskInfoDO>> queryPage(DomainPage<DcTaskInfoDO> pageReq);


    /**
     * 按指定的任务获取任务的信息
     *
     * @param taskInfo
     * @return
     */
    List<DcTaskInfoDO> getTaskByTaskList(DcTaskInfoDO taskInfo);


    /**
     * 按id查询详细
     *
     * @param param 参数信息
     * @return 数询结果集
     */
    DcTaskInfoDO detail(DcTaskInfoDO param);

}
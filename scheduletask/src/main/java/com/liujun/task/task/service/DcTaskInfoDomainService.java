package com.liujun.task.task.service;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.task.task.entity.DcTaskInfoDO;
import com.liujun.task.task.repository.facade.DcTaskInfoRepositoryInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调度任务信息-的领域服务
 *
 * @author liujun
 * @version 0.0.1
 */
@Service
@Slf4j
public class DcTaskInfoDomainService {


    /**
     * 调度任务信息-的领域存储接口
     */
    @Autowired
    private DcTaskInfoRepositoryInterface repository;

    /**
     * 单个添加
     *
     * @param param 调度任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insert(DcTaskInfoDO param) {
        boolean updateRsp = repository.insert(param);
        return updateRsp;
    }

    /**
     * 批量添加
     *
     * @param param 调度任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insertList(List<DcTaskInfoDO> param) {
        boolean updateRsp = repository.insertList(param);
        return updateRsp;
    }

    /**
     * 修改方法
     *
     * @param param 调度任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean update(DcTaskInfoDO param) {
        boolean updateRsp = repository.update(param);
        return updateRsp;
    }

    /**
     * 批量删除
     *
     * @param param 调度任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean deleteByIds(DcTaskInfoDO param) {
        boolean updateRsp = repository.deleteByIds(param);
        return updateRsp;
    }

    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    public DomainPage<List<DcTaskInfoDO>> queryPage(DomainPage<DcTaskInfoDO> pageReq) {
        DomainPage<List<DcTaskInfoDO>> pageResult = repository.queryPage(pageReq);
        return pageResult;
    }


    /**
     * 通过任务的id获取任务信息
     *
     * @param taskInfo
     * @return
     */
    public List<DcTaskInfoDO> getTaskByTaskList(DcTaskInfoDO taskInfo) {
        return repository.getTaskByTaskList(taskInfo);
    }

    /**
     * 按id查询详细
     *
     * @param param 调度任务信息-的领域实体信息
     * @return 数据集
     */
    public DcTaskInfoDO detail(DcTaskInfoDO param) {
        DcTaskInfoDO queryReturn = repository.detail(param);
        return queryReturn;
    }

}
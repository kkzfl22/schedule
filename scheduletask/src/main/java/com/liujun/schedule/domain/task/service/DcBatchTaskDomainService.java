package com.liujun.schedule.domain.task.service;

import com.liujun.schedule.domain.task.entity.DcBatchTaskDO;
import com.liujun.schedule.domain.task.facade.DcBatchTaskRepositoryInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批次任务信息-的领域服务
 *
 * @author liujun
 * @version 0.0.1
 */
@Service
@Slf4j
public class DcBatchTaskDomainService {


    /**
     * 批次任务信息-的领域存储接口
     */
    @Autowired
    private DcBatchTaskRepositoryInterface repository;

    /**
     * 单个添加
     *
     * @param param 批次任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insert(DcBatchTaskDO param) {
        boolean updateRsp = repository.insert(param);
        return updateRsp;
    }

    /**
     * 批量添加
     *
     * @param param 批次任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insertList(List<DcBatchTaskDO> param) {
        boolean updateRsp = repository.insertList(param);
        return updateRsp;
    }


    /**
     * 批量删除
     *
     * @param param 批次任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean deleteByBatchId(DcBatchTaskDO param) {
        boolean updateRsp = repository.deleteById(param);
        return updateRsp;
    }

    /**
     * 通过批次号获取批次的所有任务
     *
     * @param batchId 分页查询请求参数
     * @return 分页查询响应
     */
    public List<DcBatchTaskDO> getTaskListByBatchId(Long batchId) {
        return repository.getTaskListByBatchId(batchId);
    }


    /**
     * 按id查询详细
     *
     * @param param 批次任务信息-的领域实体信息
     * @return 数据集
     */
    public DcBatchTaskDO detail(DcBatchTaskDO param) {
        DcBatchTaskDO queryReturn = repository.detail(param);
        return queryReturn;
    }

}
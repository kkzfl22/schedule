package com.liujun.schedule.application;

import com.liujun.task.task.entity.DcBatchTaskDO;
import com.liujun.task.task.service.DcBatchTaskDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批次任务信息-的应用服务
 *
 * @author liujun
 * @version 0.0.1
 */
@Service
@Slf4j
public class DcBatchTaskApplicationService {


    /**
     * 批次任务信息-的领域服务
     */
    @Autowired
    private DcBatchTaskDomainService domainService;

    /**
     * 单个添加
     *
     * @param param 批次任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insert(DcBatchTaskDO param) {
        boolean updateRsp = domainService.insert(param);
        return updateRsp;
    }

    /**
     * 批量添加
     *
     * @param param 批次任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insertList(List<DcBatchTaskDO> param) {
        boolean updateRsp = domainService.insertList(param);
        return updateRsp;
    }


    /**
     * 批量删除
     *
     * @param param 批次任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean deleteByIds(DcBatchTaskDO param) {
        boolean updateRsp = domainService.deleteByBatchId(param);
        return updateRsp;
    }

    /**
     * 分页查询
     *
     * @param batchId 分页查询请求参数
     * @return 分页查询响应
     */
    public List<DcBatchTaskDO> getTaskListByBatchId(Long batchId) {
        return domainService.getTaskListByBatchId(batchId);
    }

    /**
     * 按id查询详细
     *
     * @param param 批次任务信息-的领域实体信息
     * @return 数据集
     */
    public DcBatchTaskDO detail(DcBatchTaskDO param) {
        DcBatchTaskDO queryReturn = domainService.detail(param);
        return queryReturn;
    }

}
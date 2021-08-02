package com.liujun.schedule.application;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.task.task.entity.DcBatchTaskDependDO;
import com.liujun.task.task.service.DcBatchTaskDependDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批次中任务依赖关系-的应用服务
 *
 * @author liujun
 * @version 0.0.1
 */
@Service
@Slf4j
public class DcBatchTaskDependApplicationService {


    /**
     * 批次中任务依赖关系-的领域服务
     */
    @Autowired
    private DcBatchTaskDependDomainService domainService;

    /**
     * 单个添加
     *
     * @param param 批次中任务依赖关系-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insert(DcBatchTaskDependDO param) {
        boolean updateRsp = domainService.insert(param);
        return updateRsp;
    }

    /**
     * 批量添加
     *
     * @param param 批次中任务依赖关系-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insertList(List<DcBatchTaskDependDO> param) {
        boolean updateRsp = domainService.insertList(param);
        return updateRsp;
    }


    /**
     * 批量删除
     *
     * @param param 批次中任务依赖关系-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean deleteByIds(DcBatchTaskDependDO param) {
        boolean updateRsp = domainService.deleteByIds(param);
        return updateRsp;
    }

    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    public DomainPage<List<DcBatchTaskDependDO>> queryPage(DomainPage<DcBatchTaskDependDO> pageReq) {
        DomainPage<List<DcBatchTaskDependDO>> pageResult = domainService.queryPage(pageReq);
        return pageResult;
    }

    /**
     * 按id查询详细
     *
     * @param param 批次中任务依赖关系-的领域实体信息
     * @return 数据集
     */
    public DcBatchTaskDependDO detail(DcBatchTaskDependDO param) {
        DcBatchTaskDependDO queryReturn = domainService.detail(param);
        return queryReturn;
    }

}
package com.liujun.schedule.application;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.schedule.domain.task.entity.DcBatchLogDO;
import com.liujun.schedule.domain.task.service.DcBatchLogDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批次的日志批次状态-的应用服务
 *
 * @version 0.0.1
 * @author liujun
 */
@Service
@Slf4j
public class DcBatchLogApplicationService {


    /**
     * 批次的日志批次状态-的领域服务
     */
    @Autowired
    private DcBatchLogDomainService domainService;

    /**
     * 单个添加
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insert(DcBatchLogDO param){
        boolean updateRsp = domainService.insert(param);
        return updateRsp;
    }

    /**
     * 批量添加
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insertList(List<DcBatchLogDO> param){
        boolean updateRsp = domainService.insertList(param);
        return updateRsp;
    }

    /**
     * 修改方法
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean update(DcBatchLogDO param){
        boolean updateRsp = domainService.updateStatus(param);
        return updateRsp;
    }

    /**
     * 批量删除
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean deleteByIds(DcBatchLogDO param){
        boolean updateRsp = domainService.deleteByIds(param);
        return updateRsp;
    }

    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    public DomainPage<List<DcBatchLogDO>> queryPage(DomainPage<DcBatchLogDO> pageReq){
        DomainPage<List<DcBatchLogDO>> pageResult = domainService.queryPage(pageReq);
        return pageResult;
    }

    /**
     * 按id查询详细
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return 数据集
     */
    public DcBatchLogDO detail(DcBatchLogDO param){
        DcBatchLogDO queryReturn = domainService.detail(param);
        return queryReturn;
    }

}
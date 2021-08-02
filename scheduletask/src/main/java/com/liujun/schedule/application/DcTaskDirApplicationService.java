package com.liujun.schedule.application;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.task.task.entity.DcTaskDirDO;
import com.liujun.task.task.service.DcTaskDirDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务目录信息-的应用服务
 *
 * @version 0.0.1
 * @author liujun
 */
@Service
@Slf4j
public class DcTaskDirApplicationService {


    /**
     * 任务目录信息-的领域服务
     */
    @Autowired
    private DcTaskDirDomainService domainService;

    /**
     * 单个添加
     *
     * @param param 任务目录信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insert(DcTaskDirDO param){
        boolean updateRsp = domainService.insert(param);
        return updateRsp;
    }

    /**
     * 批量添加
     *
     * @param param 任务目录信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insertList(List<DcTaskDirDO> param){
        boolean updateRsp = domainService.insertList(param);
        return updateRsp;
    }

    /**
     * 修改方法
     *
     * @param param 任务目录信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean update(DcTaskDirDO param){
        boolean updateRsp = domainService.update(param);
        return updateRsp;
    }

    /**
     * 批量删除
     *
     * @param param 任务目录信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean deleteByIds(DcTaskDirDO param){
        boolean updateRsp = domainService.deleteByIds(param);
        return updateRsp;
    }

    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    public DomainPage<List<DcTaskDirDO>> queryPage(DomainPage<DcTaskDirDO> pageReq){
        DomainPage<List<DcTaskDirDO>> pageResult = domainService.queryPage(pageReq);
        return pageResult;
    }

    /**
     * 按id查询详细
     *
     * @param param 任务目录信息-的领域实体信息
     * @return 数据集
     */
    public DcTaskDirDO detail(DcTaskDirDO param){
        DcTaskDirDO queryReturn = domainService.detail(param);
        return queryReturn;
    }

}
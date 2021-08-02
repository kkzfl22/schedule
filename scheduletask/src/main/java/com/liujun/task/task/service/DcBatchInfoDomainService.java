package com.liujun.task.task.service;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.task.task.entity.DcBatchInfoDO;
import com.liujun.task.task.repository.facade.DcBatchInfoRepositoryInterface;
import com.liujun.schedule.infrastructure.comm.uid.UidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批次信息-的领域服务
 *
 * @author liujun
 * @version 0.0.1
 */
@Service
@Slf4j
public class DcBatchInfoDomainService {


    /**
     * 批次信息-的领域存储接口
     */
    @Autowired
    private DcBatchInfoRepositoryInterface repository;

    /**
     * 序列号
     */
    @Autowired
    private UidGenerator uidGenerator;


    /**
     * 单个添加
     *
     * @param param 批次信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insert(DcBatchInfoDO param) {
        param.batchAdd(uidGenerator);
        boolean updateRsp = repository.insert(param);
        return updateRsp;
    }

    /**
     * 批量添加
     *
     * @param param 批次信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insertList(List<DcBatchInfoDO> param) {
        boolean updateRsp = repository.insertList(param);
        return updateRsp;
    }

    /**
     * 修改运行状态
     *
     * @param param 批次信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean updateRunStatus(DcBatchInfoDO param) {
        boolean updateRsp = repository.updateRunStatus(param);
        return updateRsp;
    }

    /**
     * 批量删除
     *
     * @param param 批次信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean deleteByIds(DcBatchInfoDO param) {
        boolean updateRsp = repository.deleteByIds(param);
        return updateRsp;
    }

    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    public DomainPage<List<DcBatchInfoDO>> queryPage(DomainPage<DcBatchInfoDO> pageReq) {
        DomainPage<List<DcBatchInfoDO>> pageResult = repository.queryPage(pageReq);
        return pageResult;
    }

    /**
     * 按id查询详细
     *
     * @param param 批次信息-的领域实体信息
     * @return 数据集
     */
    public DcBatchInfoDO detail(DcBatchInfoDO param) {
        DcBatchInfoDO queryReturn = repository.detail(param);
        return queryReturn;
    }

}
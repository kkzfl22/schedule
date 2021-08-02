package com.liujun.task.task.service;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.task.task.entity.DcBatchLogDO;
import com.liujun.task.task.repository.facade.DcBatchLogRepositoryInterface;
import com.liujun.schedule.infrastructure.comm.uid.UidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批次的日志批次状态-的领域服务
 *
 * @author liujun
 * @version 0.0.1
 */
@Service
@Slf4j
public class DcBatchLogDomainService {


    /**
     * 批次的日志批次状态-的领域存储接口
     */
    @Autowired
    private DcBatchLogRepositoryInterface repository;


    /**
     * 生成的id操作
     */
    @Autowired
    private UidGenerator uid;

    /**
     * 单个添加
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insert(DcBatchLogDO param) {
        //执行添加生成的id处理
        param.addProcess(uid);
        boolean updateRsp = repository.insert(param);
        return updateRsp;
    }

    /**
     * 批量添加
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insertList(List<DcBatchLogDO> param) {
        boolean updateRsp = repository.insertList(param);
        return updateRsp;
    }

    /**
     * 修改方法
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean updateStatus(DcBatchLogDO param) {
        boolean updateRsp = repository.updateStatus(param);
        return updateRsp;
    }

    /**
     * 批量删除
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean deleteByIds(DcBatchLogDO param) {
        boolean updateRsp = repository.deleteByIds(param);
        return updateRsp;
    }

    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    public DomainPage<List<DcBatchLogDO>> queryPage(DomainPage<DcBatchLogDO> pageReq) {
        DomainPage<List<DcBatchLogDO>> pageResult = repository.queryPage(pageReq);
        return pageResult;
    }

    /**
     * 按id查询详细
     *
     * @param param 批次的日志批次状态-的领域实体信息
     * @return 数据集
     */
    public DcBatchLogDO detail(DcBatchLogDO param) {
        DcBatchLogDO queryReturn = repository.detail(param);
        return queryReturn;
    }

}
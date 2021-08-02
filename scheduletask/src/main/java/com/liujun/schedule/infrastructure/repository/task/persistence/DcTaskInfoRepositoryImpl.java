package com.liujun.schedule.infrastructure.repository.task.persistence;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liujun.task.task.entity.DcTaskInfoDO;
import com.liujun.task.task.repository.facade.DcTaskInfoRepositoryInterface;
import com.liujun.schedule.infrastructure.repository.task.assembler.DcTaskInfoPersistAssembler;
import com.liujun.schedule.infrastructure.repository.task.mapper.DcTaskInfoMapper;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskInfoPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 调度任务信息-的领域存储接口实现
 *
 * @author liujun
 * @version 0.0.1
 */
@Repository
@Slf4j
public class DcTaskInfoRepositoryImpl implements DcTaskInfoRepositoryInterface {


    /**
     * 调度任务信息表(dc_task_info)的数据库操作
     */
    @Autowired
    private DcTaskInfoMapper mapper;

    @Override
    public boolean insert(DcTaskInfoDO param) {
        log.debug("insert param {}", param);
        DcTaskInfoPO parseData = DcTaskInfoPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.insert(parseData);
        log.debug("insert param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean insertList(List<DcTaskInfoDO> param) {
        log.debug("insertList param {}", param);
        List<DcTaskInfoPO> parseData = DcTaskInfoPersistAssembler.toListPersistObject(param);
        int updateRsp = mapper.insertList(parseData);
        log.debug("insertList param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean update(DcTaskInfoDO param) {
        log.debug("update param {}", param);
        DcTaskInfoPO parseData = DcTaskInfoPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.update(parseData);
        log.debug("update param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean deleteByIds(DcTaskInfoDO param) {
        log.debug("deleteByIds param {}", param);
        DcTaskInfoPO parseData = DcTaskInfoPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.deleteByIds(parseData);
        log.debug("deleteByIds param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public DomainPage<List<DcTaskInfoDO>> queryPage(DomainPage<DcTaskInfoDO> pageReq) {
        log.debug("queryPage param {}", pageReq);
        DcTaskInfoPO parseData = DcTaskInfoPersistAssembler.toPersistObject(pageReq.getData());
        Page<PageInfo> pageSet = PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<DcTaskInfoPO> pageRsp = mapper.queryPage(parseData);
        log.debug("queryPage param {} return {}", pageReq, pageRsp);
        List<DcTaskInfoDO> pageRspParse = DcTaskInfoPersistAssembler.toListDomainEntity(pageRsp);
        DomainPage pageResult = DomainPage.<List<DcTaskInfoDO>>builder()
                .page(pageSet.getPageNum())
                .size(pageSet.getPageSize())
                .total(pageSet.getTotal())
                .data(pageRspParse)
                .build();
        return pageResult;
    }


    @Override
    public List<DcTaskInfoDO> getTaskByTaskList(DcTaskInfoDO taskInfo) {
        log.debug("detail param {}", taskInfo);
        DcTaskInfoPO parseData = DcTaskInfoPersistAssembler.toPersistObject(taskInfo);
        List<DcTaskInfoPO> queryRspData = mapper.getTaskByTaskList(parseData);
        log.debug("detail param {} return {}", taskInfo, queryRspData);
        List<DcTaskInfoDO> queryReturn = DcTaskInfoPersistAssembler.toListDomainEntity(queryRspData);
        return queryReturn;
    }

    @Override
    public DcTaskInfoDO detail(DcTaskInfoDO param) {
        log.debug("detail param {}", param);
        DcTaskInfoPO parseData = DcTaskInfoPersistAssembler.toPersistObject(param);
        DcTaskInfoPO queryRspData = mapper.detail(parseData);
        log.debug("detail param {} return {}", param, queryRspData);
        DcTaskInfoDO queryReturn = DcTaskInfoPersistAssembler.toDomainEntity(queryRspData);
        return queryReturn;
    }

}
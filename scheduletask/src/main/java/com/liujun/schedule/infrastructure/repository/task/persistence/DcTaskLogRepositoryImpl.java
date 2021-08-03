package com.liujun.schedule.infrastructure.repository.task.persistence;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liujun.schedule.domain.task.entity.DcTaskLogDO;
import com.liujun.schedule.domain.task.facade.DcTaskLogRepositoryInterface;
import com.liujun.schedule.infrastructure.repository.task.assembler.DcTaskLogPersistAssembler;
import com.liujun.schedule.infrastructure.repository.task.mapper.DcTaskLogMapper;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskLogPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务的日志信息-的领域存储接口实现
 *
 * @author liujun
 * @version 0.0.1
 */
@Repository
@Slf4j
public class DcTaskLogRepositoryImpl implements DcTaskLogRepositoryInterface {


    /**
     * 任务的日志信息表(dc_task_log)的数据库操作
     */
    @Autowired
    private DcTaskLogMapper mapper;

    @Override
    public boolean insert(DcTaskLogDO param) {
        log.debug("insert param {}", param);
        DcTaskLogPO parseData = DcTaskLogPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.insert(parseData);
        log.debug("insert param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean insertList(List<DcTaskLogDO> param) {
        log.debug("insertList param {}", param);
        List<DcTaskLogPO> parseData = DcTaskLogPersistAssembler.toListPersistObject(param);
        int updateRsp = mapper.insertList(parseData);
        log.debug("insertList param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean updateStatus(DcTaskLogDO param) {
        log.debug("update param {}", param);
        DcTaskLogPO parseData = DcTaskLogPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.updateStatus(parseData);
        log.debug("update param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean deleteByIds(DcTaskLogDO param) {
        log.debug("deleteByIds param {}", param);
        DcTaskLogPO parseData = DcTaskLogPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.deleteByIds(parseData);
        log.debug("deleteByIds param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public DomainPage<List<DcTaskLogDO>> queryPage(DomainPage<DcTaskLogDO> pageReq) {
        log.debug("queryPage param {}", pageReq);
        DcTaskLogPO parseData = DcTaskLogPersistAssembler.toPersistObject(pageReq.getData());
        Page<PageInfo> pageSet = PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<DcTaskLogPO> pageRsp = mapper.queryPage(parseData);
        log.debug("queryPage param {} return {}", pageReq, pageRsp);
        List<DcTaskLogDO> pageRspParse = DcTaskLogPersistAssembler.toListDomainEntity(pageRsp);
        DomainPage pageResult = DomainPage.<List<DcTaskLogDO>>builder()
                .page(pageSet.getPageNum())
                .size(pageSet.getPageSize())
                .total(pageSet.getTotal())
                .data(pageRspParse)
                .build();
        return pageResult;
    }

    @Override
    public DcTaskLogDO detail(DcTaskLogDO param) {
        log.debug("detail param {}", param);
        DcTaskLogPO parseData = DcTaskLogPersistAssembler.toPersistObject(param);
        DcTaskLogPO queryRspData = mapper.detail(parseData);
        log.debug("detail param {} return {}", param, queryRspData);
        DcTaskLogDO queryReturn = DcTaskLogPersistAssembler.toDomainEntity(queryRspData);
        return queryReturn;
    }

}
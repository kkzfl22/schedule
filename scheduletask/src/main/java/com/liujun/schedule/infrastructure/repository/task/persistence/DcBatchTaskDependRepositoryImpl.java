package com.liujun.schedule.infrastructure.repository.task.persistence;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDependDO;
import com.liujun.schedule.domain.task.facade.DcBatchTaskDependRepositoryInterface;
import com.liujun.schedule.infrastructure.repository.task.assembler.DcBatchTaskDependPersistAssembler;
import com.liujun.schedule.infrastructure.repository.task.mapper.DcBatchTaskDependMapper;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchTaskDependPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 批次中任务依赖关系-的领域存储接口实现
 *
 * @author liujun
 * @version 0.0.1
 */
@Repository
@Slf4j
public class DcBatchTaskDependRepositoryImpl implements DcBatchTaskDependRepositoryInterface {


    /**
     * 批次中任务依赖关系表(dc_batch_task_depend)的数据库操作
     */
    @Autowired
    private DcBatchTaskDependMapper mapper;

    @Override
    public boolean insert(DcBatchTaskDependDO param) {
        log.debug("insert param {}", param);
        DcBatchTaskDependPO parseData = DcBatchTaskDependPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.insert(parseData);
        log.debug("insert param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean insertList(List<DcBatchTaskDependDO> param) {
        log.debug("insertList param {}", param);
        List<DcBatchTaskDependPO> parseData = DcBatchTaskDependPersistAssembler.toListPersistObject(param);
        int updateRsp = mapper.insertList(parseData);
        log.debug("insertList param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }


    @Override
    public boolean deleteByIds(DcBatchTaskDependDO param) {
        log.debug("deleteByIds param {}", param);
        DcBatchTaskDependPO parseData = DcBatchTaskDependPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.deleteByIds(parseData);
        log.debug("deleteByIds param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public DomainPage<List<DcBatchTaskDependDO>> queryPage(DomainPage<DcBatchTaskDependDO> pageReq) {
        log.debug("queryPage param {}", pageReq);
        DcBatchTaskDependPO parseData = DcBatchTaskDependPersistAssembler.toPersistObject(pageReq.getData());
        Page<PageInfo> pageSet = PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<DcBatchTaskDependPO> pageRsp = mapper.queryPage(parseData);
        log.debug("queryPage param {} return {}", pageReq, pageRsp);
        List<DcBatchTaskDependDO> pageRspParse = DcBatchTaskDependPersistAssembler.toListDomainEntity(pageRsp);
        DomainPage pageResult = DomainPage.<List<DcBatchTaskDependDO>>builder()
                .page(pageSet.getPageNum())
                .size(pageSet.getPageSize())
                .total(pageSet.getTotal())
                .data(pageRspParse)
                .build();
        return pageResult;
    }

    @Override
    public List<DcBatchTaskDependDO> getDependByBatchId(DcBatchTaskDependDO param) {
        log.debug("queryPage param {}", param);
        DcBatchTaskDependPO parseData = DcBatchTaskDependPersistAssembler.toPersistObject(param);
        List<DcBatchTaskDependPO> pageRsp = mapper.queryPage(parseData);
        log.debug("queryPage param {} return {}", param, pageRsp);
        return DcBatchTaskDependPersistAssembler.toListDomainEntity(pageRsp);
    }





    @Override
    public DcBatchTaskDependDO detail(DcBatchTaskDependDO param) {
        log.debug("detail param {}", param);
        DcBatchTaskDependPO parseData = DcBatchTaskDependPersistAssembler.toPersistObject(param);
        DcBatchTaskDependPO queryRspData = mapper.detail(parseData);
        log.debug("detail param {} return {}", param, queryRspData);
        DcBatchTaskDependDO queryReturn = DcBatchTaskDependPersistAssembler.toDomainEntity(queryRspData);
        return queryReturn;
    }

}
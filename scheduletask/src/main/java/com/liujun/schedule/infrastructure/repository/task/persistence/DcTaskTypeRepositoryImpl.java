package com.liujun.schedule.infrastructure.repository.task.persistence;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liujun.schedule.domain.task.entity.DcTaskTypeDO;
import com.liujun.schedule.domain.task.facade.DcTaskTypeRepositoryInterface;
import com.liujun.schedule.infrastructure.repository.task.assembler.DcTaskTypePersistAssembler;
import com.liujun.schedule.infrastructure.repository.task.mapper.DcTaskTypeMapper;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskTypePO;
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
public class DcTaskTypeRepositoryImpl implements DcTaskTypeRepositoryInterface {


    /**
     * 调度任务信息表(dc_task_type)的数据库操作
     */
    @Autowired
    private DcTaskTypeMapper mapper;

    @Override
    public boolean insert(DcTaskTypeDO param) {
        log.debug("insert param {}", param);
        DcTaskTypePO parseData = DcTaskTypePersistAssembler.toPersistObject(param);
        int updateRsp = mapper.insert(parseData);
        log.debug("insert param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean insertList(List<DcTaskTypeDO> param) {
        log.debug("insertList param {}", param);
        List<DcTaskTypePO> parseData = DcTaskTypePersistAssembler.toListPersistObject(param);
        int updateRsp = mapper.insertList(parseData);
        log.debug("insertList param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean update(DcTaskTypeDO param) {
        log.debug("update param {}", param);
        DcTaskTypePO parseData = DcTaskTypePersistAssembler.toPersistObject(param);
        int updateRsp = mapper.update(parseData);
        log.debug("update param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean deleteByIds(DcTaskTypeDO param) {
        log.debug("deleteByIds param {}", param);
        DcTaskTypePO parseData = DcTaskTypePersistAssembler.toPersistObject(param);
        int updateRsp = mapper.deleteByIds(parseData);
        log.debug("deleteByIds param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public DomainPage<List<DcTaskTypeDO>> queryPage(DomainPage<DcTaskTypeDO> pageReq) {
        log.debug("queryPage param {}", pageReq);
        DcTaskTypePO parseData = DcTaskTypePersistAssembler.toPersistObject(pageReq.getData());
        Page<PageInfo> pageSet = PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<DcTaskTypePO> pageRsp = mapper.queryPage(parseData);
        log.debug("queryPage param {} return {}", pageReq, pageRsp);
        List<DcTaskTypeDO> pageRspParse = DcTaskTypePersistAssembler.toListDomainEntity(pageRsp);
        DomainPage pageResult = DomainPage.<List<DcTaskTypeDO>>builder()
                .page(pageSet.getPageNum())
                .size(pageSet.getPageSize())
                .total(pageSet.getTotal())
                .data(pageRspParse)
                .build();
        return pageResult;
    }


    @Override
    public List<DcTaskTypeDO> queryAll() {
        List<DcTaskTypePO> pageRsp = mapper.queryAll();
        List<DcTaskTypeDO> pageRspParse = DcTaskTypePersistAssembler.toListDomainEntity(pageRsp);

        return pageRspParse;
    }

    @Override
    public DcTaskTypeDO detail(DcTaskTypeDO param) {
        log.debug("detail param {}", param);
        DcTaskTypePO parseData = DcTaskTypePersistAssembler.toPersistObject(param);
        DcTaskTypePO queryRspData = mapper.detail(parseData);
        log.debug("detail param {} return {}", param, queryRspData);
        DcTaskTypeDO queryReturn = DcTaskTypePersistAssembler.toDomainEntity(queryRspData);
        return queryReturn;
    }

}
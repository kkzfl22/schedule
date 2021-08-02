package com.liujun.schedule.infrastructure.repository.task.persistence;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liujun.task.task.entity.DcBatchLogDO;
import com.liujun.task.task.repository.facade.DcBatchLogRepositoryInterface;
import com.liujun.schedule.infrastructure.repository.task.assembler.DcBatchLogPersistAssembler;
import com.liujun.schedule.infrastructure.repository.task.mapper.DcBatchLogMapper;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchLogPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 批次的日志批次状态-的领域存储接口实现
 *
 * @version 0.0.1
 * @author liujun
 */
@Repository
@Slf4j
public class DcBatchLogRepositoryImpl implements DcBatchLogRepositoryInterface {


    /**
     * 批次的日志批次状态表(dc_batch_log)的数据库操作
     */
    @Autowired
    private DcBatchLogMapper mapper;

    @Override
    public boolean insert(DcBatchLogDO param){
        log.debug("insert param {}",param);
        DcBatchLogPO parseData = DcBatchLogPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.insert(parseData);
        log.debug("insert param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean insertList(List<DcBatchLogDO> param){
        log.debug("insertList param {}",param);
        List<DcBatchLogPO> parseData = DcBatchLogPersistAssembler.toListPersistObject(param);
        int updateRsp = mapper.insertList(parseData);
        log.debug("insertList param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean updateStatus(DcBatchLogDO param){
        log.debug("update param {}",param);
        DcBatchLogPO parseData = DcBatchLogPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.updateStatus(parseData);
        log.debug("update param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean deleteByIds(DcBatchLogDO param){
        log.debug("deleteByIds param {}",param);
        DcBatchLogPO parseData = DcBatchLogPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.deleteByIds(parseData);
        log.debug("deleteByIds param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public DomainPage<List<DcBatchLogDO>> queryPage(DomainPage<DcBatchLogDO> pageReq){
        log.debug("queryPage param {}",pageReq);
        DcBatchLogPO parseData = DcBatchLogPersistAssembler.toPersistObject(pageReq.getData());
        Page<PageInfo> pageSet = PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<DcBatchLogPO> pageRsp = mapper.queryPage(parseData);
        log.debug("queryPage param {} return {}",pageReq,pageRsp);
        List<DcBatchLogDO> pageRspParse = DcBatchLogPersistAssembler.toListDomainEntity(pageRsp);
        DomainPage pageResult = DomainPage.<List<DcBatchLogDO>>builder()
                                        .page(pageSet.getPageNum())
                                        .size(pageSet.getPageSize())
                                        .total(pageSet.getTotal())
                                        .data(pageRspParse)
                                        .build();
        return pageResult;
    }

    @Override
    public DcBatchLogDO detail(DcBatchLogDO param){
        log.debug("detail param {}",param);
        DcBatchLogPO parseData = DcBatchLogPersistAssembler.toPersistObject(param);
        DcBatchLogPO queryRspData = mapper.detail(parseData);
        log.debug("detail param {} return {}",param,queryRspData);
        DcBatchLogDO queryReturn = DcBatchLogPersistAssembler.toDomainEntity(queryRspData);
        return queryReturn;
    }

}
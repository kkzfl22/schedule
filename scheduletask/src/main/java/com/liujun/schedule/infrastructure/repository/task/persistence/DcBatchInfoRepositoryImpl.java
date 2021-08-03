package com.liujun.schedule.infrastructure.repository.task.persistence;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liujun.schedule.domain.task.entity.DcBatchInfoDO;
import com.liujun.schedule.domain.task.facade.DcBatchInfoRepositoryInterface;
import com.liujun.schedule.infrastructure.repository.task.assembler.DcBatchInfoPersistAssembler;
import com.liujun.schedule.infrastructure.repository.task.mapper.DcBatchInfoMapper;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchInfoPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 批次信息-的领域存储接口实现
 *
 * @version 0.0.1
 * @author liujun
 */
@Repository
@Slf4j
public class DcBatchInfoRepositoryImpl implements DcBatchInfoRepositoryInterface {


    /**
     * 批次信息表(dc_batch_info)的数据库操作
     */
    @Autowired
    private DcBatchInfoMapper mapper;

    @Override
    public boolean insert(DcBatchInfoDO param){
        log.debug("insert param {}",param);
        DcBatchInfoPO parseData = DcBatchInfoPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.insert(parseData);
        log.debug("insert param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean insertList(List<DcBatchInfoDO> param){
        log.debug("insertList param {}",param);
        List<DcBatchInfoPO> parseData = DcBatchInfoPersistAssembler.toListPersistObject(param);
        int updateRsp = mapper.insertList(parseData);
        log.debug("insertList param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean updateRunStatus(DcBatchInfoDO param){
        log.debug("update param {}",param);
        DcBatchInfoPO parseData = DcBatchInfoPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.updateRunStatus(parseData);
        log.debug("update param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean deleteByIds(DcBatchInfoDO param){
        log.debug("deleteByIds param {}",param);
        DcBatchInfoPO parseData = DcBatchInfoPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.deleteByIds(parseData);
        log.debug("deleteByIds param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public DomainPage<List<DcBatchInfoDO>> queryPage(DomainPage<DcBatchInfoDO> pageReq){
        log.debug("queryPage param {}",pageReq);
        DcBatchInfoPO parseData = DcBatchInfoPersistAssembler.toPersistObject(pageReq.getData());
        Page<PageInfo> pageSet = PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<DcBatchInfoPO> pageRsp = mapper.queryPage(parseData);
        log.debug("queryPage param {} return {}",pageReq,pageRsp);
        List<DcBatchInfoDO> pageRspParse = DcBatchInfoPersistAssembler.toListDomainEntity(pageRsp);
        DomainPage pageResult = DomainPage.<List<DcBatchInfoDO>>builder()
                                        .page(pageSet.getPageNum())
                                        .size(pageSet.getPageSize())
                                        .total(pageSet.getTotal())
                                        .data(pageRspParse)
                                        .build();
        return pageResult;
    }

    @Override
    public DcBatchInfoDO detail(DcBatchInfoDO param){
        log.debug("detail param {}",param);
        DcBatchInfoPO parseData = DcBatchInfoPersistAssembler.toPersistObject(param);
        DcBatchInfoPO queryRspData = mapper.detail(parseData);
        log.debug("detail param {} return {}",param,queryRspData);
        DcBatchInfoDO queryReturn = DcBatchInfoPersistAssembler.toDomainEntity(queryRspData);
        return queryReturn;
    }

}
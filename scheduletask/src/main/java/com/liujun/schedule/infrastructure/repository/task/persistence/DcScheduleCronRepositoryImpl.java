package com.liujun.schedule.infrastructure.repository.task.persistence;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liujun.schedule.domain.task.entity.DcScheduleCronDO;
import com.liujun.schedule.domain.task.facade.DcScheduleCronRepositoryInterface;
import com.liujun.schedule.infrastructure.repository.task.assembler.DcScheduleCronPersistAssembler;
import com.liujun.schedule.infrastructure.repository.task.mapper.DcScheduleCronMapper;
import com.liujun.schedule.infrastructure.repository.task.po.DcScheduleCronPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 调度的CRON-达式信息-的领域存储接口实现
 *
 * @version 0.0.1
 * @author liujun
 */
@Repository
@Slf4j
public class DcScheduleCronRepositoryImpl implements DcScheduleCronRepositoryInterface {


    /**
     * 调度的CRON表达式信息表(dc_schedule_cron)的数据库操作
     */
    @Autowired
    private DcScheduleCronMapper mapper;

    @Override
    public boolean insert(DcScheduleCronDO param){
        log.debug("insert param {}",param);
        DcScheduleCronPO parseData = DcScheduleCronPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.insert(parseData);
        log.debug("insert param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean insertList(List<DcScheduleCronDO> param){
        log.debug("insertList param {}",param);
        List<DcScheduleCronPO> parseData = DcScheduleCronPersistAssembler.toListPersistObject(param);
        int updateRsp = mapper.insertList(parseData);
        log.debug("insertList param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean update(DcScheduleCronDO param){
        log.debug("update param {}",param);
        DcScheduleCronPO parseData = DcScheduleCronPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.update(parseData);
        log.debug("update param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean deleteByIds(DcScheduleCronDO param){
        log.debug("deleteByIds param {}",param);
        DcScheduleCronPO parseData = DcScheduleCronPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.deleteByIds(parseData);
        log.debug("deleteByIds param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public DomainPage<List<DcScheduleCronDO>> queryPage(DomainPage<DcScheduleCronDO> pageReq){
        log.debug("queryPage param {}",pageReq);
        DcScheduleCronPO parseData = DcScheduleCronPersistAssembler.toPersistObject(pageReq.getData());
        Page<PageInfo> pageSet = PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<DcScheduleCronPO> pageRsp = mapper.queryPage(parseData);
        log.debug("queryPage param {} return {}",pageReq,pageRsp);
        List<DcScheduleCronDO> pageRspParse = DcScheduleCronPersistAssembler.toListDomainEntity(pageRsp);
        DomainPage pageResult = DomainPage.<List<DcScheduleCronDO>>builder()
                                        .page(pageSet.getPageNum())
                                        .size(pageSet.getPageSize())
                                        .total(pageSet.getTotal())
                                        .data(pageRspParse)
                                        .build();
        return pageResult;
    }

    @Override
    public DcScheduleCronDO detail(DcScheduleCronDO param){
        log.debug("detail param {}",param);
        DcScheduleCronPO parseData = DcScheduleCronPersistAssembler.toPersistObject(param);
        DcScheduleCronPO queryRspData = mapper.detail(parseData);
        log.debug("detail param {} return {}",param,queryRspData);
        DcScheduleCronDO queryReturn = DcScheduleCronPersistAssembler.toDomainEntity(queryRspData);
        return queryReturn;
    }

}
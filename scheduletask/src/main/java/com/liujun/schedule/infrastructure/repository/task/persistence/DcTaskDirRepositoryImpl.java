package com.liujun.schedule.infrastructure.repository.task.persistence;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liujun.task.task.entity.DcTaskDirDO;
import com.liujun.task.task.repository.facade.DcTaskDirRepositoryInterface;
import com.liujun.schedule.infrastructure.repository.task.assembler.DcTaskDirPersistAssembler;
import com.liujun.schedule.infrastructure.repository.task.mapper.DcTaskDirMapper;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskDirPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务目录信息-的领域存储接口实现
 *
 * @version 0.0.1
 * @author liujun
 */
@Repository
@Slf4j
public class DcTaskDirRepositoryImpl implements DcTaskDirRepositoryInterface {


    /**
     * 任务目录信息表(dc_task_dir)的数据库操作
     */
    @Autowired
    private DcTaskDirMapper mapper;

    @Override
    public boolean insert(DcTaskDirDO param){
        log.debug("insert param {}",param);
        DcTaskDirPO parseData = DcTaskDirPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.insert(parseData);
        log.debug("insert param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean insertList(List<DcTaskDirDO> param){
        log.debug("insertList param {}",param);
        List<DcTaskDirPO> parseData = DcTaskDirPersistAssembler.toListPersistObject(param);
        int updateRsp = mapper.insertList(parseData);
        log.debug("insertList param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean update(DcTaskDirDO param){
        log.debug("update param {}",param);
        DcTaskDirPO parseData = DcTaskDirPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.update(parseData);
        log.debug("update param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean deleteByIds(DcTaskDirDO param){
        log.debug("deleteByIds param {}",param);
        DcTaskDirPO parseData = DcTaskDirPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.deleteByIds(parseData);
        log.debug("deleteByIds param {} return {}",param,updateRsp);
        return updateRsp > 0;
    }

    @Override
    public DomainPage<List<DcTaskDirDO>> queryPage(DomainPage<DcTaskDirDO> pageReq){
        log.debug("queryPage param {}",pageReq);
        DcTaskDirPO parseData = DcTaskDirPersistAssembler.toPersistObject(pageReq.getData());
        Page<PageInfo> pageSet = PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<DcTaskDirPO> pageRsp = mapper.queryPage(parseData);
        log.debug("queryPage param {} return {}",pageReq,pageRsp);
        List<DcTaskDirDO> pageRspParse = DcTaskDirPersistAssembler.toListDomainEntity(pageRsp);
        DomainPage pageResult = DomainPage.<List<DcTaskDirDO>>builder()
                                        .page(pageSet.getPageNum())
                                        .size(pageSet.getPageSize())
                                        .total(pageSet.getTotal())
                                        .data(pageRspParse)
                                        .build();
        return pageResult;
    }

    @Override
    public DcTaskDirDO detail(DcTaskDirDO param){
        log.debug("detail param {}",param);
        DcTaskDirPO parseData = DcTaskDirPersistAssembler.toPersistObject(param);
        DcTaskDirPO queryRspData = mapper.detail(parseData);
        log.debug("detail param {} return {}",param,queryRspData);
        DcTaskDirDO queryReturn = DcTaskDirPersistAssembler.toDomainEntity(queryRspData);
        return queryReturn;
    }

}
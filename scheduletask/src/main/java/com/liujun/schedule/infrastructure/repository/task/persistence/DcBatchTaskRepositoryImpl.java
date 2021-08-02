package com.liujun.schedule.infrastructure.repository.task.persistence;

import com.liujun.task.task.entity.DcBatchTaskDO;
import com.liujun.task.task.repository.facade.DcBatchTaskRepositoryInterface;
import com.liujun.schedule.infrastructure.repository.task.assembler.DcBatchTaskPersistAssembler;
import com.liujun.schedule.infrastructure.repository.task.mapper.DcBatchTaskMapper;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchTaskPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 批次任务信息-的领域存储接口实现
 *
 * @author liujun
 * @version 0.0.1
 */
@Repository
@Slf4j
public class DcBatchTaskRepositoryImpl implements DcBatchTaskRepositoryInterface {


    /**
     * 批次任务信息表(dc_batch_task)的数据库操作
     */
    @Autowired
    private DcBatchTaskMapper mapper;

    @Override
    public boolean insert(DcBatchTaskDO param) {
        log.debug("insert param {}", param);
        DcBatchTaskPO parseData = DcBatchTaskPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.insert(parseData);
        log.debug("insert param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public boolean insertList(List<DcBatchTaskDO> param) {
        log.debug("insertList param {}", param);
        List<DcBatchTaskPO> parseData = DcBatchTaskPersistAssembler.toListPersistObject(param);
        int updateRsp = mapper.insertList(parseData);
        log.debug("insertList param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }


    @Override
    public boolean deleteById(DcBatchTaskDO param) {
        log.debug("deleteByIds param {}", param);
        DcBatchTaskPO parseData = DcBatchTaskPersistAssembler.toPersistObject(param);
        int updateRsp = mapper.deleteByIds(parseData);
        log.debug("deleteByIds param {} return {}", param, updateRsp);
        return updateRsp > 0;
    }

    @Override
    public List<DcBatchTaskDO> getTaskListByBatchId(Long batchId) {
        log.debug("queryPage param {}", batchId);

        List<DcBatchTaskPO> pageRsp = mapper.getTaskListByBatchId(batchId);
        log.debug("queryPage param {} return {}", batchId, pageRsp);
        List<DcBatchTaskDO> pageRspParse = DcBatchTaskPersistAssembler.toListDomainEntity(pageRsp);

        return pageRspParse;
    }

    @Override
    public DcBatchTaskDO detail(DcBatchTaskDO param) {
        log.debug("detail param {}", param);
        DcBatchTaskPO parseData = DcBatchTaskPersistAssembler.toPersistObject(param);
        DcBatchTaskPO queryRspData = mapper.detail(parseData);
        log.debug("detail param {} return {}", param, queryRspData);
        DcBatchTaskDO queryReturn = DcBatchTaskPersistAssembler.toDomainEntity(queryRspData);
        return queryReturn;
    }

}
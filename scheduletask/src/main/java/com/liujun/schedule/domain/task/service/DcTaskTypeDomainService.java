package com.liujun.schedule.domain.task.service;

import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.schedule.domain.task.entity.DcTaskTypeDO;
import com.liujun.schedule.domain.task.facade.DcTaskTypeRepositoryInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调度任务信息-的领域服务
 *
 * @author liujun
 * @version 0.0.1
 */
@Service
@Slf4j
public class DcTaskTypeDomainService {


    /**
     * 调度任务信息-的领域存储接口
     */
    @Autowired
    private DcTaskTypeRepositoryInterface repository;

    /**
     * 单个添加
     *
     * @param param 调度任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insert(DcTaskTypeDO param) {
        boolean updateRsp = repository.insert(param);
        return updateRsp;
    }

    /**
     * 批量添加
     *
     * @param param 调度任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean insertList(List<DcTaskTypeDO> param) {
        boolean updateRsp = repository.insertList(param);
        return updateRsp;
    }

    /**
     * 修改方法
     *
     * @param param 调度任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean update(DcTaskTypeDO param) {
        boolean updateRsp = repository.update(param);
        return updateRsp;
    }

    /**
     * 批量删除
     *
     * @param param 调度任务信息-的领域实体信息
     * @return true 操作成功,false 操作失败
     */
    public boolean deleteByIds(DcTaskTypeDO param) {
        boolean updateRsp = repository.deleteByIds(param);
        return updateRsp;
    }

    /**
     * 分页查询
     *
     * @param pageReq 分页查询请求参数
     * @return 分页查询响应
     */
    public DomainPage<List<DcTaskTypeDO>> queryPage(DomainPage<DcTaskTypeDO> pageReq) {
        DomainPage<List<DcTaskTypeDO>> pageResult = repository.queryPage(pageReq);
        return pageResult;
    }

    /**
     * 获取类型的配制信息
     *
     * @return
     */
    public Map<String, DcTaskTypeDO> queryAllToMap() {
        List<DcTaskTypeDO> list = repository.queryAll();

        if (null == list) {
            return Collections.emptyMap();
        }

        Map<String, DcTaskTypeDO> result = new HashMap<>(list.size());
        for (DcTaskTypeDO item : list) {
            result.put(item.getType(), item);
        }

        return result;
    }


    /**
     * 按id查询详细
     *
     * @param param 调度任务信息-的领域实体信息
     * @return 数据集
     */
    public DcTaskTypeDO detail(DcTaskTypeDO param) {
        DcTaskTypeDO queryReturn = repository.detail(param);
        return queryReturn;
    }

}
package com.liujun.schedule.application.taskflow.flow.batch;

import cn.hutool.core.collection.CollUtil;
import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.task.task.entity.DcBatchTaskDO;
import com.liujun.task.task.entity.DcBatchTaskDependDO;
import com.liujun.task.task.entity.DcTaskInfoDO;
import com.liujun.task.task.entity.DcTaskTypeDO;
import com.liujun.task.task.service.DcBatchTaskDependDomainService;
import com.liujun.task.task.service.DcBatchTaskDomainService;
import com.liujun.task.task.service.DcTaskInfoDomainService;
import com.liujun.task.task.service.DcTaskTypeDomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取关联的批次的任务的所有信息
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/12
 */
@Service("getBatchAllData")
public class GetBatchAllInfoList implements FlowInf {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(GetBatchAllInfoList.class);

    /**
     * 批次任务
     */
    @Autowired
    private DcBatchTaskDomainService batchTaskService;

    /**
     * 任务信息的主表
     */
    @Autowired
    private DcTaskInfoDomainService taskInfoDomainService;


    /**
     * 类型信息
     */
    @Autowired
    private DcTaskTypeDomainService typeDomainService;


    /**
     * 依赖关系
     */
    @Autowired
    private DcBatchTaskDependDomainService taskDependService;

    @Override
    public boolean invokeFlow(ContextContainer context) {

        Long batchId = context.getObject(BatchFLowEnum.INPUT_BATCH_ID.name());

        // 1,根据批次查询所有关联的任务
        List<DcBatchTaskDO> taskDataList = batchTaskService.getTaskListByBatchId(batchId);
        if (CollUtil.isEmpty(taskDataList)) {
            logger.info("flow batch sqlmap job list is empty");
            return false;
        }

        // 记录下所有的任务的id
        context.put(BatchFLowEnum.PROC_DATA_BATCH_TASK_ID.name(), taskDataList);

        // 获取任务的id
        List<Long> taskIds = this.getTaskIds(taskDataList);

        // 2,根据任务的id查询出所有的任务信息
        Map<Long, DcTaskInfoDO> dataSchedule = this.getScheduleTask(taskIds);
        if (CollUtil.isEmpty(dataSchedule)) {
            logger.info("flow schedule sqlmap task list result is empty");
            return false;
        }

        // 将当前的调度任务信息放入到流程
        context.put(BatchFLowEnum.PROC_DATA_BATCH_TASK.name(), dataSchedule);

        Map<String, DcTaskTypeDO> map = typeDomainService.queryAllToMap();
        if (CollUtil.isEmpty(map)) {
            logger.info("flow schedule Task Type list result is empty");
            return false;
        }

        context.put(BatchFLowEnum.PROC_DATA_TYPE_MAP.name(), map);


        // 通过任务获取数据
        List<DcBatchTaskDependDO> dependList = this.loadDepend(batchId);
        context.put(BatchFLowEnum.PROC_DATA_DEPEND_LINK.name(), dependList);


        return true;
    }


    /**
     * @param batchTaskList
     * @return
     */
    private List<Long> getTaskIds(List<DcBatchTaskDO> batchTaskList) {
        if (CollUtil.isEmpty(batchTaskList)) {
            return Collections.emptyList();
        }

        List<Long> taskIds = new ArrayList<>(batchTaskList.size());

        for (DcBatchTaskDO taskInfo : batchTaskList) {
            taskIds.add(taskInfo.getTaskId());
        }

        return taskIds;
    }


    /**
     * 根据任务去查询信息
     *
     * @param taskIds 任务的id
     * @return 查询的任务信息
     */
    private Map<Long, DcTaskInfoDO> getScheduleTask(List<Long> taskIds) {

        Map<Long, DcTaskInfoDO> dataRsp = new HashMap<>(0);

        if (CollUtil.isEmpty(taskIds)) {
            return dataRsp;
        }

        DcTaskInfoDO queryScheduleData = new DcTaskInfoDO();
        queryScheduleData.setTaskList(taskIds);
        List<DcTaskInfoDO> queryData = taskInfoDomainService.getTaskByTaskList(queryScheduleData);

        if (null != queryData && !queryData.isEmpty()) {
            Map<Long, DcTaskInfoDO> dataMap = new HashMap<>(queryData.size());
            for (DcTaskInfoDO scheduleItem : queryData) {
                dataMap.put(scheduleItem.getTaskId(), scheduleItem);
            }
            return dataMap;
        }

        return dataRsp;
    }


    /**
     * 离开载数据关系
     *
     * @param batchId
     * @return
     */
    private List<DcBatchTaskDependDO> loadDepend(Long batchId) {
        DcBatchTaskDependDO queryBean = new DcBatchTaskDependDO();
        queryBean.setBatchId(batchId);
        List<DcBatchTaskDependDO> listData = taskDependService.getDependByBatchId(queryBean);

        if (CollUtil.isEmpty(listData)) {
            return Collections.emptyList();
        }

        return listData;
    }


}

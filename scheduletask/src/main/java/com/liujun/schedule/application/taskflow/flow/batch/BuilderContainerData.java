package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.application.taskflow.container.TaskContainerMap;
import com.liujun.schedule.application.taskflow.graph.ContainData;
import com.liujun.schedule.application.taskflow.graph.GraphEtl;
import com.liujun.schedule.application.taskflow.graph.GraphPointData;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDO;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDependDO;
import com.liujun.schedule.domain.task.entity.DcTaskInfoDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 构建内存中的容器对象
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/11
 */
@Service
public class BuilderContainerData implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(BuilderContainerData.class);

    @Override
    public boolean invokeFlow(ContextContainer context) {

        logger.info("builder container start ");

        Long batchId = context.getObject(BatchFLowEnum.INPUT_BATCH_ID.name());

        // 1,获取批次下所有的任务的id
        List<DcBatchTaskDO> taskDataList =
                context.getObject(BatchFLowEnum.PROC_DATA_BATCH_TASK_ID.name());

        // 2,获取任务的关联
        List<DcBatchTaskDependDO> dependdList =
                context.getObject(BatchFLowEnum.PROC_DATA_DEPEND_LINK.name());

        // 构建图所依赖的相关信息
        Map<Long, GraphPointData> graphMap =
                GraphEtl.INSTANCE.buildDependEtl(taskDataList, dependdList);

        //查询所有的任务
        Map<Long, DcTaskInfoDO> taskMap =
                context.getObject(BatchFLowEnum.PROC_DATA_TYPE_MAP.name());


        // 获取当前调度任务信息
        Map<Long, DcTaskInfoDO> dataScheduleMap =
                context.getObject(BatchFLowEnum.PROC_DATA_BATCH_TASK.name());

        ContainData contain = new ContainData(batchId, dataScheduleMap, graphMap, null);
        TaskContainerMap.INSTANCE.put(batchId, contain);

        // 将初始化要启的顶点找出来
        List<Long> firstInVertext = GraphEtl.INSTANCE.getInputVertex(taskDataList, dependdList);
        context.put(BatchFLowEnum.PROC_DATA_FIRST_IN_VERTEX.name(), firstInVertext);

        logger.info("builder container finish ");

        return true;
    }
}

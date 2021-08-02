package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.application.taskflow.container.TaskContainerMap;
import com.liujun.schedule.application.taskflow.flow.task.ThreadTaskRunFlow;
import com.liujun.schedule.application.taskflow.graph.ContainData;
import com.liujun.schedule.application.taskflow.graph.GraphEtl;
import com.liujun.schedule.application.taskflow.graph.GraphPointData;
import com.liujun.task.task.entity.DcBatchInfoDO;
import com.liujun.task.task.entity.DcBatchTaskDO;
import com.liujun.task.task.entity.DcBatchTaskDependDO;
import com.liujun.task.task.entity.DcTaskInfoDO;
import com.liujun.task.task.entity.DcTaskTypeDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service("builderContainerData")
public class BuilderContainerData implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(BuilderContainerData.class);


    /**
     * 通过xml配制的文件流程进行注入
     */
    @Autowired
    private ThreadTaskRunFlow runTaskFlow;


    @Override
    public boolean invokeFlow(ContextContainer context) {

        logger.info("builder container start ");

        Long batchId = context.getObject(BatchFLowEnum.INPUT_BATCH_ID.name());

        // 1,获取批次下所有的任务的id
        List<DcBatchTaskDO> taskDataList =
                context.getObject(BatchFLowEnum.PROC_DATA_BATCH_TASK_ID.name());

        // 2,获取任务的关联
        List<DcBatchTaskDependDO> dependList =
                context.getObject(BatchFLowEnum.PROC_DATA_DEPEND_LINK.name());

        // 构建图所依赖的相关信息
        Map<Long, GraphPointData> graphMap =
                GraphEtl.INSTANCE.buildDependEtl(taskDataList, dependList);

        //查询所有的任务
        Map<String, DcTaskTypeDO> typeMap =
                context.getObject(BatchFLowEnum.PROC_DATA_TYPE_MAP.name());

        // 获取当前调度任务信息
        Map<Long, DcTaskInfoDO> dataScheduleMap =
                context.getObject(BatchFLowEnum.PROC_DATA_BATCH_TASK.name());

        //批次信息
        DcBatchInfoDO batchInfo = context.getObject(BatchFLowEnum.PROC_BATCH_INFO.name());


        ContainData contain = new ContainData(dataScheduleMap, graphMap, typeMap, batchInfo, runTaskFlow);
        TaskContainerMap.INSTANCE.put(batchId, contain);

        // 将初始化要启动的顶点找出来
        List<Long> firstVertex = GraphEtl.INSTANCE.getInputVertex(taskDataList, dependList);
        context.put(BatchFLowEnum.PROC_DATA_FIRST_IN_VERTEX.name(), firstVertex);

        logger.info("builder container finish ");

        return true;
    }
}

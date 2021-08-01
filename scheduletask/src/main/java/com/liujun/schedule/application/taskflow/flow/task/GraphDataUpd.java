package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.application.taskflow.container.TaskContainerMap;
import com.liujun.schedule.application.taskflow.graph.ContainData;
import com.liujun.schedule.application.taskflow.graph.GraphPointData;
import com.liujun.schedule.application.taskflow.thread.SubmitTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 进行图计算数据的更新操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/14
 */
@Service
public class GraphDataUpd implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(GraphDataUpd.class);


    @Override
    public boolean invokeFlow(ContextContainer context) {

        Boolean finishFlag = context.getObject(ThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        // 当前流程的状态为未完成，则继续
        if (null != finishFlag && !finishFlag) {

            // 1,获取当前任务的执行结果
            boolean runRsp = context.getObject(ThreadTaskEnum.PROC_TASK_RSP_FLAG.name());

            // 如果当前任务执行成功，则继续
            if (runRsp) {
                // 1，通过当前任务，获取后续的任务
                Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
                Long taskId = context.getObject(ThreadTaskEnum.INPUT_TASK_ID.name());
                Long runFlag = context.getObject(ThreadTaskEnum.INPUT_RUNTIME_FLAG.name());

                // 获取数据
                ContainData conData = TaskContainerMap.INSTANCE.get(batchId);

                if (null == conData) {
                    logger.info("GraphDataUpd data container is null contain ");
                    return false;
                }

                Map<Long, GraphPointData> graphMap = conData.getGraphTaskPointMap();

                // 进行数据的获取操作
                GraphPointData graphData = graphMap.get(taskId);

                for (Long pointData : graphData.getOutPointLink()) {
                    // 将顶点的入度减1
                    graphMap.get(pointData).getInPointLinkNum().decrementAndGet();
                }

                logger.info(
                        "GraphDataUpd data container is sumbit data point list  :{} ",
                        graphData.getOutPointLink());

                // 遍历检查后续任务是否需要进行启动
                for (Long pointData : graphData.getOutPointLink()) {

                    logger.info(
                            "GraphDataUpd data container is sumbit data point inPointLink num  :{} ",
                            graphMap.get(pointData).getInPointLinkNum().get());

                    // 检查顶点的入度是否为0，为0表示当前需要启动此任务
                    if (graphMap.get(pointData).getInPointLinkNum().get() == 0) {
                        SubmitTask.INSTANCE.submit(batchId, pointData, runFlag);
                    }
                }
            }
        }

        return true;
    }
}

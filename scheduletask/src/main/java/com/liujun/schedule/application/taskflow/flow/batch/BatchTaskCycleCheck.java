package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.application.taskflow.constant.GraphPointEnum;
import com.liujun.schedule.application.taskflow.graph.GraphEtl;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDO;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDependDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 批次下的任务环的检查
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/15
 */
@Service
public class BatchTaskCycleCheck implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(BatchTaskCycleCheck.class);

    @Override
    public boolean invokeFlow(ContextContainer context) {

        logger.info("batch sqlmap.sqlmap.job cycle check start ");

        // 1,获取批次下所有的任务的id
        List<DcBatchTaskDO> taskDataList =
                context.getObject(BatchFLowEnum.PROC_DATA_BATCH_TASK_ID.name());

        // 2,获取任务的关联
        List<DcBatchTaskDependDO> dependdList =
                context.getObject(BatchFLowEnum.PROC_DATA_DEPEND_LINK.name());

        // 将检查环路顶点
        Long cycleVertex = GraphEtl.INSTANCE.graphCycleCheck(taskDataList, dependdList);

        // 如果不存在依赖环，则继续
        if (GraphPointEnum.NOT_CYCLE_FLAG.getPoint().equals(cycleVertex)) {
            logger.info("batch sqlmap.sqlmap.job cycle check finish  not cycle");
            return true;
        }

        logger.info("batch sqlmap.sqlmap.job cycle check finish exists cycle  point {} ", cycleVertex);
        return false;
    }
}

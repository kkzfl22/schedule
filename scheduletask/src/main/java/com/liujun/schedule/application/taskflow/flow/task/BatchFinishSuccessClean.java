package com.liujun.schedule.application.taskflow.flow.task;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.ThreadTaskEnum;
import com.liujun.schedule.application.taskflow.container.TaskContainerMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 批次操作完成，将状态改为成功
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
@Service("batchFinishSuccessClean")
public class BatchFinishSuccessClean implements FlowInf {

    private Logger logger = LoggerFactory.getLogger(BatchFinishSuccessClean.class);

    @Override
    public boolean invokeFlow(ContextContainer context) {
        Boolean finishFlag = context.getObject(ThreadTaskEnum.PROC_RUN_FINISH_FLAG.name());

        if (null != finishFlag && finishFlag) {
            logger.info("thread task job flow finish success clean  start ...");
            Long batchId = context.getObject(ThreadTaskEnum.INPUT_BATCH_ID.name());
            // 将批次相关的信息进行移除操作
            TaskContainerMap.INSTANCE.remove(batchId);

            logger.info("thread task job flow finish success clean rsp finish ");
        }
        return true;
    }
}

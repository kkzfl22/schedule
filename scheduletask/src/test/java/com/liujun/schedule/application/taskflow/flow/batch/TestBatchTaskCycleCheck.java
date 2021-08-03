package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDO;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDependDO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行环路检查
 *
 * @author liujun
 * @since 2021/7/30
 */
@SpringBootTest(classes = {BatchTaskCycleCheck.class})
public class TestBatchTaskCycleCheck {


    /**
     * 环路检查
     */
    @Autowired
    @Qualifier("taskCycleCheck")
    private FlowInf taskCycleCheck;

    private long batchId = 10001;


    /**
     * 无环的测试
     */
    @Test
    public void notCycle() {
        ContextContainer container = new ContextContainer();

        container.put(BatchFLowEnum.PROC_DATA_BATCH_TASK_ID.name(), dataBuilder());
        container.put(BatchFLowEnum.PROC_DATA_DEPEND_LINK.name(), buildTaskNotCycle());
        FlowCommonData.addRunTask(container);
        boolean rsp = taskCycleCheck.invokeFlow(container);
        Assertions.assertEquals(true, rsp);
    }

    /**
     * 环路依赖的测试
     */
    @Test
    public void cycle() {
        ContextContainer container = new ContextContainer();

        container.put(BatchFLowEnum.PROC_DATA_BATCH_TASK_ID.name(), dataBuilder());
        container.put(BatchFLowEnum.PROC_DATA_DEPEND_LINK.name(), buildTaskCycle());
        FlowCommonData.addRunTask(container);
        boolean rsp = taskCycleCheck.invokeFlow(container);
        Assertions.assertEquals(false, rsp);
    }


    /**
     * 无环依赖
     *
     * @return
     */
    private List<DcBatchTaskDependDO> buildTaskNotCycle() {
        List<DcBatchTaskDependDO> data = new ArrayList<>();
        data.add(this.dataDepend(100, 101));
        data.add(this.dataDepend(101, 102));
        data.add(this.dataDepend(102, 103));
        data.add(this.dataDepend(103, 104));
        data.add(this.dataDepend(104, 105));
        return data;
    }

    /**
     * 有环依赖
     *
     * @return
     */
    private List<DcBatchTaskDependDO> buildTaskCycle() {
        List<DcBatchTaskDependDO> data = new ArrayList<>();
        data.add(this.dataDepend(100, 101));
        data.add(this.dataDepend(101, 102));
        data.add(this.dataDepend(102, 103));
        data.add(this.dataDepend(103, 104));
        data.add(this.dataDepend(104, 105));
        data.add(this.dataDepend(105, 100));
        return data;
    }

    private DcBatchTaskDependDO dataDepend(long taskId, long dependId) {
        DcBatchTaskDependDO data = new DcBatchTaskDependDO();
        data.setBatchId(batchId);
        data.setTaskId(taskId);
        data.setPrevTaskId(dependId);
        return data;
    }


    private List<DcBatchTaskDO> dataBuilder() {
        List<DcBatchTaskDO> data = new ArrayList<>();


        data.add(this.batchTask(batchId, 100));
        data.add(this.batchTask(batchId, 101));
        data.add(this.batchTask(batchId, 102));
        data.add(this.batchTask(batchId, 103));
        data.add(this.batchTask(batchId, 104));
        data.add(this.batchTask(batchId, 105));

        return data;
    }


    private DcBatchTaskDO batchTask(long batchId, long taskId) {
        DcBatchTaskDO batchTask = new DcBatchTaskDO();

        batchTask.setBatchId(batchId);
        batchTask.setTaskId(taskId);

        return batchTask;
    }


}

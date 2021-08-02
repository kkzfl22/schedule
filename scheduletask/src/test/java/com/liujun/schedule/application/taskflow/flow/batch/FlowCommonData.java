package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;

/**
 * 公共测试数据
 *
 * @author liujun
 * @since 2021/7/30
 */
public class FlowCommonData {


    private FlowCommonData() {

    }

    /**
     * 添加运行标识
     *
     * @param container 容器对象
     */
    public static final void addRunTask(ContextContainer container) {
        container.put(BatchFLowEnum.PROC_RUNTIME_FLAG.name(), System.currentTimeMillis());
    }

}

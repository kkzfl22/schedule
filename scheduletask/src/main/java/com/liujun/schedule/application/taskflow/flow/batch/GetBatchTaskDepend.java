package com.liujun.schedule.application.taskflow.flow.batch;

import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDependDO;
import com.liujun.schedule.domain.task.service.DcBatchTaskDependDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载批次的任务依赖
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/12
 */
@Service
public class GetBatchTaskDepend implements FlowInf {

    /**
     * 依赖关系
     */
    @Autowired
    private DcBatchTaskDependDomainService taskDependService;

    @Override
    public boolean invokeFlow(ContextContainer context) {

        // 获取批次的id
        Long batchId = context.getObject(BatchFLowEnum.INPUT_BATCH_ID.name());

        // 通过任务获取数据
        List<DcBatchTaskDependDO> dependdList = this.loadDepend(batchId);

        context.put(BatchFLowEnum.PROC_DATA_DEPEND_LINK.name(), dependdList);

        return true;
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
        List<DcBatchTaskDependDO> listdata = null;//taskDependService.query(queryBean);

        if (null == listdata) {
            return new ArrayList<>(0);
        }

        return listdata;
    }
}

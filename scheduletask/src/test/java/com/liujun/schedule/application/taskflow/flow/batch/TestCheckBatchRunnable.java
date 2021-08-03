package com.liujun.schedule.application.taskflow.flow.batch;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.domain.task.constant.BatchRunStatusEnum;
import com.liujun.schedule.domain.task.entity.DcBatchInfoDO;
import com.liujun.schedule.domain.task.service.DcBatchInfoDomainService;
import com.liujun.schedule.domain.task.service.TestDcBatchInfoDomainService;
import com.liujun.schedule.infrastructure.comm.uid.UidGenerator;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcBatchInfoRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 测试状态的检查 操作
 *
 * @author liujun
 * @since 2021/7/30
 */
@SpringBootTest(classes = {CheckBatchRunnable.class, DruidDataSourceAutoConfigure.class, MybatisAutoConfiguration.class, MyBatisScanConfiguration.class})
@Transactional(transactionManager = "testTransactionManager")
@Import(value = {DcBatchInfoDomainService.class, DcBatchInfoRepositoryImpl.class, UidGenerator.class})
@TestPropertySource("classpath:application.yml")
public class TestCheckBatchRunnable {


    /**
     * 批次信息-的领域服务
     */
    @Autowired
    private DcBatchInfoDomainService batchDomainService;

    /**
     * 检查
     */
    @Autowired
    @Qualifier("checkBatchRunnable")
    private FlowInf checkBatchRunnable;


    /**
     * 进行批次任务的测试，运行成功
     */
    @Test
    public void runBatchSuccess() {
        DcBatchInfoDO batchInfo = this.getBatchInfo(BatchRunStatusEnum.INIT.getStatus());

        try {
            ContextContainer container = new ContextContainer();


            container.put(BatchFLowEnum.INPUT_BATCH_ID.name(), batchInfo.getBatchId());
            FlowCommonData.addRunTask(container);

            boolean rsp = checkBatchRunnable.invokeFlow(container);
            Assertions.assertEquals(true, rsp);
        } finally {
            batchInfo.setBatchList(Arrays.asList(batchInfo.getBatchId()));
            Boolean deleteRsp = batchDomainService.deleteByIds(batchInfo);
            Assertions.assertEquals(true, deleteRsp);
        }
    }


    /**
     * 进行批次任务的测试
     */
    @Test
    public void runBatchFail() {

        DcBatchInfoDO batchInfo = this.getBatchInfo(BatchRunStatusEnum.RUNNING.getStatus());
        try {
            ContextContainer container = new ContextContainer();
            container.put(BatchFLowEnum.INPUT_BATCH_ID.name(), batchInfo.getBatchId());
            FlowCommonData.addRunTask(container);

            boolean rsp = checkBatchRunnable.invokeFlow(container);
            Assertions.assertEquals(false, rsp);
        } finally {
            batchInfo.setBatchList(Arrays.asList(batchInfo.getBatchId()));
            Boolean deleteRsp = batchDomainService.deleteByIds(batchInfo);
            Assertions.assertEquals(true, deleteRsp);
        }
    }


    /**
     * 不存在的数据测试
     */
    @Test
    public void runBatchNotExists() {

        DcBatchInfoDO batchInfo = TestDcBatchInfoDomainService.getDataBean();
        ContextContainer container = new ContextContainer();
        container.put(BatchFLowEnum.INPUT_BATCH_ID.name(), batchInfo.getBatchId());
        FlowCommonData.addRunTask(container);

        boolean rsp = checkBatchRunnable.invokeFlow(container);
        Assertions.assertEquals(false, rsp);

    }


    /**
     * 儿取批次信息
     *
     * @return
     */
    private DcBatchInfoDO getBatchInfo(int status) {
        DcBatchInfoDO batchInfo = TestDcBatchInfoDomainService.getDataBean();
        batchInfo.setBatchRunStatus(status);

        boolean addBatch = batchDomainService.insert(batchInfo);
        Assertions.assertEquals(true, addBatch);

        return batchInfo;
    }


}

/*
 * Copyright (C), 2008-2021, Paraview All Rights Reserved.
 */
package com.liujun.schedule.application.taskflow.flow.batch;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.base.context.ContextContainer;
import com.ddd.common.infrastructure.base.context.FlowInf;
import com.liujun.schedule.application.taskflow.constant.BatchFLowEnum;
import com.liujun.schedule.domain.task.constant.BatchRunStatusEnum;
import com.liujun.schedule.domain.task.entity.DcBatchInfoDO;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDO;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDependDO;
import com.liujun.schedule.domain.task.entity.DcTaskInfoDO;
import com.liujun.schedule.domain.task.entity.DcTaskTypeDO;
import com.liujun.schedule.domain.task.service.DcBatchInfoDomainService;
import com.liujun.schedule.domain.task.service.DcBatchTaskDependDomainService;
import com.liujun.schedule.domain.task.service.DcBatchTaskDomainService;
import com.liujun.schedule.domain.task.service.DcTaskInfoDomainService;
import com.liujun.schedule.domain.task.service.DcTaskTypeDomainService;
import com.liujun.schedule.domain.task.service.TestDcBatchInfoDomainService;
import com.liujun.schedule.domain.task.service.TestDcBatchTaskDependDomainService;
import com.liujun.schedule.domain.task.service.TestDcTaskInfoDomainService;
import com.liujun.schedule.domain.task.service.TestDcTaskTypeDomainService;
import com.liujun.schedule.infrastructure.comm.uid.UidGenerator;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcBatchInfoRepositoryImpl;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcBatchTaskDependRepositoryImpl;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcBatchTaskRepositoryImpl;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcTaskInfoRepositoryImpl;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcTaskTypeRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 获取批次下所有的任务信息操作
 *
 * @author liujun
 * @since 2021/7/30
 */
@SpringBootTest(classes = {GetBatchAllInfoList.class, DruidDataSourceAutoConfigure.class, UidGenerator.class, MybatisAutoConfiguration.class,
        MyBatisScanConfiguration.class})
@Transactional(transactionManager = "testTransactionManager")
@Import(value = {DcBatchInfoDomainService.class, DcBatchInfoRepositoryImpl.class,
        DcTaskInfoDomainService.class, DcTaskInfoRepositoryImpl.class,
        DcBatchTaskDomainService.class, DcBatchTaskRepositoryImpl.class,
        DcTaskTypeDomainService.class, DcTaskTypeRepositoryImpl.class,
        DcBatchTaskDependDomainService.class, DcBatchTaskDependRepositoryImpl.class
})
@TestPropertySource("classpath:application.yml")
public class TestGetBatchAllInfoList {


    /**
     * 批次信息-的领域服务
     */
    @Autowired
    private DcBatchInfoDomainService batchDomainService;


    /**
     * 任务信息
     */
    @Autowired
    private DcTaskInfoDomainService taskInfoDomainService;

    /**
     * 批次下的任务信息
     */
    @Autowired
    private DcBatchTaskDomainService batchTaskDomainService;


    /**
     * 类型信息
     */
    @Autowired
    private DcTaskTypeDomainService typeDomainService;


    /**
     * 任务依赖关系
     */
    @Autowired
    private DcBatchTaskDependDomainService taskDependDomainService;


    /**
     * 检查
     */
    @Autowired
    @Qualifier("getBatchAllData")
    private FlowInf getBatchTaskList;


    /**
     * 进行批次任务的测试，运行成功
     */
    @Test
    public void runBatchSuccess() {
        DcBatchInfoDO batchInfo = this.getBatchInfo(BatchRunStatusEnum.INIT.getStatus());
        List<DcTaskInfoDO> taskList = this.getTaskInfo();
        List<DcBatchTaskDO> batchList = this.batchTask(batchInfo.getBatchId(), taskList);
        List<DcTaskTypeDO> typeList = this.typeInfo();
        List<DcBatchTaskDependDO> batchDependList = this.taskDependList(batchInfo.getBatchId());

        try {
            ContextContainer container = new ContextContainer();
            container.put(BatchFLowEnum.INPUT_BATCH_ID.name(), batchInfo.getBatchId());

            boolean rsp = getBatchTaskList.invokeFlow(container);
            Assertions.assertEquals(true, rsp);
        } finally {
            batchInfo.setBatchList(Arrays.asList(batchInfo.getBatchId()));
            Boolean deleteRsp = batchDomainService.deleteByIds(batchInfo);
            Assertions.assertEquals(true, deleteRsp);


            List<Long> taskIdList = new ArrayList<>();
            for (DcTaskInfoDO taskInfo : taskList) {
                taskIdList.add(taskInfo.getTaskId());
            }
            DcTaskInfoDO deleteTask = new DcTaskInfoDO();
            deleteTask.setTaskList(taskIdList);
            boolean deleteTaskRsp = taskInfoDomainService.deleteByIds(deleteTask);
            Assertions.assertEquals(deleteTaskRsp, true);


            DcBatchTaskDO batchTask = new DcBatchTaskDO();
            batchTask.setBatchId(batchInfo.getBatchId());
            Boolean deleteBatchRsp = batchTaskDomainService.deleteByBatchId(batchTask);
            Assertions.assertEquals(true, deleteBatchRsp);

            //类型删除
            for (DcTaskTypeDO taskType : typeList) {
                Boolean addRsp = typeDomainService.deleteByIds(taskType);
                Assertions.assertEquals(true, addRsp);
            }

            for (DcBatchTaskDependDO dependInfo : batchDependList) {
                boolean deleteDependRsp = taskDependDomainService.deleteByIds(dependInfo);
                Assertions.assertEquals(true, deleteDependRsp);
            }
        }
    }


    /**
     * 进行批次任务的测试，运行成功
     */
    @Test
    public void runBatchTaskNull() {
        DcBatchInfoDO batchInfo = this.getBatchInfo(BatchRunStatusEnum.INIT.getStatus());
        List<DcTaskInfoDO> taskList = this.getTaskInfo();

        try {
            ContextContainer container = new ContextContainer();
            container.put(BatchFLowEnum.INPUT_BATCH_ID.name(), batchInfo.getBatchId());

            boolean rsp = getBatchTaskList.invokeFlow(container);
            Assertions.assertEquals(false, rsp);
        } finally {
            batchInfo.setBatchList(Arrays.asList(batchInfo.getBatchId()));
            Boolean deleteRsp = batchDomainService.deleteByIds(batchInfo);
            Assertions.assertEquals(true, deleteRsp);


            List<Long> taskIdList = new ArrayList<>();
            for (DcTaskInfoDO taskInfo : taskList) {
                taskIdList.add(taskInfo.getTaskId());
            }
            DcTaskInfoDO deleteTask = new DcTaskInfoDO();
            deleteTask.setTaskList(taskIdList);
            boolean deleteTaskRsp = taskInfoDomainService.deleteByIds(deleteTask);
            Assertions.assertEquals(deleteTaskRsp, true);

        }
    }


    /**
     * 进行批次任务的测试，运行成功
     */
    @Test
    public void runTaskNull() {
        DcBatchInfoDO batchInfo = this.getBatchInfo(BatchRunStatusEnum.INIT.getStatus());
        List<DcTaskInfoDO> taskList = TestDcTaskInfoDomainService.getListDataBean();
        List<DcBatchTaskDO> batchList = this.batchTask(batchInfo.getBatchId(), taskList);

        try {
            ContextContainer container = new ContextContainer();
            container.put(BatchFLowEnum.INPUT_BATCH_ID.name(), batchInfo.getBatchId());

            boolean rsp = getBatchTaskList.invokeFlow(container);
            Assertions.assertEquals(false, rsp);
        } finally {
            batchInfo.setBatchList(Arrays.asList(batchInfo.getBatchId()));
            Boolean deleteRsp = batchDomainService.deleteByIds(batchInfo);
            Assertions.assertEquals(true, deleteRsp);


            DcBatchTaskDO batchTask = new DcBatchTaskDO();
            batchTask.setBatchId(batchInfo.getBatchId());
            Boolean deleteBatchRsp = batchTaskDomainService.deleteByBatchId(batchTask);
            Assertions.assertEquals(true, deleteBatchRsp);

        }
    }


    /**
     * 进行批次任务的测试，运行成功
     */
    @Test
    public void runTypeNull() {
        DcBatchInfoDO batchInfo = this.getBatchInfo(BatchRunStatusEnum.INIT.getStatus());
        List<DcTaskInfoDO> taskList = this.getTaskInfo();
        List<DcBatchTaskDO> batchList = this.batchTask(batchInfo.getBatchId(), taskList);
        List<DcTaskTypeDO> taskTypeList = TestDcTaskTypeDomainService.getListDataBean();

        try {
            ContextContainer container = new ContextContainer();
            container.put(BatchFLowEnum.INPUT_BATCH_ID.name(), batchInfo.getBatchId());

            boolean rsp = getBatchTaskList.invokeFlow(container);
            Assertions.assertEquals(false, rsp);
        } finally {
            batchInfo.setBatchList(Arrays.asList(batchInfo.getBatchId()));
            Boolean deleteRsp = batchDomainService.deleteByIds(batchInfo);
            Assertions.assertEquals(true, deleteRsp);


            List<Long> taskIdList = new ArrayList<>();
            for (DcTaskInfoDO taskInfo : taskList) {
                taskIdList.add(taskInfo.getTaskId());
            }
            DcTaskInfoDO deleteTask = new DcTaskInfoDO();
            deleteTask.setTaskList(taskIdList);
            boolean deleteTaskRsp = taskInfoDomainService.deleteByIds(deleteTask);
            Assertions.assertEquals(deleteTaskRsp, true);


            DcBatchTaskDO batchTask = new DcBatchTaskDO();
            batchTask.setBatchId(batchInfo.getBatchId());
            Boolean deleteBatchRsp = batchTaskDomainService.deleteByBatchId(batchTask);
            Assertions.assertEquals(true, deleteBatchRsp);

        }
    }


    /**
     * 获取批次信息
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


    private List<DcTaskInfoDO> getTaskInfo() {
        List<DcTaskInfoDO> dataList = TestDcTaskInfoDomainService.getListDataBean();
        boolean addRsp = taskInfoDomainService.insertList(dataList);
        Assertions.assertEquals(true, addRsp);

        return dataList;
    }


    private List<DcBatchTaskDO> getBatchTaskList(Long batchId, List<DcTaskInfoDO> taskList) {
        List<DcBatchTaskDO> result = new ArrayList<>(taskList.size());

        for (DcTaskInfoDO taskInfo : taskList) {
            DcBatchTaskDO batchTask = new DcBatchTaskDO();
            batchTask.setBatchId(batchId);
            batchTask.setTaskId(taskInfo.getTaskId());
            result.add(batchTask);
        }
        return result;
    }


    private List<DcBatchTaskDO> batchTask(Long batchId, List<DcTaskInfoDO> taskList) {
        List<DcBatchTaskDO> result = getBatchTaskList(batchId, taskList);
        boolean addRsp = batchTaskDomainService.insertList(result);
        Assertions.assertEquals(true, addRsp);

        return result;
    }


    private List<DcTaskTypeDO> typeInfo() {
        List<DcTaskTypeDO> taskList = TestDcTaskTypeDomainService.getListDataBean();

        Boolean typeRsp = typeDomainService.insertList(taskList);
        Assertions.assertEquals(true, typeRsp);
        return taskList;
    }

    /**
     * 任务依赖关系
     *
     * @return 任务信息
     */
    private List<DcBatchTaskDependDO> taskDependList(Long batchId) {
        List<DcBatchTaskDependDO> batchTaskList = TestDcBatchTaskDependDomainService.getListDataBean();

        for (DcBatchTaskDependDO taskDepend : batchTaskList) {
            taskDepend.setBatchId(batchId);
        }

        Boolean rsp = taskDependDomainService.insertList(batchTaskList);
        Assertions.assertEquals(rsp, true);

        return batchTaskList;

    }


}

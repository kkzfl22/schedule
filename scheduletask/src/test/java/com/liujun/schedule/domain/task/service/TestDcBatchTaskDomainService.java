package com.liujun.schedule.domain.task.service;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDO;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcBatchTaskRepositoryImpl;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 领域服务的单元测试
 *
 * @author liujun
 * @version 0.0.1
 */
@SpringBootTest(classes = {DruidDataSourceAutoConfigure.class, MybatisAutoConfiguration.class, MyBatisScanConfiguration.class})
@Transactional(transactionManager = "testTransactionManager")
@Import(value = {DcBatchTaskDomainService.class, DcBatchTaskRepositoryImpl.class})
@TestPropertySource("classpath:application.yml")
public class TestDcBatchTaskDomainService {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 批次任务信息-的领域服务
     */
    @Autowired
    private DcBatchTaskDomainService domainService;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcBatchTaskDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 批次任务信息-的领域实体信息
     */
    private DcBatchTaskDO oneData;


    /**
     * 数据操作类型，1，单个插入，2批量插入，其他不操作
     */
    private int insertType;


    /**
     * 单元测试的数据准备
     */
    @BeforeEach
    public void beforeSetData() {
        for (int i = 0; i < INSERT_BATCH_NUM; i++) {
            batchDataList.add(getDataBean());
        }
        oneData = batchDataList.get(0);
    }

    /**
     * 单元测试的获取数据对象
     */
    public DcBatchTaskDO getDataBean() {
        DcBatchTaskDO paramBean = new DcBatchTaskDO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcBatchTaskDO paramBean) {
        //批次的ID
        paramBean.setBatchId(RandomUtils.nextLong(0, 1 << 20));
        //调度任务信息表(DC_TASK_INFO)中的任务的ID
        paramBean.setTaskId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcBatchTaskDO paramBean) {
    }

    /**
     * 数据集对比方法
     *
     * @param srcList    源数据集
     * @param targetList 目标数据集
     */
    private void assertDataList(List<DcBatchTaskDO> srcList, List<DcBatchTaskDO> targetList) {
        Map<String, DcBatchTaskDO> dataMapTmp = this.parseMap(targetList);
        for (DcBatchTaskDO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcBatchTaskDO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcBatchTaskDO> parseMap(List<DcBatchTaskDO> dataList) {
        Map<String, DcBatchTaskDO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcBatchTaskDO dataItemTmp : dataList) {
            String key = this.getKey(dataItemTmp);
            dataMapTmp.put(key, dataItemTmp);
        }
        return dataMapTmp;
    }

    /**
     * 提取数据集的主键key
     *
     * @param paramBean 实体信息
     */
    private String getKey(DcBatchTaskDO paramBean) {
        StringBuilder key = new StringBuilder();
        key.append(paramBean.getBatchId());
        key.append(paramBean.getTaskId());
        return key.toString();
    }

    /**
     * 数据对比方法
     *
     * @param src    源数据信息
     * @param target 目标数据信息
     */
    private void assertData(DcBatchTaskDO src, DcBatchTaskDO target) {
        //批次的ID
        Assertions.assertEquals(src.getBatchId(), target.getBatchId());
        //调度任务信息表(DC_TASK_INFO)中的任务的ID
        Assertions.assertEquals(src.getTaskId(), target.getTaskId());
    }

    /**
     * 单个添加
     */
    @Test
    public void testInsert() {
        boolean operatorRsp = domainService.insert(oneData);
        Assertions.assertEquals(true, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;
    }

    /**
     * 批量添加
     */
    @Test
    public void testInsertList() {
        boolean operatorRsp = domainService.insertList(batchDataList);
        Assertions.assertEquals(true, operatorRsp);
        insertType = InsertType.INSERT_TYPE_BATCH;
    }


    /**
     * 分页查询
     */
    @Test
    public void testGetTaskListByBatchId() {
        long batchId = RandomUtils.nextLong(0, 1 << 20);
        for (DcBatchTaskDO taskInfo : batchDataList) {
            taskInfo.setBatchId(batchId);
        }

        boolean operatorRsp = domainService.insertList(batchDataList);
        Assertions.assertEquals(true, operatorRsp);
        insertType = InsertType.INSERT_TYPE_BATCH;
        List<DcBatchTaskDO> pageRsp = domainService.getTaskListByBatchId(batchId);
        this.assertDataList(batchDataList, pageRsp);
    }

    /**
     * 按id查询详细
     */
    @Test
    public void testDetail() {
        boolean operatorRsp = domainService.insert(oneData);
        Assertions.assertEquals(true, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;

        DcBatchTaskDO paramDataTmp = new DcBatchTaskDO();
        paramDataTmp.setBatchId(oneData.getBatchId());
        paramDataTmp.setTaskId(oneData.getTaskId());
        DcBatchTaskDO dataRsp = domainService.detail(paramDataTmp);
        this.assertData(oneData, dataRsp);
    }

    /**
     * 数据清理
     */
    @AfterEach
    public void afterCleanData() {
        if (insertType == InsertType.INSERT_TYPE_ONE) {
            boolean deleteRsp = domainService.deleteByBatchId(oneData);
            Assertions.assertEquals(true, deleteRsp);
        } else if (insertType == InsertType.INSERT_TYPE_BATCH) {
            for (int i = 0; i < INSERT_BATCH_NUM; i++) {
                DcBatchTaskDO dataItemTmp = batchDataList.get(i);
                domainService.deleteByBatchId(dataItemTmp);
            }
        }
    }

}
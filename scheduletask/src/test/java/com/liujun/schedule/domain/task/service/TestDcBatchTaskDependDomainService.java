package com.liujun.schedule.domain.task.service;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.schedule.domain.task.entity.DcBatchTaskDependDO;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcBatchTaskDependRepositoryImpl;
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
@Import(value = {DcBatchTaskDependDomainService.class, DcBatchTaskDependRepositoryImpl.class})
@TestPropertySource("classpath:application.yml")
public class TestDcBatchTaskDependDomainService {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 批次中任务依赖关系-的领域服务
     */
    @Autowired
    private DcBatchTaskDependDomainService domainService;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcBatchTaskDependDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 批次中任务依赖关系-的领域实体信息
     */
    private DcBatchTaskDependDO oneData;


    /**
     * 数据操作类型，1，单个插入，2批量插入，其他不操作
     */
    private int insertType;


    /**
     * 单元测试的数据准备
     */
    @BeforeEach
    public void beforeSetData() {
        batchDataList.addAll(getListDataBean());
        oneData = batchDataList.get(0);
    }

    public static List<DcBatchTaskDependDO> getListDataBean() {
        List<DcBatchTaskDependDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);
        for (int i = 0; i < INSERT_BATCH_NUM; i++) {
            batchDataList.add(getDataBean());
        }
        return batchDataList;
    }


    /**
     * 单元测试的获取数据对象
     */
    public static DcBatchTaskDependDO getDataBean() {
        DcBatchTaskDependDO paramBean = new DcBatchTaskDependDO();
        setPrimaryField(paramBean);

        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private static void setPrimaryField(DcBatchTaskDependDO paramBean) {
        //批次的ID
        paramBean.setBatchId(RandomUtils.nextLong(0, 1 << 20));
        //调度任务信息表(DC_TASK_INFO)中的任务的ID
        paramBean.setTaskId(RandomUtils.nextLong(0, 1 << 20));
        //依赖的任务的ID
        paramBean.setPrevTaskId(RandomUtils.nextLong(0, 1 << 20));
    }


    /**
     * 数据集对比方法
     *
     * @param srcList    源数据集
     * @param targetList 目标数据集
     */
    private void assertDataList(List<DcBatchTaskDependDO> srcList, List<DcBatchTaskDependDO> targetList) {
        Map<String, DcBatchTaskDependDO> dataMapTmp = this.parseMap(targetList);
        for (DcBatchTaskDependDO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcBatchTaskDependDO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcBatchTaskDependDO> parseMap(List<DcBatchTaskDependDO> dataList) {
        Map<String, DcBatchTaskDependDO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcBatchTaskDependDO dataItemTmp : dataList) {
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
    private String getKey(DcBatchTaskDependDO paramBean) {
        StringBuilder key = new StringBuilder();
        key.append(paramBean.getBatchId());
        key.append(paramBean.getTaskId());
        key.append(paramBean.getPrevTaskId());
        return key.toString();
    }

    /**
     * 数据对比方法
     *
     * @param src    源数据信息
     * @param target 目标数据信息
     */
    private void assertData(DcBatchTaskDependDO src, DcBatchTaskDependDO target) {
        //批次的ID
        Assertions.assertEquals(src.getBatchId(), target.getBatchId());
        //调度任务信息表(DC_TASK_INFO)中的任务的ID
        Assertions.assertEquals(src.getTaskId(), target.getTaskId());
        //依赖的任务的ID
        Assertions.assertEquals(src.getPrevTaskId(), target.getPrevTaskId());
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
    public void testQueryPage() {
        boolean operatorRsp = domainService.insertList(batchDataList);
        Assertions.assertEquals(true, operatorRsp);
        insertType = InsertType.INSERT_TYPE_BATCH;

        DcBatchTaskDependDO paramDataTmp = new DcBatchTaskDependDO();
        paramDataTmp.setBatchId(oneData.getBatchId());
        paramDataTmp.setTaskId(oneData.getTaskId());
        paramDataTmp.setPrevTaskId(oneData.getPrevTaskId());
        DomainPage<DcBatchTaskDependDO> pageData = DomainPage.<DcBatchTaskDependDO>builder().size(INSERT_BATCH_NUM).page(0).data(paramDataTmp).build();
        DomainPage<List<DcBatchTaskDependDO>> pageRsp = domainService.queryPage(pageData);
        this.assertDataList(batchDataList, pageRsp.getData());
    }

    /**
     * 按id查询详细
     */
    @Test
    public void testDetail() {
        boolean operatorRsp = domainService.insert(oneData);
        Assertions.assertEquals(true, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;

        DcBatchTaskDependDO paramDataTmp = new DcBatchTaskDependDO();
        paramDataTmp.setBatchId(oneData.getBatchId());
        paramDataTmp.setTaskId(oneData.getTaskId());
        paramDataTmp.setPrevTaskId(oneData.getPrevTaskId());
        DcBatchTaskDependDO dataRsp = domainService.detail(paramDataTmp);
        this.assertData(oneData, dataRsp);
    }

    /**
     * 数据清理
     */
    @AfterEach
    public void afterCleanData() {
        if (insertType == InsertType.INSERT_TYPE_ONE) {
            boolean deleteRsp = domainService.deleteByIds(oneData);
            Assertions.assertEquals(true, deleteRsp);
        } else if (insertType == InsertType.INSERT_TYPE_BATCH) {
            for (int i = 0; i < INSERT_BATCH_NUM; i++) {
                DcBatchTaskDependDO dataItemTmp = batchDataList.get(i);
                boolean deleteRsp = domainService.deleteByIds(dataItemTmp);
                Assertions.assertEquals(true, deleteRsp);
            }
        }
    }

}
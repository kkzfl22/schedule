package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchTaskDependPO;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作单元测试
 *
 * @author liujun
 * @version 0.0.1
 */
@SpringBootTest(classes = {DruidDataSourceAutoConfigure.class, MybatisAutoConfiguration.class, MyBatisScanConfiguration.class})
@Transactional(transactionManager = "testTransactionManager")
@TestPropertySource("classpath:application.yml")
public class TestDcBatchTaskDependMapper {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 批次中任务依赖关系表(dc_batch_task_depend)的数据库操作
     */
    @Autowired
    private DcBatchTaskDependMapper mapper;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcBatchTaskDependPO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 批次中任务依赖关系表(dc_batch_task_depend)的数据库存储实体信息
     */
    private DcBatchTaskDependPO oneData;


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
    public DcBatchTaskDependPO getDataBean() {
        DcBatchTaskDependPO paramBean = new DcBatchTaskDependPO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcBatchTaskDependPO paramBean) {
        //批次的ID
        paramBean.setBatchId(RandomUtils.nextLong(0, 1 << 20));
        //调度任务信息表(DC_TASK_INFO)中的任务的ID
        paramBean.setTaskId(RandomUtils.nextLong(0, 1 << 20));
        //依赖的任务的ID
        paramBean.setPrevTaskId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcBatchTaskDependPO paramBean) {
    }

    /**
     * 数据集对比方法
     *
     * @param srcList    源数据集
     * @param targetList 目标数据集
     */
    private void assertDataList(List<DcBatchTaskDependPO> srcList, List<DcBatchTaskDependPO> targetList) {
        Map<String, DcBatchTaskDependPO> dataMapTmp = this.parseMap(targetList);
        for (DcBatchTaskDependPO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcBatchTaskDependPO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcBatchTaskDependPO> parseMap(List<DcBatchTaskDependPO> dataList) {
        Map<String, DcBatchTaskDependPO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcBatchTaskDependPO dataItemTmp : dataList) {
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
    private String getKey(DcBatchTaskDependPO paramBean) {
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
    private void assertData(DcBatchTaskDependPO src, DcBatchTaskDependPO target) {
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
        int operatorRsp = mapper.insert(oneData);
        Assertions.assertEquals(1, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;
    }

    /**
     * 批量添加
     */
    @Test
    public void testInsertList() {
        int operatorRsp = mapper.insertList(batchDataList);
        Assertions.assertEquals(INSERT_BATCH_NUM, operatorRsp);
        insertType = InsertType.INSERT_TYPE_BATCH;
    }


    /**
     * 按id查询详细
     */
    @Test
    public void testDetail() {
        int operatorRsp = mapper.insert(oneData);
        Assertions.assertEquals(1, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;

        DcBatchTaskDependPO paramDataTmp = new DcBatchTaskDependPO();
        paramDataTmp.setBatchId(oneData.getBatchId());
        paramDataTmp.setTaskId(oneData.getTaskId());
        paramDataTmp.setPrevTaskId(oneData.getPrevTaskId());
        DcBatchTaskDependPO dataRsp = mapper.detail(paramDataTmp);
        this.assertData(oneData, dataRsp);
    }


    /**
     * 按id查询详细
     */
    @Test
    public void testBatchList() {

        long batchId = RandomUtils.nextLong(0, 1 << 20);
        for (DcBatchTaskDependPO dependItem : batchDataList) {
            dependItem.setBatchId(batchId);
        }
        int operatorRsp = mapper.insertList(batchDataList);
        Assertions.assertEquals(INSERT_BATCH_NUM, operatorRsp);
        insertType = InsertType.INSERT_TYPE_BATCH;

        DcBatchTaskDependPO paramDataTmp = new DcBatchTaskDependPO();
        paramDataTmp.setBatchId(oneData.getBatchId());

        List<DcBatchTaskDependPO> dataRsp = mapper.getDependByBatchId(paramDataTmp);
        this.assertDataList(batchDataList, dataRsp);
    }

    /**
     * 数据清理
     */
    @AfterEach
    public void afterCleanData() {
        if (insertType == InsertType.INSERT_TYPE_ONE) {
            int deleteRsp = mapper.deleteByIds(oneData);
            Assertions.assertEquals(1, deleteRsp);
        } else if (insertType == InsertType.INSERT_TYPE_BATCH) {
            for (int i = 0; i < INSERT_BATCH_NUM; i++) {
                DcBatchTaskDependPO dataItemTmp = batchDataList.get(i);
                int deleteRsp = mapper.deleteByIds(dataItemTmp);
                Assertions.assertEquals(1, deleteRsp);
            }
        }
    }

}
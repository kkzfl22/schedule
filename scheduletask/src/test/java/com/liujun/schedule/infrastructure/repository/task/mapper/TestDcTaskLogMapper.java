package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskLogPO;
import org.apache.commons.lang3.RandomStringUtils;
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
public class TestDcTaskLogMapper {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 任务的日志信息表(dc_task_log)的数据库操作
     */
    @Autowired
    private DcTaskLogMapper mapper;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcTaskLogPO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 任务的日志信息表(dc_task_log)的数据库存储实体信息
     */
    private DcTaskLogPO oneData;


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
    public DcTaskLogPO getDataBean() {
        DcTaskLogPO paramBean = new DcTaskLogPO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcTaskLogPO paramBean) {
        //日志ID
        paramBean.setLogId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcTaskLogPO paramBean) {
        //批次号
        paramBean.setBatchId(RandomUtils.nextLong(0, 1 << 20));
        //任务的ID
        paramBean.setTaskId(RandomUtils.nextLong(0, 1 << 20));
        //任务名称
        paramBean.setTaskName(RandomStringUtils.randomAlphabetic(20));
        //任务的状态:, 1:成功, 2:任务执行中, -1：失败
        paramBean.setTaskStatus(RandomUtils.nextInt(0, 1 << 3));
        //任务的运行次数次数, 从1开始，任务最少执行一次
        paramBean.setTaskRunNum(RandomUtils.nextInt(0, 1 << 5));
        //日志信息
        paramBean.setTaskMsg(RandomStringUtils.randomAlphabetic(20));
        //任务的开始时间
        paramBean.setTaskStartTime(RandomUtils.nextLong(0, 1 << 20));
        //任务的结束时间
        paramBean.setTaskFinishTime(RandomUtils.nextLong(0, 1 << 20));
        //当前任务在任务依赖链中的顺序
        paramBean.setTaskOrder(RandomUtils.nextInt(0, 1 << 8));
        //每次执行任务时的一个标识
        paramBean.setTaskRuntimeFlag(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 数据集对比方法
     *
     * @param srcList    源数据集
     * @param targetList 目标数据集
     */
    private void assertDataList(List<DcTaskLogPO> srcList, List<DcTaskLogPO> targetList) {
        Map<String, DcTaskLogPO> dataMapTmp = this.parseMap(targetList);
        for (DcTaskLogPO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcTaskLogPO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcTaskLogPO> parseMap(List<DcTaskLogPO> dataList) {
        Map<String, DcTaskLogPO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcTaskLogPO dataItemTmp : dataList) {
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
    private String getKey(DcTaskLogPO paramBean) {
        StringBuilder key = new StringBuilder();
        key.append(paramBean.getLogId());
        return key.toString();
    }

    /**
     * 数据对比方法
     *
     * @param src    源数据信息
     * @param target 目标数据信息
     */
    private void assertData(DcTaskLogPO src, DcTaskLogPO target) {
        //日志ID
        Assertions.assertEquals(src.getLogId(), target.getLogId());
        //批次号
        Assertions.assertEquals(src.getBatchId(), target.getBatchId());
        //任务的ID
        Assertions.assertEquals(src.getTaskId(), target.getTaskId());
        //任务名称
        Assertions.assertEquals(src.getTaskName(), target.getTaskName());
        //任务的状态:, 1:成功, 2:任务执行中, -1：失败
        Assertions.assertEquals(src.getTaskStatus(), target.getTaskStatus());
        //任务的运行次数次数, 从1开始，任务最少执行一次
        Assertions.assertEquals(src.getTaskRunNum(), target.getTaskRunNum());
        //日志信息
        Assertions.assertEquals(src.getTaskMsg(), target.getTaskMsg());
        //任务的开始时间
        Assertions.assertEquals(src.getTaskStartTime(), target.getTaskStartTime());
        //任务的结束时间
        Assertions.assertEquals(src.getTaskFinishTime(), target.getTaskFinishTime());
        //当前任务在任务依赖链中的顺序
        Assertions.assertEquals(src.getTaskOrder(), target.getTaskOrder());
        //每次执行任务时的一个标识
        Assertions.assertEquals(src.getTaskRuntimeFlag(), target.getTaskRuntimeFlag());
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
     * 修改方法
     */
    @Test
    public void testUpdate() {
        int operatorRsp = mapper.insert(oneData);
        Assertions.assertEquals(1, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;

        this.setDataField(oneData);
        int updateRsp = mapper.updateStatus(oneData);
        Assertions.assertEquals(1, updateRsp);
    }

    /**
     * 按id查询详细
     */
    @Test
    public void testDetail() {
        int operatorRsp = mapper.insert(oneData);
        Assertions.assertEquals(1, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;

        DcTaskLogPO paramDataTmp = new DcTaskLogPO();
        paramDataTmp.setLogId(oneData.getLogId());
        DcTaskLogPO dataRsp = mapper.detail(paramDataTmp);
        this.assertData(oneData, dataRsp);
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
                DcTaskLogPO dataItemTmp = batchDataList.get(i);
                int deleteRsp = mapper.deleteByIds(dataItemTmp);
                Assertions.assertEquals(1, deleteRsp);
            }
        }
    }

}
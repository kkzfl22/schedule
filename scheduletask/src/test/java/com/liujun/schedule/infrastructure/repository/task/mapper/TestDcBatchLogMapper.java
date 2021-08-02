package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.liujun.schedule.application.taskflow.constant.TaskRunStatusEnum;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchLogPO;
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
public class TestDcBatchLogMapper {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 批次的日志批次状态表(dc_batch_log)的数据库操作
     */
    @Autowired
    private DcBatchLogMapper mapper;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcBatchLogPO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 批次的日志批次状态表(dc_batch_log)的数据库存储实体信息
     */
    private DcBatchLogPO oneData;


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
    public DcBatchLogPO getDataBean() {
        DcBatchLogPO paramBean = new DcBatchLogPO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcBatchLogPO paramBean) {
        //日志ID
        paramBean.setLogId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcBatchLogPO paramBean) {
        //批次号
        paramBean.setBatchId(RandomUtils.nextLong(0, 1 << 20));
        //任务的名称
        paramBean.setBatchName(RandomStringUtils.randomAlphabetic(20));
        //任务的状态:, 1:成功, 2:任务执行中, 0:初始化状态 -1：失败
        paramBean.setBatchRunStatus(RandomUtils.nextInt(0, 1 << 2));
        //任务日志，成功为空，失败时记录下失败信息
        paramBean.setBatchMsg(RandomStringUtils.randomAlphabetic(20));
        //批次的开始时间
        paramBean.setBatchStartTime(RandomUtils.nextLong(0, 1 << 20));
        //批次的结束时间
        paramBean.setBatchFinishTime(RandomUtils.nextLong(0, 1 << 20));
        //每个批次执行时的标识
        paramBean.setTaskRuntimeFlag(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 数据集对比方法
     *
     * @param srcList    源数据集
     * @param targetList 目标数据集
     */
    private void assertDataList(List<DcBatchLogPO> srcList, List<DcBatchLogPO> targetList) {
        Map<String, DcBatchLogPO> dataMapTmp = this.parseMap(targetList);
        for (DcBatchLogPO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcBatchLogPO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcBatchLogPO> parseMap(List<DcBatchLogPO> dataList) {
        Map<String, DcBatchLogPO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcBatchLogPO dataItemTmp : dataList) {
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
    private String getKey(DcBatchLogPO paramBean) {
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
    private void assertData(DcBatchLogPO src, DcBatchLogPO target) {
        //日志ID
        Assertions.assertEquals(src.getLogId(), target.getLogId());
        //批次号
        Assertions.assertEquals(src.getBatchId(), target.getBatchId());
        //任务的名称
        Assertions.assertEquals(src.getBatchName(), target.getBatchName());
        //任务的状态:, 1:成功, 2:任务执行中, 0:初始化状态 -1：失败
        Assertions.assertEquals(src.getBatchRunStatus(), target.getBatchRunStatus());
        //任务日志，成功为空，失败时记录下失败信息
        Assertions.assertEquals(src.getBatchMsg(), target.getBatchMsg());
        //批次的开始时间
        Assertions.assertEquals(src.getBatchStartTime(), target.getBatchStartTime());
        //批次的结束时间
        Assertions.assertEquals(src.getBatchFinishTime(), target.getBatchFinishTime());
        //每个批次执行时的标识
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


        DcBatchLogPO updLog = new DcBatchLogPO();
        updLog.setBatchId(oneData.getBatchId());
        updLog.setTaskRuntimeFlag(oneData.getTaskRuntimeFlag());
        updLog.setBatchRunStatus(TaskRunStatusEnum.SUCCESS.getStatus());
        updLog.setBeforeBatchRunStatus(oneData.getBatchRunStatus());

        int updateRsp = mapper.updateStatus(updLog);
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

        DcBatchLogPO paramDataTmp = new DcBatchLogPO();
        paramDataTmp.setLogId(oneData.getLogId());
        DcBatchLogPO dataRsp = mapper.detail(paramDataTmp);
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
                DcBatchLogPO dataItemTmp = batchDataList.get(i);
                int deleteRsp = mapper.deleteByIds(dataItemTmp);
                Assertions.assertEquals(1, deleteRsp);
            }
        }
    }

}
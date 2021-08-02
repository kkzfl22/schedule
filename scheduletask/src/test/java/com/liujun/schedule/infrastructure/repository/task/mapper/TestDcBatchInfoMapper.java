package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.po.DcBatchInfoPO;
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
import java.util.Arrays;
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
public class TestDcBatchInfoMapper {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 批次信息表(dc_batch_info)的数据库操作
     */
    @Autowired
    private DcBatchInfoMapper mapper;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcBatchInfoPO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 批次信息表(dc_batch_info)的数据库存储实体信息
     */
    private DcBatchInfoPO oneData;


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
    public DcBatchInfoPO getDataBean() {
        DcBatchInfoPO paramBean = new DcBatchInfoPO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcBatchInfoPO paramBean) {
        //批次的ID
        paramBean.setBatchId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcBatchInfoPO paramBean) {
        //所属任务目录ID
        paramBean.setTaskDirId(RandomUtils.nextLong(0, 1 << 20));
        //批次的任务名称
        paramBean.setBatchName(RandomStringUtils.randomAlphabetic(20));
        //批次任务的描述信息
        paramBean.setBatchMsg(RandomStringUtils.randomAlphabetic(20));
        //批次任务的状态，1，启用，0，停用
        paramBean.setBatchStatus(RandomUtils.nextInt(0, 1 << 1));
        //批次的运行状态,0,初始化，1：运行中，2，运行完成
        paramBean.setBatchRunStatus(RandomUtils.nextInt(0, 1 << 2));
        //每个批次执行时的标识
        paramBean.setTaskRuntimeFlag(RandomUtils.nextLong(0, 1 << 20));
        //批次中任务最大并行度，防止一个批次占用过多的线程
        paramBean.setBatchConcurrent(RandomUtils.nextInt(0, 1 << 3));
        //创建者
        paramBean.setCreator(RandomStringUtils.randomAlphabetic(20));
        //更新者
        paramBean.setUpdater(RandomStringUtils.randomAlphabetic(20));
        //创建时间
        paramBean.setCreateTime(LocalDateTimeUtils.getDatabaseLocalDateTime());
        //更新时间
        paramBean.setUpdateTime(LocalDateTimeUtils.getDatabaseLocalDateTime());
    }

    /**
     * 数据集对比方法
     *
     * @param srcList    源数据集
     * @param targetList 目标数据集
     */
    private void assertDataList(List<DcBatchInfoPO> srcList, List<DcBatchInfoPO> targetList) {
        Map<String, DcBatchInfoPO> dataMapTmp = this.parseMap(targetList);
        for (DcBatchInfoPO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcBatchInfoPO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcBatchInfoPO> parseMap(List<DcBatchInfoPO> dataList) {
        Map<String, DcBatchInfoPO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcBatchInfoPO dataItemTmp : dataList) {
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
    private String getKey(DcBatchInfoPO paramBean) {
        StringBuilder key = new StringBuilder();
        key.append(paramBean.getBatchId());
        return key.toString();
    }

    /**
     * 数据对比方法
     *
     * @param src    源数据信息
     * @param target 目标数据信息
     */
    private void assertData(DcBatchInfoPO src, DcBatchInfoPO target) {
        //批次的ID
        Assertions.assertEquals(src.getBatchId(), target.getBatchId());
        //所属任务目录ID
        Assertions.assertEquals(src.getTaskDirId(), target.getTaskDirId());
        //批次的任务名称
        Assertions.assertEquals(src.getBatchName(), target.getBatchName());
        //批次任务的描述信息
        Assertions.assertEquals(src.getBatchMsg(), target.getBatchMsg());
        //批次任务的状态，1，启用，0，停用
        Assertions.assertEquals(src.getBatchStatus(), target.getBatchStatus());
        //批次的运行状态,0,初始化，1：运行中，2，运行完成
        Assertions.assertEquals(src.getBatchRunStatus(), target.getBatchRunStatus());
        //每个批次执行时的标识
        Assertions.assertEquals(src.getTaskRuntimeFlag(), target.getTaskRuntimeFlag());
        //批次中任务最大并行度，防止一个批次占用过多的线程
        Assertions.assertEquals(src.getBatchConcurrent(), target.getBatchConcurrent());
        //创建者
        Assertions.assertEquals(src.getCreator(), target.getCreator());
        //更新者
        Assertions.assertEquals(src.getUpdater(), target.getUpdater());
        //创建时间
        Assertions.assertEquals(src.getCreateTime(), target.getCreateTime());
        //更新时间
        Assertions.assertEquals(src.getUpdateTime(), target.getUpdateTime());
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
    public void testUpdateRunStatus() {
        int operatorRsp = mapper.insert(oneData);
        Assertions.assertEquals(1, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;
        oneData.setBatchRunStatusBefore(oneData.getBatchRunStatus());
        this.setDataField(oneData);

        int updateRsp = mapper.updateRunStatus(oneData);
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

        DcBatchInfoPO paramDataTmp = new DcBatchInfoPO();
        paramDataTmp.setBatchId(oneData.getBatchId());
        DcBatchInfoPO dataRsp = mapper.detail(paramDataTmp);
        this.assertData(oneData, dataRsp);
    }

    /**
     * 数据清理
     */
    @AfterEach
    public void afterCleanData() {
        if (insertType == InsertType.INSERT_TYPE_ONE) {
            oneData.setBatchList(Arrays.asList(oneData.getBatchId()));
            int deleteRsp = mapper.deleteByIds(oneData);
            Assertions.assertEquals(1, deleteRsp);
        } else if (insertType == InsertType.INSERT_TYPE_BATCH) {
            List<Long> idList = new ArrayList<>(INSERT_BATCH_NUM);
            for (int i = 0; i < INSERT_BATCH_NUM; i++) {
                idList.add(batchDataList.get(i).getBatchId());
            }
            oneData.setBatchList(idList);
            int deleteRsp = mapper.deleteByIds(oneData);
            Assertions.assertEquals(INSERT_BATCH_NUM, deleteRsp);

        }
    }

}
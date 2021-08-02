package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.po.DcScheduleCronPO;
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
public class TestDcScheduleCronMapper {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 调度的CRON表达式信息表(dc_schedule_cron)的数据库操作
     */
    @Autowired
    private DcScheduleCronMapper mapper;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcScheduleCronPO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 调度的CRON表达式信息表(dc_schedule_cron)的数据库存储实体信息
     */
    private DcScheduleCronPO oneData;


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
    public DcScheduleCronPO getDataBean() {
        DcScheduleCronPO paramBean = new DcScheduleCronPO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcScheduleCronPO paramBean) {
        //使用算法生成
        paramBean.setTaskId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcScheduleCronPO paramBean) {
        //当前任务的类型, 1:按任务调度,即, DC_TASK中的任务ID, 2:按批次调用，即DC_BATCH_INFO中的批次的ID
        paramBean.setTaskType(RandomUtils.nextInt(0, 1 << 2));
        //类型(月:MONTH,周:WEEK,日:DAY)
        paramBean.setScheduleType(RandomStringUtils.randomAlphabetic(20));
        //值(月:1,2,3 周:MON,SUN)
        paramBean.setScheduleValue(RandomStringUtils.randomAlphabetic(20));
        //CRON的表达式
        paramBean.setScheduleCron(RandomStringUtils.randomAlphabetic(20));
        //UI显示和配置的时间
        paramBean.setUiTime(RandomStringUtils.randomAlphabetic(20));
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
    private void assertDataList(List<DcScheduleCronPO> srcList, List<DcScheduleCronPO> targetList) {
        Map<String, DcScheduleCronPO> dataMapTmp = this.parseMap(targetList);
        for (DcScheduleCronPO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcScheduleCronPO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcScheduleCronPO> parseMap(List<DcScheduleCronPO> dataList) {
        Map<String, DcScheduleCronPO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcScheduleCronPO dataItemTmp : dataList) {
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
    private String getKey(DcScheduleCronPO paramBean) {
        StringBuilder key = new StringBuilder();
        key.append(paramBean.getTaskId());
        return key.toString();
    }

    /**
     * 数据对比方法
     *
     * @param src    源数据信息
     * @param target 目标数据信息
     */
    private void assertData(DcScheduleCronPO src, DcScheduleCronPO target) {
        //使用算法生成
        Assertions.assertEquals(src.getTaskId(), target.getTaskId());
        //当前任务的类型, 1:按任务调度,即, DC_TASK中的任务ID, 2:按批次调用，即DC_BATCH_INFO中的批次的ID
        Assertions.assertEquals(src.getTaskType(), target.getTaskType());
        //类型(月:MONTH,周:WEEK,日:DAY)
        Assertions.assertEquals(src.getScheduleType(), target.getScheduleType());
        //值(月:1,2,3 周:MON,SUN)
        Assertions.assertEquals(src.getScheduleValue(), target.getScheduleValue());
        //CRON的表达式
        Assertions.assertEquals(src.getScheduleCron(), target.getScheduleCron());
        //UI显示和配置的时间
        Assertions.assertEquals(src.getUiTime(), target.getUiTime());
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
    public void testUpdate() {
        int operatorRsp = mapper.insert(oneData);
        Assertions.assertEquals(1, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;

        this.setDataField(oneData);
        int updateRsp = mapper.update(oneData);
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

        DcScheduleCronPO paramDataTmp = new DcScheduleCronPO();
        paramDataTmp.setTaskId(oneData.getTaskId());
        DcScheduleCronPO dataRsp = mapper.detail(paramDataTmp);
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
                DcScheduleCronPO dataItemTmp = batchDataList.get(i);
                int deleteRsp = mapper.deleteByIds(dataItemTmp);
                Assertions.assertEquals(1, deleteRsp);
            }
        }
    }

}
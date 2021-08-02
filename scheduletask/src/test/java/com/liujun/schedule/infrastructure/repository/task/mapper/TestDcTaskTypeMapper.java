package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskTypePO;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
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
public class TestDcTaskTypeMapper {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 调度任务信息表(dc_task_type)的数据库操作
     */
    @Autowired
    private DcTaskTypeMapper mapper;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcTaskTypePO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 调度任务信息表(dc_task_type)的数据库存储实体信息
     */
    private DcTaskTypePO oneData;


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
    public DcTaskTypePO getDataBean() {
        DcTaskTypePO paramBean = new DcTaskTypePO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcTaskTypePO paramBean) {
        //任务的类型,
        paramBean.setType(RandomStringUtils.randomAlphabetic(20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcTaskTypePO paramBean) {
        //类型的信息
        paramBean.setTypeName(RandomStringUtils.randomAlphabetic(20));
        //类型的描述
        paramBean.setTypeMsg(RandomStringUtils.randomAlphabetic(10));
        //任务的配制信息,以JSON格式
        paramBean.setTypeCfg(RandomStringUtils.randomAlphabetic(20));
    }

    /**
     * 数据集对比方法
     *
     * @param srcList    源数据集
     * @param targetList 目标数据集
     */
    private void assertDataList(List<DcTaskTypePO> srcList, List<DcTaskTypePO> targetList) {
        Map<String, DcTaskTypePO> dataMapTmp = this.parseMap(targetList);
        for (DcTaskTypePO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcTaskTypePO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcTaskTypePO> parseMap(List<DcTaskTypePO> dataList) {
        Map<String, DcTaskTypePO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcTaskTypePO dataItemTmp : dataList) {
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
    private String getKey(DcTaskTypePO paramBean) {
        StringBuilder key = new StringBuilder();
        key.append(paramBean.getType());
        return key.toString();
    }

    /**
     * 数据对比方法
     *
     * @param src    源数据信息
     * @param target 目标数据信息
     */
    private void assertData(DcTaskTypePO src, DcTaskTypePO target) {
        //任务的类型,
        Assertions.assertEquals(src.getType(), target.getType());
        //类型的信息
        Assertions.assertEquals(src.getTypeName(), target.getTypeName());
        //类型的描述
        Assertions.assertEquals(src.getTypeMsg(), target.getTypeMsg());
        //任务的配制信息,以JSON格式
        Assertions.assertEquals(src.getTypeCfg(), target.getTypeCfg());
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

        DcTaskTypePO paramDataTmp = new DcTaskTypePO();
        paramDataTmp.setType(oneData.getType());
        DcTaskTypePO dataRsp = mapper.detail(paramDataTmp);
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
                DcTaskTypePO dataItemTmp = batchDataList.get(i);
                int deleteRsp = mapper.deleteByIds(dataItemTmp);
                Assertions.assertEquals(1, deleteRsp);
            }
        }
    }

}
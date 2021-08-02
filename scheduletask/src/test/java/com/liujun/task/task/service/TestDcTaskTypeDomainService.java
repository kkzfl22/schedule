package com.liujun.task.task.service;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.task.task.entity.DcTaskTypeDO;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcTaskTypeRepositoryImpl;
import org.apache.commons.lang3.RandomStringUtils;
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
@Import(value = {DcTaskTypeDomainService.class, DcTaskTypeRepositoryImpl.class})
@TestPropertySource("classpath:application.yml")
public class TestDcTaskTypeDomainService {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 调度任务信息-的领域服务
     */
    @Autowired
    private DcTaskTypeDomainService domainService;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcTaskTypeDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 调度任务信息-的领域实体信息
     */
    private DcTaskTypeDO oneData;


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

    /**
     * 获取批量的数据
     *
     * @return
     */
    public static List<DcTaskTypeDO> getListDataBean() {
        List<DcTaskTypeDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);
        for (int i = 0; i < INSERT_BATCH_NUM; i++) {
            batchDataList.add(getDataBean());
        }

        return batchDataList;
    }

    /**
     * 单元测试的获取数据对象
     */
    public static DcTaskTypeDO getDataBean() {
        DcTaskTypeDO paramBean = new DcTaskTypeDO();
        setPrimaryField(paramBean);
        setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private static void setPrimaryField(DcTaskTypeDO paramBean) {
        //任务的类型,
        paramBean.setType(RandomStringUtils.randomAlphabetic(20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private static void setDataField(DcTaskTypeDO paramBean) {
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
    private void assertDataList(List<DcTaskTypeDO> srcList, List<DcTaskTypeDO> targetList) {
        Map<String, DcTaskTypeDO> dataMapTmp = this.parseMap(targetList);
        for (DcTaskTypeDO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcTaskTypeDO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcTaskTypeDO> parseMap(List<DcTaskTypeDO> dataList) {
        Map<String, DcTaskTypeDO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcTaskTypeDO dataItemTmp : dataList) {
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
    private String getKey(DcTaskTypeDO paramBean) {
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
    private void assertData(DcTaskTypeDO src, DcTaskTypeDO target) {
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
     * 修改方法
     */
    @Test
    public void testUpdate() {
        boolean operatorRsp = domainService.insert(oneData);
        Assertions.assertEquals(true, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;

        this.setDataField(oneData);
        boolean updateRsp = domainService.update(oneData);
        Assertions.assertEquals(true, updateRsp);
    }

    /**
     * 分页查询
     */
    @Test
    public void testQueryPage() {
        boolean operatorRsp = domainService.insertList(batchDataList);
        Assertions.assertEquals(true, operatorRsp);
        insertType = InsertType.INSERT_TYPE_BATCH;

        DcTaskTypeDO paramDataTmp = new DcTaskTypeDO();
        paramDataTmp.setType(oneData.getType());
        DomainPage<DcTaskTypeDO> pageData = DomainPage.<DcTaskTypeDO>builder().size(INSERT_BATCH_NUM).page(0).data(paramDataTmp).build();
        DomainPage<List<DcTaskTypeDO>> pageRsp = domainService.queryPage(pageData);
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

        DcTaskTypeDO paramDataTmp = new DcTaskTypeDO();
        paramDataTmp.setType(oneData.getType());
        DcTaskTypeDO dataRsp = domainService.detail(paramDataTmp);
        this.assertData(oneData, dataRsp);
    }


    /**
     * 按id查询详细
     */
    @Test
    public void testQueryAll() {
        boolean operatorRsp = domainService.insert(oneData);
        Assertions.assertEquals(true, operatorRsp);
        insertType = InsertType.INSERT_TYPE_ONE;

        Map<String, DcTaskTypeDO> dataRsp = domainService.queryAllToMap();
        Assertions.assertNotEquals(0, dataRsp.size());
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
                DcTaskTypeDO dataItemTmp = batchDataList.get(i);
                boolean deleteRsp = domainService.deleteByIds(dataItemTmp);
                Assertions.assertEquals(true, deleteRsp);
            }
        }
    }

}
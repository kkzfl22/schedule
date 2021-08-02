package com.liujun.task.task.service;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.entity.DomainPage;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.task.task.entity.DcTaskDirDO;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcTaskDirRepositoryImpl;
import org.apache.commons.lang3.RandomStringUtils;
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
@Import(value = {DcTaskDirDomainService.class, DcTaskDirRepositoryImpl.class})
@TestPropertySource("classpath:application.yml")
public class TestDcTaskDirDomainService {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 任务目录信息-的领域服务
     */
    @Autowired
    private DcTaskDirDomainService domainService;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcTaskDirDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 任务目录信息-的领域实体信息
     */
    private DcTaskDirDO oneData;


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
    public DcTaskDirDO getDataBean() {
        DcTaskDirDO paramBean = new DcTaskDirDO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcTaskDirDO paramBean) {
        //任务目录的ID
        paramBean.setTaskDirId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcTaskDirDO paramBean) {
        //父任务目录的ID
        paramBean.setParentDirId(RandomUtils.nextLong(0, 1 << 20));
        //任务目录名称
        paramBean.setTaskDirName(RandomStringUtils.randomAlphabetic(20));
        //任务目录描述
        paramBean.setTaskDirDescription(RandomStringUtils.randomAlphabetic(20));
        //排序号
        paramBean.setTaskOrder(RandomUtils.nextInt(0, 1 << 6));
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
    private void assertDataList(List<DcTaskDirDO> srcList, List<DcTaskDirDO> targetList) {
        Map<String, DcTaskDirDO> dataMapTmp = this.parseMap(targetList);
        for (DcTaskDirDO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcTaskDirDO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcTaskDirDO> parseMap(List<DcTaskDirDO> dataList) {
        Map<String, DcTaskDirDO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcTaskDirDO dataItemTmp : dataList) {
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
    private String getKey(DcTaskDirDO paramBean) {
        StringBuilder key = new StringBuilder();
        key.append(paramBean.getTaskDirId());
        return key.toString();
    }

    /**
     * 数据对比方法
     *
     * @param src    源数据信息
     * @param target 目标数据信息
     */
    private void assertData(DcTaskDirDO src, DcTaskDirDO target) {
        //任务目录的ID
        Assertions.assertEquals(src.getTaskDirId(), target.getTaskDirId());
        //父任务目录的ID
        Assertions.assertEquals(src.getParentDirId(), target.getParentDirId());
        //任务目录名称
        Assertions.assertEquals(src.getTaskDirName(), target.getTaskDirName());
        //任务目录描述
        Assertions.assertEquals(src.getTaskDirDescription(), target.getTaskDirDescription());
        //排序号
        Assertions.assertEquals(src.getTaskOrder(), target.getTaskOrder());
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

        DcTaskDirDO paramDataTmp = new DcTaskDirDO();
        paramDataTmp.setTaskDirId(oneData.getTaskDirId());
        DomainPage<DcTaskDirDO> pageData = DomainPage.<DcTaskDirDO>builder().size(INSERT_BATCH_NUM).page(0).data(paramDataTmp).build();
        DomainPage<List<DcTaskDirDO>> pageRsp = domainService.queryPage(pageData);
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

        DcTaskDirDO paramDataTmp = new DcTaskDirDO();
        paramDataTmp.setTaskDirId(oneData.getTaskDirId());
        DcTaskDirDO dataRsp = domainService.detail(paramDataTmp);
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
                DcTaskDirDO dataItemTmp = batchDataList.get(i);
                boolean deleteRsp = domainService.deleteByIds(dataItemTmp);
                Assertions.assertEquals(true, deleteRsp);
            }
        }
    }

}
package com.liujun.schedule.domain.task.service;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.entity.DomainPage;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.domain.task.entity.DcBatchInfoDO;
import com.liujun.schedule.infrastructure.comm.uid.UidGenerator;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcBatchInfoRepositoryImpl;
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
import java.util.Arrays;
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
@Import(value = {DcBatchInfoDomainService.class, DcBatchInfoRepositoryImpl.class, UidGenerator.class})
@TestPropertySource("classpath:application.yml")
public class TestDcBatchInfoDomainService {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 批次信息-的领域服务
     */
    @Autowired
    private DcBatchInfoDomainService domainService;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcBatchInfoDO> batchDataList;


    /**
     * 批次信息-的领域实体信息
     */
    private DcBatchInfoDO oneData;


    /**
     * 数据操作类型，1，单个插入，2批量插入，其他不操作
     */
    private int insertType;


    /**
     * 单元测试的数据准备
     */
    @BeforeEach
    public void beforeSetData() {
        //获取数据
        batchDataList = getListDataBean();
        oneData = batchDataList.get(0);
    }


    public static List<DcBatchInfoDO> getListDataBean() {
        List<DcBatchInfoDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);
        for (int i = 0; i < INSERT_BATCH_NUM; i++) {
            batchDataList.add(getDataBean());
        }
        return batchDataList;
    }

    /**
     * 单元测试的获取数据对象
     */
    public static DcBatchInfoDO getDataBean() {
        DcBatchInfoDO paramBean = new DcBatchInfoDO();
        setPrimaryField(paramBean);
        setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private static void setPrimaryField(DcBatchInfoDO paramBean) {
        //批次的ID
        paramBean.setBatchId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private static void setDataField(DcBatchInfoDO paramBean) {
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
    private void assertDataList(List<DcBatchInfoDO> srcList, List<DcBatchInfoDO> targetList) {
        Map<String, DcBatchInfoDO> dataMapTmp = this.parseMap(targetList);
        for (DcBatchInfoDO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcBatchInfoDO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcBatchInfoDO> parseMap(List<DcBatchInfoDO> dataList) {
        Map<String, DcBatchInfoDO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcBatchInfoDO dataItemTmp : dataList) {
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
    private String getKey(DcBatchInfoDO paramBean) {
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
    private void assertData(DcBatchInfoDO src, DcBatchInfoDO target) {
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

        oneData.setBatchRunStatusBefore(oneData.getBatchRunStatus());
        this.setDataField(oneData);

        boolean updateRsp = domainService.updateRunStatus(oneData);
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

        DcBatchInfoDO paramDataTmp = new DcBatchInfoDO();
        paramDataTmp.setBatchId(oneData.getBatchId());
        DomainPage<DcBatchInfoDO> pageData = DomainPage.<DcBatchInfoDO>builder().size(INSERT_BATCH_NUM).page(0).data(paramDataTmp).build();
        DomainPage<List<DcBatchInfoDO>> pageRsp = domainService.queryPage(pageData);
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

        DcBatchInfoDO paramDataTmp = new DcBatchInfoDO();
        paramDataTmp.setBatchId(oneData.getBatchId());
        DcBatchInfoDO dataRsp = domainService.detail(paramDataTmp);
        this.assertData(oneData, dataRsp);
    }

    /**
     * 数据清理
     */
    @AfterEach
    public void afterCleanData() {
        if (insertType == InsertType.INSERT_TYPE_ONE) {
            oneData.setBatchList(Arrays.asList(oneData.getBatchId()));
            Boolean deleteRsp = domainService.deleteByIds(oneData);
            Assertions.assertEquals(true, deleteRsp);
        } else if (insertType == InsertType.INSERT_TYPE_BATCH) {
            List<Long> idList = new ArrayList<>(INSERT_BATCH_NUM);
            for (int i = 0; i < INSERT_BATCH_NUM; i++) {
                idList.add(batchDataList.get(i).getBatchId());
            }
            oneData.setBatchList(idList);
            Boolean deleteRsp = domainService.deleteByIds(oneData);
            Assertions.assertEquals(true, deleteRsp);

        }

    }

}
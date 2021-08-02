package com.liujun.task.task.service;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.schedule.application.taskflow.constant.TaskRunStatusEnum;
import com.liujun.task.task.entity.DcBatchLogDO;
import com.liujun.schedule.infrastructure.comm.uid.UidGenerator;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcBatchLogRepositoryImpl;
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
@Import(value = {DcBatchLogDomainService.class, DcBatchLogRepositoryImpl.class, UidGenerator.class})
@TestPropertySource("classpath:application.yml")
public class TestDcBatchLogDomainService {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 批次的日志批次状态-的领域服务
     */
    @Autowired
    private DcBatchLogDomainService domainService;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcBatchLogDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 批次的日志批次状态-的领域实体信息
     */
    private DcBatchLogDO oneData;


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
    public DcBatchLogDO getDataBean() {
        DcBatchLogDO paramBean = new DcBatchLogDO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcBatchLogDO paramBean) {
        //日志ID
        paramBean.setLogId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcBatchLogDO paramBean) {
        //批次号
        paramBean.setBatchId(RandomUtils.nextLong(0, 1 << 20));
        //任务的名称
        paramBean.setBatchName(RandomStringUtils.randomAlphabetic(20));
        //批次中任务最大并行度，防止一个批次占用过多的线程
        paramBean.setBatchConcurrent(RandomUtils.nextInt(0, 1 << 3));
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
    private void assertDataList(List<DcBatchLogDO> srcList, List<DcBatchLogDO> targetList) {
        Map<String, DcBatchLogDO> dataMapTmp = this.parseMap(targetList);
        for (DcBatchLogDO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcBatchLogDO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcBatchLogDO> parseMap(List<DcBatchLogDO> dataList) {
        Map<String, DcBatchLogDO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcBatchLogDO dataItemTmp : dataList) {
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
    private String getKey(DcBatchLogDO paramBean) {
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
    private void assertData(DcBatchLogDO src, DcBatchLogDO target) {
        //日志ID
        Assertions.assertEquals(src.getLogId(), target.getLogId());
        //批次号
        Assertions.assertEquals(src.getBatchId(), target.getBatchId());
        //任务的名称
        Assertions.assertEquals(src.getBatchName(), target.getBatchName());
        //批次中任务最大并行度，防止一个批次占用过多的线程
        Assertions.assertEquals(src.getBatchConcurrent(), target.getBatchConcurrent());
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

        DcBatchLogDO updLog = new DcBatchLogDO();
        updLog.setBatchId(oneData.getBatchId());
        updLog.setTaskRuntimeFlag(oneData.getTaskRuntimeFlag());
        updLog.setBatchRunStatus(TaskRunStatusEnum.SUCCESS.getStatus());
        updLog.setBeforeBatchRunStatus(oneData.getBatchRunStatus());

        boolean updateRsp = domainService.updateStatus(updLog);
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

        DcBatchLogDO paramDataTmp = new DcBatchLogDO();
        paramDataTmp.setLogId(oneData.getLogId());
        DomainPage<DcBatchLogDO> pageData = DomainPage.<DcBatchLogDO>builder().size(INSERT_BATCH_NUM).page(0).data(paramDataTmp).build();
        DomainPage<List<DcBatchLogDO>> pageRsp = domainService.queryPage(pageData);
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

        DcBatchLogDO paramDataTmp = new DcBatchLogDO();
        paramDataTmp.setLogId(oneData.getLogId());
        DcBatchLogDO dataRsp = domainService.detail(paramDataTmp);
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
                DcBatchLogDO dataItemTmp = batchDataList.get(i);
                boolean deleteRsp = domainService.deleteByIds(dataItemTmp);
                Assertions.assertEquals(true, deleteRsp);
            }
        }
    }

}
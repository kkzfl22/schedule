package com.liujun.schedule.domain.task.service;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.entity.DomainPage;
import com.liujun.schedule.application.taskflow.constant.TaskRunStatusEnum;
import com.liujun.schedule.domain.task.entity.DcTaskLogDO;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcTaskLogRepositoryImpl;
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
@Import(value = {DcTaskLogDomainService.class, DcTaskLogRepositoryImpl.class})
@TestPropertySource("classpath:application.yml")
public class TestDcTaskLogDomainService {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 任务的日志信息-的领域服务
     */
    @Autowired
    private DcTaskLogDomainService domainService;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcTaskLogDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 任务的日志信息-的领域实体信息
     */
    private DcTaskLogDO oneData;


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
    public DcTaskLogDO getDataBean() {
        DcTaskLogDO paramBean = new DcTaskLogDO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcTaskLogDO paramBean) {
        //日志ID
        paramBean.setLogId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcTaskLogDO paramBean) {
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
        //任务的的配制
        paramBean.setTaskCfg(RandomStringUtils.randomAlphabetic(20));
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
    private void assertDataList(List<DcTaskLogDO> srcList, List<DcTaskLogDO> targetList) {
        Map<String, DcTaskLogDO> dataMapTmp = this.parseMap(targetList);
        for (DcTaskLogDO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcTaskLogDO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcTaskLogDO> parseMap(List<DcTaskLogDO> dataList) {
        Map<String, DcTaskLogDO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcTaskLogDO dataItemTmp : dataList) {
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
    private String getKey(DcTaskLogDO paramBean) {
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
    private void assertData(DcTaskLogDO src, DcTaskLogDO target) {
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
        //任务的的配制
        Assertions.assertEquals(src.getTaskCfg(), target.getTaskCfg());
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


        DcTaskLogDO taskLogUpd = new DcTaskLogDO();
        taskLogUpd.setBatchId(oneData.getBatchId());
        taskLogUpd.setTaskId(oneData.getTaskId());
        taskLogUpd.setTaskRuntimeFlag(oneData.getTaskRuntimeFlag());
        taskLogUpd.setBeforeStatus(oneData.getTaskStatus());
        taskLogUpd.setTaskStatus(TaskRunStatusEnum.SUCCESS.getStatus());
        taskLogUpd.setTaskFinishTime(System.currentTimeMillis());
        taskLogUpd.setTaskMsg("success");

        boolean updateRsp = domainService.updateStatus(taskLogUpd);
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

        DcTaskLogDO paramDataTmp = new DcTaskLogDO();
        paramDataTmp.setLogId(oneData.getLogId());
        DomainPage<DcTaskLogDO> pageData = DomainPage.<DcTaskLogDO>builder().size(INSERT_BATCH_NUM).page(0).data(paramDataTmp).build();
        DomainPage<List<DcTaskLogDO>> pageRsp = domainService.queryPage(pageData);
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

        DcTaskLogDO paramDataTmp = new DcTaskLogDO();
        paramDataTmp.setLogId(oneData.getLogId());
        DcTaskLogDO dataRsp = domainService.detail(paramDataTmp);
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
                DcTaskLogDO dataItemTmp = batchDataList.get(i);
                boolean deleteRsp = domainService.deleteByIds(dataItemTmp);
                Assertions.assertEquals(true, deleteRsp);
            }
        }
    }

}
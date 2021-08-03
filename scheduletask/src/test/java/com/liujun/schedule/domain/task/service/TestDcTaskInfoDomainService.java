package com.liujun.schedule.domain.task.service;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.entity.DomainPage;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.domain.task.entity.DcTaskInfoDO;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.persistence.DcTaskInfoRepositoryImpl;
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
@Import(value = {DcTaskInfoDomainService.class, DcTaskInfoRepositoryImpl.class})
@TestPropertySource("classpath:application.yml")
public class TestDcTaskInfoDomainService {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 调度任务信息-的领域服务
     */
    @Autowired
    private DcTaskInfoDomainService domainService;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcTaskInfoDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 调度任务信息-的领域实体信息
     */
    private DcTaskInfoDO oneData;


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
     * 单元测试的获取数据对象
     */
    public static List<DcTaskInfoDO> getListDataBean() {
        List<DcTaskInfoDO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);
        for (int i = 0; i < INSERT_BATCH_NUM; i++) {
            batchDataList.add(getDataBean());
        }
        return batchDataList;
    }


    /**
     * 单元测试的获取数据对象
     */
    public static DcTaskInfoDO getDataBean() {
        DcTaskInfoDO paramBean = new DcTaskInfoDO();
        setPrimaryField(paramBean);
        setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private static void setPrimaryField(DcTaskInfoDO paramBean) {
        //任务的ID,
        paramBean.setTaskId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private static void setDataField(DcTaskInfoDO paramBean) {
        //目录的id
        paramBean.setTaskDirId(RandomUtils.nextLong(0, 1 << 20));
        //任务的名称
        paramBean.setTaskName(RandomStringUtils.randomAlphabetic(20));
        //任务的类型:关联DC_TASK_TYPE表
        paramBean.setTaskType(RandomStringUtils.randomAlphabetic(20));
        //任务的状态, 1:正常状态,0，停用状态
        paramBean.setStatus(RandomUtils.nextInt(0, 1 << 2));
        //任务的的配制
        paramBean.setTaskCfg(RandomStringUtils.randomAlphabetic(20));
        //重试配制:-1,无限重试;5,15,30.执行后5秒重试，以此类推,成功则不再重试。
        paramBean.setTaskRetry(RandomStringUtils.randomAlphabetic(10));
        //任务的描述
        paramBean.setTaskMsg(RandomStringUtils.randomAlphabetic(20));
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
    private void assertDataList(List<DcTaskInfoDO> srcList, List<DcTaskInfoDO> targetList) {
        Map<String, DcTaskInfoDO> dataMapTmp = this.parseMap(targetList);
        for (DcTaskInfoDO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcTaskInfoDO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcTaskInfoDO> parseMap(List<DcTaskInfoDO> dataList) {
        Map<String, DcTaskInfoDO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcTaskInfoDO dataItemTmp : dataList) {
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
    private String getKey(DcTaskInfoDO paramBean) {
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
    private void assertData(DcTaskInfoDO src, DcTaskInfoDO target) {
        //任务的ID,
        Assertions.assertEquals(src.getTaskId(), target.getTaskId());
        //目录的id
        Assertions.assertEquals(src.getTaskDirId(), target.getTaskDirId());
        //任务的名称
        Assertions.assertEquals(src.getTaskName(), target.getTaskName());
        //任务的类型:关联DC_TASK_TYPE表
        Assertions.assertEquals(src.getTaskType(), target.getTaskType());
        //任务的状态, 1:正常状态,0，停用状态
        Assertions.assertEquals(src.getStatus(), target.getStatus());
        //任务的的配制
        Assertions.assertEquals(src.getTaskCfg(), target.getTaskCfg());
        //重试配制:-1,无限重试;5,15,30.执行后5秒重试，以此类推,成功则不再重试。
        Assertions.assertEquals(src.getTaskRetry(), target.getTaskRetry());
        //任务的描述
        Assertions.assertEquals(src.getTaskMsg(), target.getTaskMsg());
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

        DcTaskInfoDO paramDataTmp = new DcTaskInfoDO();
        paramDataTmp.setTaskId(oneData.getTaskId());
        DomainPage<DcTaskInfoDO> pageData = DomainPage.<DcTaskInfoDO>builder().size(INSERT_BATCH_NUM).page(0).data(paramDataTmp).build();
        DomainPage<List<DcTaskInfoDO>> pageRsp = domainService.queryPage(pageData);
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

        DcTaskInfoDO paramDataTmp = new DcTaskInfoDO();
        paramDataTmp.setTaskId(oneData.getTaskId());
        DcTaskInfoDO dataRsp = domainService.detail(paramDataTmp);
        this.assertData(oneData, dataRsp);
    }

    /**
     * 数据清理
     */
    @AfterEach
    public void afterCleanData() {
        if (insertType == InsertType.INSERT_TYPE_ONE) {
            oneData.setTaskList(Arrays.asList(oneData.getTaskId()));
            boolean deleteRsp = domainService.deleteByIds(oneData);
            Assertions.assertEquals(true, deleteRsp);
        } else if (insertType == InsertType.INSERT_TYPE_BATCH) {
            List<Long> idList = new ArrayList<>(INSERT_BATCH_NUM);
            for (int i = 0; i < INSERT_BATCH_NUM; i++) {
                idList.add(batchDataList.get(i).getTaskId());
            }
            oneData.setTaskList(idList);
            boolean deleteRsp = domainService.deleteByIds(oneData);
            Assertions.assertEquals(true, deleteRsp);
        }
    }

}
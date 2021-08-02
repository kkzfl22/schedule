package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskInfoPO;
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
public class TestDcTaskInfoMapper {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 调度任务信息表(dc_task_info)的数据库操作
     */
    @Autowired
    private DcTaskInfoMapper mapper;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcTaskInfoPO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 调度任务信息表(dc_task_info)的数据库存储实体信息
     */
    private DcTaskInfoPO oneData;


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
    public DcTaskInfoPO getDataBean() {
        DcTaskInfoPO paramBean = new DcTaskInfoPO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcTaskInfoPO paramBean) {
        //任务的ID,
        paramBean.setTaskId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcTaskInfoPO paramBean) {
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
    private void assertDataList(List<DcTaskInfoPO> srcList, List<DcTaskInfoPO> targetList) {
        Map<String, DcTaskInfoPO> dataMapTmp = this.parseMap(targetList);
        for (DcTaskInfoPO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcTaskInfoPO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcTaskInfoPO> parseMap(List<DcTaskInfoPO> dataList) {
        Map<String, DcTaskInfoPO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcTaskInfoPO dataItemTmp : dataList) {
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
    private String getKey(DcTaskInfoPO paramBean) {
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
    private void assertData(DcTaskInfoPO src, DcTaskInfoPO target) {
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

        DcTaskInfoPO paramDataTmp = new DcTaskInfoPO();
        paramDataTmp.setTaskId(oneData.getTaskId());
        DcTaskInfoPO dataRsp = mapper.detail(paramDataTmp);
        this.assertData(oneData, dataRsp);
    }


    /**
     * 按一批id查询详细
     */
    @Test
    public void testQueryList() {
        int operatorRsp = mapper.insertList(batchDataList);
        Assertions.assertEquals(INSERT_BATCH_NUM, operatorRsp);
        insertType = InsertType.INSERT_TYPE_BATCH;

        DcTaskInfoPO paramDataTmp = new DcTaskInfoPO();
        List<Long> taskList = new ArrayList<>(InsertType.INSERT_TYPE_BATCH);

        for (DcTaskInfoPO item : batchDataList) {
            taskList.add(item.getTaskId());
        }
        paramDataTmp.setTaskList(taskList);
        List<DcTaskInfoPO> dataRsp = mapper.getTaskByTaskList(paramDataTmp);
        this.assertDataList(batchDataList, dataRsp);
    }

    /**
     * 数据清理
     */
    @AfterEach
    public void afterCleanData() {
        if (insertType == InsertType.INSERT_TYPE_ONE) {
            List<Long> idList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                idList.add(batchDataList.get(i).getTaskId());
            }
            oneData.setTaskList(idList);

            int deleteRsp = mapper.deleteByIds(oneData);
            Assertions.assertEquals(1, deleteRsp);
        } else if (insertType == InsertType.INSERT_TYPE_BATCH) {

            List<Long> idList = new ArrayList<>(INSERT_BATCH_NUM);
            for (int i = 0; i < INSERT_BATCH_NUM; i++) {
                idList.add(batchDataList.get(i).getTaskId());
            }
            oneData.setTaskList(idList);

            int deleteRsp = mapper.deleteByIds(oneData);
            Assertions.assertEquals(INSERT_BATCH_NUM, deleteRsp);

        }
    }

}
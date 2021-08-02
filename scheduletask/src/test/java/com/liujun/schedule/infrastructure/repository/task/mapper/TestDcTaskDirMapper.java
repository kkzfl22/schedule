package com.liujun.schedule.infrastructure.repository.task.mapper;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ddd.common.infrastructure.constant.InsertType;
import com.ddd.common.infrastructure.utils.LocalDateTimeUtils;
import com.liujun.schedule.infrastructure.repository.task.mapper.config.MyBatisScanConfiguration;
import com.liujun.schedule.infrastructure.repository.task.po.DcTaskDirPO;
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
public class TestDcTaskDirMapper {


    /**
     * 当前批量添加的个数
     */
    private static final int INSERT_BATCH_NUM = 3;


    /**
     * 任务目录信息表(dc_task_dir)的数据库操作
     */
    @Autowired
    private DcTaskDirMapper mapper;


    /**
     * 批量操作的数据存储容器
     */
    private List<DcTaskDirPO> batchDataList = new ArrayList<>(INSERT_BATCH_NUM);


    /**
     * 任务目录信息表(dc_task_dir)的数据库存储实体信息
     */
    private DcTaskDirPO oneData;


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
    public DcTaskDirPO getDataBean() {
        DcTaskDirPO paramBean = new DcTaskDirPO();
        this.setPrimaryField(paramBean);
        this.setDataField(paramBean);
        return paramBean;
    }

    /**
     * 设置主键属性
     *
     * @param paramBean 参数信息
     */
    private void setPrimaryField(DcTaskDirPO paramBean) {
        //任务目录的ID
        paramBean.setTaskDirId(RandomUtils.nextLong(0, 1 << 20));
    }

    /**
     * 设置普通属性值
     *
     * @param paramBean 参数信息
     */
    private void setDataField(DcTaskDirPO paramBean) {
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
    private void assertDataList(List<DcTaskDirPO> srcList, List<DcTaskDirPO> targetList) {
        Map<String, DcTaskDirPO> dataMapTmp = this.parseMap(targetList);
        for (DcTaskDirPO dataItemTmp : srcList) {
            String key = this.getKey(dataItemTmp);
            DcTaskDirPO target = dataMapTmp.get(key);
            this.assertData(dataItemTmp, target);
        }
    }

    /**
     * 将数据集合转换为map
     *
     * @param dataList 数据集
     */
    private Map<String, DcTaskDirPO> parseMap(List<DcTaskDirPO> dataList) {
        Map<String, DcTaskDirPO> dataMapTmp = new HashMap<>(dataList.size());
        for (DcTaskDirPO dataItemTmp : dataList) {
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
    private String getKey(DcTaskDirPO paramBean) {
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
    private void assertData(DcTaskDirPO src, DcTaskDirPO target) {
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

        DcTaskDirPO paramDataTmp = new DcTaskDirPO();
        paramDataTmp.setTaskDirId(oneData.getTaskDirId());
        DcTaskDirPO dataRsp = mapper.detail(paramDataTmp);
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
                DcTaskDirPO dataItemTmp = batchDataList.get(i);
                int deleteRsp = mapper.deleteByIds(dataItemTmp);
                Assertions.assertEquals(1, deleteRsp);
            }
        }
    }

}
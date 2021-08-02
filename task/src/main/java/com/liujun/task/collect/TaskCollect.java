package com.liujun.task.collect;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务的收集器，用于收集执行的任务
 *
 * @author liujun
 * @since 2021/8/2
 */
public class TaskCollect {

    /**
     * 用于存放运行任务的map
     */
    private static final Map<String, TaskInf> TASK_CONTAINER_MAP = new HashMap<>();


    /**
     * 任务容器对象
     */
    public static final TaskCollect INSTANCE = new TaskCollect();

    private TaskCollect() {

    }


    /**
     * 将任务的接口加入容器
     *
     * @param instance
     */
    public void put(TaskInf instance) {
        if (null == instance) {
            return;
        }

        String type = instance.getType();
        if (StringUtils.isEmpty(type)) {
            return;
        }

        TASK_CONTAINER_MAP.put(type, instance);
    }


    /**
     * 批量添加任务集合
     *
     * @param tasList 任务列表
     */
    public void putList(List<TaskInf> tasList) {
        if (CollUtil.isEmpty(tasList)) {
            return;
        }

        for (TaskInf taskItem : tasList) {
            this.put(taskItem);
        }
    }


    /**
     * 获取一个任务
     *
     * @param type 任务的类型
     * @return 任务信息
     */
    public TaskInf get(String type) {
        return TASK_CONTAINER_MAP.get(type);
    }

    /**
     * 返回一个只读的map
     *
     * @return 只读的map
     */
    public Map<String, TaskInf> getAll() {
        return Collections.unmodifiableMap(TASK_CONTAINER_MAP);
    }

}

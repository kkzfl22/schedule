package com.liujun.task.collect;

import com.liujun.task.entity.TaskEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务运行中的容器对象，用于临时存储运行中的数据
 *
 * @author liujun
 * @version 0.0.1
 * @date 2018/09/11
 */
public class TaskContainer {

    /**
     * 对数存储对象
     */
    private Map<String, Object> commonParam = new HashMap<>();


    /**
     * 任务的信息
     */
    private TaskEntity taskInfo;


    public TaskContainer(TaskEntity taskInfo) {
        this.taskInfo = taskInfo;
    }

    /**
     * 获取任务的信息
     *
     * @return
     */
    public TaskEntity getTaskInfo() {
        return taskInfo;
    }

    /**
     * 向公共参数中添加参数
     *
     * @param key   存入参数的key
     * @param value 存入参数的值
     */
    public void put(String key, Object value) {
        this.commonParam.put(key, value);
    }

    /**
     * 获取java对象的方法
     *
     * @param key 存入key
     * @param <T> 转换后的java对象，加入了强制转换的操作
     * @return
     */
    public <T> T get(String key) {
        return (T) this.commonParam.get(key);
    }

    /**
     * 进行对象的移除操作
     *
     * @param key
     */
    public void remove(String key) {
        this.commonParam.remove(key);
    }

    /**
     * 清空参数
     */
    public void clean() {
        this.commonParam.clear();
    }

    /**
     * 检查key是否存在
     *
     * @param key
     * @return
     */
    public boolean containsKey(String key) {
        return this.commonParam.containsKey(key);
    }


    /**
     * 当值不存在时需要返回返回值的类型
     *
     * @param key 存入的key信息
     * @param def 默认的值信息，需要与存入的类型相同，否则会报错误
     * @return
     */
    public <T> T getValueOrDef(String key, T def) {
        T value = (T) this.commonParam.get(key);

        if (null != value) {
            return value;
        } else {
            return def;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContextContainer{");
        sb.append("commonParam=").append(commonParam);
        sb.append('}');
        return sb.toString();
    }
}

package com.liujun.task.collect;

/**
 * 任务的运行接口
 *
 * @author liujun
 * @since 2021/8/2
 */
public interface TaskInf {


    /**
     * 当前任务的类型，用于做类型的唯一标识
     *
     * @return 类型的名称信息
     */
    String getType();

    /**
     * 任务的执行
     *
     * @param container 容器信息
     * @return true 执行成功，false 执行失败
     */
    boolean execute(TaskContainer container);

}

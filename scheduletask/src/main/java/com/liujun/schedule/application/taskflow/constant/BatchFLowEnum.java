package com.liujun.schedule.application.taskflow.constant;

/**
 * 批次处理的信息
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/11
 */
public enum BatchFLowEnum {

    /**
     * 输入的参数批次的id
     */
    INPUT_BATCH_ID,

    /**
     * 每个批次执行的标识信息
     */
    PROC_RUNTIME_FLAG,

    /**
     * 用于存储数据当前批次下所任务的id
     */
    PROC_DATA_BATCH_TASK_ID,


    /**
     * 批次信息
     */
    PROC_BATCH_INFO,

    /**
     * 用于存储数据当前批次下所任务
     */
    PROC_DATA_BATCH_TASK,

    /**
     * 存储类型信息
     */
    PROC_DATA_TYPE_MAP,

    /**
     * 顶点的依赖关系，用于构建图
     */
    PROC_DATA_DEPEND_LINK,

    /**
     * 首次启动的顶点信息
     */
    PROC_DATA_FIRST_IN_VERTEX,
    ;
}

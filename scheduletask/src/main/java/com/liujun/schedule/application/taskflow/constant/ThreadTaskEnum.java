package com.liujun.schedule.application.taskflow.constant;

/**
 * 进行线程的任务运行流程操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
public enum ThreadTaskEnum {

    /**
     * 输入的参数，task的id
     */
    INPUT_TASK_ID,

    /**
     * 输入的参数，批次号
     */
    INPUT_BATCH_ID,

    /**
     * 输入参数时间标识符
     */
    INPUT_RUNTIME_FLAG,

    /**
     * 当前运行的任务信息
     */
    PROC_INPUT_TASK_INFO,

    /**
     * 当前运行的次数
     */
    PROC_INPUT_RUN_NUM,

    /**
     * 用于标识当前流程是否结束的标识
     */
    PROC_RUN_FINISH_FLAG,


    /**
     * 任务的运行结果
     */
    PROC_TASK_RSP_FLAG,


    /**
     * 错误码信息
     */
    PROC_TASK_RSP_ERROR_CODE,
    ;
}

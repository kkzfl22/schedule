package com.liujun.schedule.application.taskflow.constant;

/**
 * 进行线程的任务运行流程操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
public enum EtlThreadTaskEnum {

  /** 输入的参数，task的id */
  INPUT_TASK_ID,

  /** 输入的参数，批次号 */
  INPUT_BATCH_ID,

  /** 输入参数时间标识符 */
  INPUT_RUNTIME_FLAG,

  /** 用于标识当前流程是否结束的标识 */
  PROC_RUN_FINISH_FLAG,

  /** 当前任务运行结果的响应 */
  PROC_RUN_TASK_RSP,

  /** 重试次数 */
  PROC_RUN_RETRY_NUM,
  ;
}

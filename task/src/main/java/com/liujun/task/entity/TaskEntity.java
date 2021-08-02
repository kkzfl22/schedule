package com.liujun.task.entity;

/**
 * 任务运行信息
 *
 * @author liujun
 * @since 2021/8/2
 */
public class TaskEntity {


    /**
     * 当前运行任务的批次号
     */
    private Long batchId;


    /**
     * 任务的ID,
     */
    private Long taskId;

    /**
     * 任务的id
     */
    private Long runFlag;

    /**
     * 任务的信息
     */
    private DcTaskInfo taskInfo;

    /**
     * 当前的运行次数
     */
    private Integer runNum;


    public TaskEntity(Long batchId, Long runFlag, DcTaskInfo taskInfo, Integer runNum) {
        this.batchId = batchId;
        this.runFlag = runFlag;
        this.taskInfo = taskInfo;
        this.runNum = runNum;
    }

    public Long getBatchId() {
        return batchId;
    }

    public Long getRunFlag() {
        return runFlag;
    }

    public DcTaskInfo getTaskInfo() {
        return taskInfo;
    }

    public Integer getRunNum() {
        return runNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaskEntity{");
        sb.append("batchId=").append(batchId);
        sb.append(", runFlag=").append(runFlag);
        sb.append(", taskInfo=").append(taskInfo);
        sb.append(", runNum=").append(runNum);
        sb.append('}');
        return sb.toString();
    }
}

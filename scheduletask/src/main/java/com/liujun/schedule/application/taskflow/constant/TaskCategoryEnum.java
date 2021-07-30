package com.liujun.schedule.application.taskflow.constant;

/**
 * @author lianhl
 * @version 0.0.1
 * @date 2019/12/11
 * 任务类型常量定义
 */
public enum TaskCategoryEnum {

    DATA_IMPORT("DATA_IMPORT"),

    ;

    private String name;

    TaskCategoryEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TaskCategoryEnum{" +
                "name='" + name + '\'' +
                '}';
    }
}

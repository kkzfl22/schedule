package com.liujun.schedule.domain.task.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 调度任务信息-的领域实体信息
 *
 * @version 0.0.1
 * @author liujun
 */
@Getter
@Setter
@ToString
public class DcTaskTypeDO {


    /**
     * 任务的类型,
     */
    private String type;

    /**
     * 类型的信息
     */
    private String typeName;

    /**
     * 类型的描述
     */
    private String typeMsg;

    /**
     * 任务的配制信息,以JSON格式
     */
    private String typeCfg;

}
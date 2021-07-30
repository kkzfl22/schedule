package com.liujun.schedule.application.taskflow.constant;

/**
 * 虚拟顶点信息
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
public enum GraphPointEnum {

  /** 虚拟结束顶点，用来标识出结束任务的结束点,由系统自动添加完成 */
  VIRTUAL_FINISH_POINT(Long.MIN_VALUE),

  /** 不存在环的标识 */
  NOT_CYCLE_FLAG(-1L),
  ;

  /** 顶点信息 */
  private Long point;

  GraphPointEnum(Long point) {
    this.point = point;
  }

  public Long getPoint() {
    return point;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("GraphPointEnum{");
    sb.append("point=").append(point);
    sb.append('}');
    return sb.toString();
  }
}

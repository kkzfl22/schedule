package com.liujun.schedule.application.taskflow.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 图中的顶点对象
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
public class GraphPointData {

  /** 入度的顶点 */
  private List<Long> inPointLink;

  /** 顶点的入度个数 */
  private AtomicInteger inPointLinkNum;

  /** 当前的顶点的对象 */
  private Long pointData;

  /** 出度的顶点对象 */
  private List<Long> outPointLink;

  /** 出度的顶点数量 */
  private AtomicInteger outPointLinkNum;

  public static class Builder {

    /** 入度的顶点 */
    private List<Long> inputLink = new LinkedList<>();

    /** 当前的顶点的对象 */
    private final Long pointData;

    /** 出度的顶点对象 */
    private List<Long> outputLink = new LinkedList<>();

    public Builder(Long pointData) {
      this.pointData = pointData;
    }

    public Builder addInPoint(Long inPoint) {
      this.inputLink.add(inPoint);
      return this;
    }

    public Builder addOutPoint(Long outPoint) {
      this.outputLink.add(outPoint);
      return this;
    }

    public GraphPointData build() {
      return new GraphPointData(this.inputLink, this.pointData, this.outputLink);
    }
  }

  public GraphPointData(List<Long> inPointLink, Long pointData, List<Long> outPointLink) {
    this.inPointLink = inPointLink;
    this.inPointLinkNum = new AtomicInteger(inPointLink.size());
    this.pointData = pointData;
    this.outPointLink = outPointLink;
    this.outPointLinkNum = new AtomicInteger(outPointLink.size());
  }

  public List<Long> getInPointLink() {
    return Collections.unmodifiableList(inPointLink);
  }

  public AtomicInteger getInPointLinkNum() {
    return inPointLinkNum;
  }

  public Long getPointData() {
    return pointData;
  }

  public List<Long> getOutPointLink() {
    return Collections.unmodifiableList(outPointLink);
  }

  public AtomicInteger getOutPointLinkNum() {
    return outPointLinkNum;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("GraphPointData{");
    sb.append("inPointLink=").append(inPointLink);
    sb.append(", inPointLinkNum=").append(inPointLinkNum.get());
    sb.append(", pointData=").append(pointData);
    sb.append(", outPointLink=").append(outPointLink);
    sb.append(", outPointLinkNum=").append(outPointLinkNum.get());
    sb.append('}');
    return sb.toString();
  }
}

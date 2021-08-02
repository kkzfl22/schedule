package com.liujun.schedule.application.taskflow.graph;

import com.liujun.schedule.application.taskflow.constant.GraphPointEnum;
import com.liujun.task.task.entity.DcBatchTaskDO;
import com.liujun.task.task.entity.DcBatchTaskDependDO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 进行图相关数据计算操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
public class GraphEtl {

  public static final GraphEtl INSTANCE = new GraphEtl();

  /**
   * 构建依赖关系
   *
   * @param point 顶点
   * @param dependList 依赖关系
   * @return
   */
  public Map<Long, GraphPointData> buildDependEtl(
          List<DcBatchTaskDO> point, List<DcBatchTaskDependDO> dependList) {

    if (null == point || point.isEmpty()) {
      return new HashMap<>(0);
    }

    // 构建图
    Graph graph = this.buildGraph(point, dependList);

    // 构建逆邻接表
    graph.buildReverselinkTable();
    // 获取邻接表
    List<Integer>[] linkData = graph.linkedTable();
    // 获取逆邻接表
    List<Integer>[] reVertexData = graph.reverselinkedTable();

    // 得到所有顶点
    List<Long> vertex = graph.getVertextList();

    // 构建顶点
    Map<Long, GraphPointData.Builder> pointBuildDataMap = new HashMap<>(vertex.size());
    // 构建邻接表
    this.buildOutLinkData(graph, pointBuildDataMap, linkData);
    // 构建逆邻接表
    this.buildInLinkData(graph, pointBuildDataMap, reVertexData);
    // 添加结束顶点
    addVirtualFinish(graph, pointBuildDataMap);

    Map<Long, GraphPointData> pointDataMap = new HashMap<>(vertex.size());

    for (Map.Entry<Long, GraphPointData.Builder> entryData : pointBuildDataMap.entrySet()) {
      pointDataMap.put(entryData.getKey(), entryData.getValue().build());
    }

    return pointDataMap;
  }

  /**
   * 找到入度为0的顶点
   *
   * @param point 顶点
   * @param dependList 依赖关系
   * @return
   */
  public List<Long> getInputVertex(
      List<DcBatchTaskDO> point, List<DcBatchTaskDependDO> dependList) {

    // 获取所有顶点
    List<Long> taskList = getPointList(point);

    // 构建图
    Graph graph = new Graph(taskList);

    // 添加依赖关系
    this.setTaskEdge(graph, dependList);

    return graph.getInputVertext();
  }

  /**
   * 构建图
   *
   * @param point 所有顶点
   * @param dependList 依赖关系
   * @return 图对象
   */
  private Graph buildGraph(
      List<DcBatchTaskDO> point, List<DcBatchTaskDependDO> dependList) {
    // 获取所有顶点
    List<Long> taskList = getPointList(point);

    // 构建图
    Graph graph = new Graph(taskList);

    // 添加依赖关系
    this.setTaskEdge(graph, dependList);

    return graph;
  }

  /**
   * 进行依赖环的检查
   *
   * @param point 顶点
   * @param dependList 依赖集合
   * @return 入环的顶点，-1 无环
   */
  public long graphCycleCheck(List<DcBatchTaskDO> point, List<DcBatchTaskDependDO> dependList) {

    if (null == point || point.isEmpty()) {
      return GraphPointEnum.NOT_CYCLE_FLAG.getPoint();
    }

    // 当依赖关系不存在，则当前直接无环
    if (null == dependList || dependList.isEmpty()) {
      return GraphPointEnum.NOT_CYCLE_FLAG.getPoint();
    }

    // 构建图
    Graph graph = this.buildGraph(point, dependList);

    return graph.checkCycle();
  }

  /**
   * 添加虚拟的结束顶点
   *
   * @param graph 点
   */
  private void addVirtualFinish(Graph graph, Map<Long, GraphPointData.Builder> graphData) {
    List<Long> outVertexList = graph.getOutVertext();

    GraphPointData.Builder pointData =
        new GraphPointData.Builder(GraphPointEnum.VIRTUAL_FINISH_POINT.getPoint());
    for (Long vertex : outVertexList) {
      pointData.addInPoint(vertex);
    }

    // 添加之前物理顶点到结束顶点的依赖
    for (Long vertex : outVertexList) {
      graphData.get(vertex).addOutPoint(GraphPointEnum.VIRTUAL_FINISH_POINT.getPoint());
    }

    graphData.put(GraphPointEnum.VIRTUAL_FINISH_POINT.getPoint(), pointData);
  }

  /**
   * 获取所有图
   *
   * @param point
   * @return
   */
  private List<Long> getPointList(List<DcBatchTaskDO> point) {
    List<Long> dataList = new LinkedList<>();

    if (null == point || point.isEmpty()) {
      return dataList;
    }

    for (DcBatchTaskDO item : point) {
      dataList.add(item.getTaskId());
    }

    return dataList;
  }

  /**
   * 添加任务的依赖关系
   *
   * @param graph 图对象
   * @param dependList 依赖数据
   */
  private void setTaskEdge(Graph graph, List<DcBatchTaskDependDO> dependList) {

    if (null == dependList || dependList.isEmpty()) {
      return;
    }

    // 设置依赖关系的有向边
    for (DcBatchTaskDependDO taskDepend : dependList) {
      graph.addEdge(taskDepend.getPrevTaskId(), taskDepend.getTaskId());
    }
  }

  /**
   * 构建出度信息
   *
   * @param graph 当前的图对象
   * @param graphDataMap 图数据存储的map
   * @param linkdata 邻接表
   */
  private void buildOutLinkData(
      Graph graph, Map<Long, GraphPointData.Builder> graphDataMap, List<Integer>[] linkdata) {
    for (int i = 0; i < linkdata.length; i++) {
      long valuedata = graph.getSrcByIndex(i);
      GraphPointData.Builder pointBuilder = new GraphPointData.Builder(valuedata);

      for (int j = 0; j < linkdata[i].size(); j++) {
        long valueData = graph.getSrcByIndex(linkdata[i].get(j));
        pointBuilder.addOutPoint(valueData);
      }

      graphDataMap.put(valuedata, pointBuilder);
    }
  }

  /**
   * 构建入度信息
   *
   * @param graph 当前的图对象
   * @param graphDataMap 图数据存储的map
   * @param reverData 逆邻接表
   */
  private void buildInLinkData(
      Graph graph, Map<Long, GraphPointData.Builder> graphDataMap, List<Integer>[] reverData) {
    for (int i = 0; i < reverData.length; i++) {
      long valueData = graph.getSrcByIndex(i);

      GraphPointData.Builder pointBuilder = graphDataMap.get(valueData);

      for (int j = 0; j < reverData[i].size(); j++) {
        long intData = graph.getSrcByIndex(reverData[i].get(j));
        pointBuilder.addInPoint(intData);
      }
    }
  }
}

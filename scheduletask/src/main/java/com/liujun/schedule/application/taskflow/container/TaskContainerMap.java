package com.liujun.schedule.application.taskflow.container;

import com.liujun.schedule.application.taskflow.graph.ContainData;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 任务的运行map
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/12/13
 */
public class TaskContainerMap {

  public static final TaskContainerMap INSTANCE = new TaskContainerMap();

  /** 按批次存储当前正在运行的任务关系 */
  private static final ConcurrentHashMap<Long, ContainData> CONTAINER_MAP =
      new ConcurrentHashMap<>();

  /**
   * 将运行时的批次任务放入共享容器在中
   *
   * @param batchId 批次的id
   * @param graphPointMap 构建的对象
   * @return 获取数据
   */
  public boolean put(Long batchId, ContainData graphPointMap) {
    if (CONTAINER_MAP.containsKey(batchId)) {
      return false;
    }

    ContainData putData = CONTAINER_MAP.putIfAbsent(batchId, graphPointMap);

    if (null == putData) {
      return true;
    } else if (putData == graphPointMap) {
      return true;
    }
    return false;
  }

  /**
   * 获取共享容器中的数据
   *
   * @param batchId
   * @return
   */
  public ContainData get(Long batchId) {
    return CONTAINER_MAP.get(batchId);
  }

  /**
   * 进行容器中的数据移除操作
   *
   * @param batchId 批次信息
   */
  public void remove(Long batchId) {
    if (CONTAINER_MAP.containsKey(batchId)) {
      CONTAINER_MAP.remove(batchId);
    }
  }
}

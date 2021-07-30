package com.liujun.schedule.application.taskflow.graph;

import com.liujun.schedule.application.taskflow.constant.GraphPointEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 图，用来计算任务调度的依赖关系
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/01/13
 */
public class Graph {

    /**
     * 按值与索引的对应关系
     */
    private Map<Long, Integer> srcToIndexMap;

    /**
     * 索引与值的关系对应
     */
    private Map<Integer, Long> indexToSrcMap;

    /**
     * 逆邻接表
     */
    private List<Integer> rdj[];

    /**
     * 顶点的个数
     */
    private int v;

    /**
     * 邻接表
     */
    private List<Integer> adj[];

    /**
     * 添加不存在的顶点
     */
    private List<Integer> notExistsVertext;

    public Graph(List<Long> depentIdList) {

        if (depentIdList.size() < 1) {
            throw new IllegalArgumentException("grap point is 0");
        }

        // 以防数据重复
        Set<Long> vertextValueSet = new HashSet<>();

        for (int i = 0; i < depentIdList.size(); i++) {
            vertextValueSet.add(depentIdList.get(i));
        }

        int v = vertextValueSet.size();

        // 初始化顶点数
        this.v = v;
        this.adj = new LinkedList[v];
        // 初始化领接表
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }

        this.srcToIndexMap = new HashMap<>(v);
        this.indexToSrcMap = new HashMap<>(v);

        int index = 0;
        for (long vertextItem : vertextValueSet) {
            srcToIndexMap.put(vertextItem, index);
            indexToSrcMap.put(index, vertextItem);
            index++;
        }

        vertextValueSet = null;
    }

    /**
     * 检查顶点是否存在
     *
     * @param vertext 顶点编号
     * @return true 存在 false 不存在
     */
    public boolean existsVertext(int vertext) {
        return srcToIndexMap.containsKey(vertext);
    }

    public int getVnum() {
        return this.v;
    }

    public void addNotExistsVertext(List<Integer> vertextList) {
        if (null == notExistsVertext) {
            notExistsVertext = new ArrayList<>();
        }

        // 添加不存在的顶点
        if (null != vertextList && !vertextList.isEmpty()) {
            for (int i = 0; i < vertextList.size(); i++) {
                notExistsVertext.add(vertextList.get(i));
            }
        }
    }

    /**
     * 检查是否包含添加的顶点，
     *
     * @param vertext 顶点
     * @return 默认返回false, 即不包括
     */
    private boolean notExistsCheck(int vertext) {
        if (null != notExistsVertext) {
            return notExistsVertext.contains(vertext);
        }

        return false;
    }

    /**
     * 获取顶点集合
     *
     * @return
     */
    public List<Long> getVertextList() {
        List<Long> result = new LinkedList<>();

        for (int i = 0; i < v; i++) {
            result.add(this.indexToSrcMap.get(i));
        }
        return result;
    }

    /**
     * s 先于t,则添加一条s到t 边
     *
     * <p>如果a先于b执行，则设置一条a指向b的边
     *
     * @param s
     * @param t
     */
    public void addEdge(long s, long t) {

        int outs = srcToIndexMap.get(s);
        int outt = srcToIndexMap.get(t);

        // 在顶点s，添加一条到t的边
        if (!adj[outs].contains(outt)) {
            adj[outs].add(outt);
        }
    }

    /**
     * s何时是否存在有向边
     *
     * @param s 起始顶点
     * @param t 结束顶点
     * @return
     */
    public boolean checkEdge(int s, int t) {
        int outs = srcToIndexMap.get(s);
        int outt = srcToIndexMap.get(t);

        // 在顶点s，添加一条到t的边
        return adj[outs].contains(outt);
    }

    /**
     * 移除数据的有向边操作
     *
     * @param s
     * @param t
     */
    public void removeEdge(int s, int t) {
        int outs = srcToIndexMap.get(s);
        int outt = srcToIndexMap.get(t);

        // 在顶点s，添加一条到t的边
        if (!adj[outs].contains(outt)) {
            adj[outs].remove(outt);
        }
    }

    public Long getSrcByIndex(int index) {
        return this.indexToSrcMap.get(index);
    }

    public int getIndexBySrc(Long src) {
        return this.srcToIndexMap.get(src);
    }

    /**
     * 依赖环地检测
     *
     * @return -1表示不存在环，其他表示存在环
     */
    public Long checkCycle() {
        // 1,找到入度为0的顶点
        int[] ingree = new int[v];

        // 统计顶点的个数
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int ponts = adj[i].get(j);
                ingree[ponts]++;
            }
        }

        // 首先将入库为0的顶点放入队列
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < v; i++) {
            if (ingree[i] == 0) {
                linkedList.add(i);
            }
        }

        // 然后开始打印顶点
        while (!linkedList.isEmpty()) {
            int pointsval = linkedList.remove();
            // 将对应的顶点的入度减1
            for (int i = 0; i < adj[pointsval].size(); i++) {
                int k = adj[pointsval].get(i);
                ingree[k]--;
                // 如果当间当前边的入库不为0，说明还有边没有访问到，需要
                if (ingree[k] == 0) {
                    linkedList.add(k);
                }
            }
        }

        // 检查环的存在操作
        int cycleId = -1;

        // 当完成之后，检查表的表中的入度，如果发现还有不为0，说明存在环
        for (int i = 0; i < ingree.length; i++) {
            if (ingree[i] != 0) {
                cycleId = i;
                break;
            }
        }

        if (cycleId != -1) {
            return this.indexToSrcMap.get(cycleId);
        }

        return GraphPointEnum.NOT_CYCLE_FLAG.getPoint();
    }

    /**
     * 命名用kahn算法对任务进行排序
     */
    public List<Long> orderKahn() {
        // 1,找到入度为0的顶点
        int[] ingree = new int[v];

        // 统计顶点的个数
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int ponts = adj[i].get(j);
                ingree[ponts]++;
            }
        }

        // 首先将入库为0的顶点放入队列
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < v; i++) {
            if (ingree[i] == 0) {
                linkedList.add(i);
            }
        }

        List<Long> linkedOrderGraph = new LinkedList<>();

        // 然后开始打印顶点
        while (!linkedList.isEmpty()) {
            int pointsval = linkedList.remove();

            linkedOrderGraph.add(this.indexToSrcMap.get(pointsval));

            // 将对应的顶点的入度减1
            for (int i = 0; i < adj[pointsval].size(); i++) {
                int k = adj[pointsval].get(i);
                ingree[k]--;
                // 如果当间当前边的入库不为0，说明还有边没有访问到，需要
                if (ingree[k] == 0) {
                    linkedList.add(k);
                }
            }
        }

        return linkedOrderGraph;
    }

    /**
     * 获取邻接表
     *
     * @return 逆邻接表
     */
    public List<Integer>[] linkedTable() {
        return adj;
    }

    /**
     * 获取逆邻接表
     *
     * @return 逆邻接表
     */
    public List<Integer>[] reverselinkedTable() {
        return rdj;
    }

    /**
     * 构建逆邻接表
     *
     * @return 逆邻接表
     */
    public void buildReverselinkTable() {
        rdj = new LinkedList[v];
        // 初始化逆邻接表
        for (int i = 0; i < v; i++) {
            rdj[i] = new LinkedList<>();

        }

        // 开始构建逆邻表
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int targetPoint = adj[i].get(j);
                rdj[targetPoint].add(i);
            }
        }
    }

    /**
     * 返回入度为0的顶点，通过邻接表
     *
     * @return 入库为0的顶点链表
     */
    public List<Long> getInputVertext() {
        return getInputVertext(null);
    }

    /**
     * 找到出度为0的顶点，通过逆邻接表
     *
     * @return 所有的末尾依赖
     */
    public List<Long> getOutVertext() {
        return this.getInputVertext(this.rdj);
    }

    /**
     * 找到当前顶点中入库为0的需点
     *
     * <p>返回转换
     *
     * @return 转换后的顶点编号，非内部编号
     */
    public List<Long> getInputVertext(List<Integer>[] linkerTable) {

        List<Integer> vertextAdj[] = adj;

        // 如果设置了邻接表，则优先使用邻接表
        if (null != linkerTable) {
            vertextAdj = linkerTable;
        }

        // 1,取到所有入库为0的顶点
        int[] ingree = new int[this.v];

        // 统计顶点的个数
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < vertextAdj[i].size(); j++) {
                int ponts = vertextAdj[i].get(j);
                ingree[ponts]++;
            }
        }
        // 首先将入库为0的顶点放入队列
        LinkedList<Long> linkedList = new LinkedList<>();

        for (int i = 0; i < v; i++) {
            if (ingree[i] == 0) {
                // 进行錾顶点编号
                linkedList.add(this.getSrcByIndex(i));
            }
        }

        return linkedList;
    }
}

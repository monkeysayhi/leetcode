package com.msh.solutions._210_Course_Schedule_2;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/26.
 */
public class Solution {
  // 拓扑排序，容易误判“1,[]”等badcase
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int nodeCnt = numCourses;
    int[][] reversedEdges = prerequisites;
    // special badcase: 1,[]
    if (nodeCnt == 0 || reversedEdges == null) {
      return new int[0];
    }

    Map<Integer, Set<Integer>> graph = constructGraph(nodeCnt, reversedEdges);
    return topoSort(graph);
  }

  private int[] topoSort(Map<Integer, Set<Integer>> graph) {
    int[] indgs = getIndegrees(graph);

    Queue<Integer> que = new LinkedList<>();
    for (Integer u : graph.keySet()) {
      if (indgs[u] == 0) {
        que.offer(u);
      }
    }
    int sortedNodeCnt = 0;
    int[] sortedNodes = new int[graph.size()];
    while (!que.isEmpty()) {
      int u = que.poll();
      sortedNodes[sortedNodeCnt] = u;
      sortedNodeCnt++;
      for (Integer v : graph.get(u)) {
        indgs[v]--;
        if (indgs[v] == 0) {
          que.offer(v);
        }
      }
    }
    if (sortedNodeCnt < graph.size()) {
      return new int[0];
    }
    return sortedNodes;
  }

  private int[] getIndegrees(Map<Integer, Set<Integer>> graph) {
    int[] indgs = new int[graph.size()];
    for (Integer u : graph.keySet()) {
      for (Integer v : graph.get(u)) {
        indgs[v]++;
      }
    }
    return indgs;
  }

  private Map<Integer, Set<Integer>> constructGraph(int nodeCnt,
                                                    int[][] reversedEdges) {
    Map<Integer, Set<Integer>> graph = new HashMap<>(nodeCnt);
    for (int u = 0; u < nodeCnt; u++) {
      graph.put(u, new HashSet<Integer>());
    }
    for (int i = 0; i < reversedEdges.length; i++) {
      int u = reversedEdges[i][1];
      int v = reversedEdges[i][0];
      graph.get(u).add(v);
    }
    return graph;
  }
}

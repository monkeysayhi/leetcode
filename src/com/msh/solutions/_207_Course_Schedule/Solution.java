package com.msh.solutions._207_Course_Schedule;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/26.
 */
public class Solution {
  // 拓扑排序，当且仅当有环时，图无法拓扑排序
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    int nodeCnt = numCourses;
    int[][] reversedEdges = prerequisites;
    if (nodeCnt == 0
        || reversedEdges == null || reversedEdges.length == 0) {
      return true;
    }
    assert reversedEdges[0] != null && reversedEdges[0].length == 2;

    Map<Integer, Set<Integer>> graph = constructGraph(nodeCnt,
        reversedEdges);
    return topoSort(graph);
  }

  private boolean topoSort(Map<Integer, Set<Integer>> graph) {
    int[] indgs = getIndegrees(graph);

    Queue<Integer> que = new LinkedList<>();
    for (Integer u : graph.keySet()) {
      if (indgs[u] == 0) {
        que.offer(u);
      }
    }
    int sortedNodeCnt = 0;
    while (!que.isEmpty()) {
      int u = que.poll();
      sortedNodeCnt++;
      for (Integer v : graph.get(u)) {
        indgs[v]--;
        if (indgs[v] == 0) {
          que.offer(v);
        }
      }
    }
    return sortedNodeCnt == graph.size();
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

package com.msh.solutions._261_Graph_Valid_Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by monkeysayhi on 2017/12/26.
 */
// http://www.lintcode.com/en/problem/graph-valid-tree/#

public class Solution {

  private enum VisitStatus {
    INIT, VISITING, VISITED
  }

  /**
   * @param n     an integer
   * @param edges a list of undirected edges
   * @return true if it's a valid tree, or false
   */
  // 判断树：连通 && 无环 <=> 连通 && e+1==n <=> 无环 && e+1==n
  // 判断连通用 dfs、bfs均可
  // 判断无环，无向图上用 dfs 更简单
  // 注意：需要在整个图上判断是否有环，如果图不是连通的，则需要防止一个子图无环，另一个子图有环
  public boolean validTree(int n, int[][] edges) {
    if (n == 0) {
      return true;
    }
    if (n <= 1 && edges == null) {
      return true;
    }

    if (n - 1 != edges.length) {
      return false;
    }

    Map<Integer, Set<Integer>> graph = constructGraph(n, edges);
    return checkWuhuan(graph);
  }

  private boolean checkWuhuan(Map<Integer, Set<Integer>> graph) {
    VisitStatus[] vis = new VisitStatus[graph.size()];
    for (Integer u : graph.keySet()) {
      vis[u] = VisitStatus.INIT;
    }
    for (Integer u : graph.keySet()) {
      if (!checkWuhuan(graph, vis, u)) {
        return false;
      }
    }
    return true;
  }

  // 后序dfs
  private boolean checkWuhuan(Map<Integer, Set<Integer>> graph,
                              VisitStatus[] vis,
                              int u) {
    if (vis[u] == VisitStatus.VISITING) {
      return false;
    }
    if (vis[u] == VisitStatus.VISITED) {
      return true;
    }
    vis[u] = VisitStatus.VISITING;
    for (Integer v : graph.get(u)) {
      graph.get(v).remove(u);
      if (!checkWuhuan(graph, vis, v)) {
        return false;
      }
    }
    vis[u] = VisitStatus.VISITED;
    return true;
  }

  private Map<Integer, Set<Integer>> constructGraph(int nodeCnt,
                                                    int[][] edges) {
    Map<Integer, Set<Integer>> graph = new HashMap<>(nodeCnt);
    for (int u = 0; u < nodeCnt; u++) {
      graph.put(u, new HashSet<Integer>());
    }
    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0];
      int v = edges[i][1];
      graph.get(u).add(v);
      graph.get(v).add(u);
    }
    return graph;
  }
}

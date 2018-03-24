package com.msh.solutions._310_Minimum_Height_Trees;

import java.util.*;

/**
 * Created by monkeysayhi on 2018/3/24.
 */
public class Solution {
  // solution 1: 暴力，每个点作为根，BFS求树深。O(n^2)
  // solution 2: 看题解，从叶子节点开始删除，O(n)，https://leetcode.com/problems/minimum-height-trees/discuss/76129/Share-my-BFS-JAVA-code-using-degree-with-explanation-which-beats-more-than-95
  // 最后最多只有2个根节点的选择
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (n == 0) {
      return new ArrayList<>();
    }
    if (n == 1) {
      return Arrays.asList(0);
    }
    if (n == 2) {
      return Arrays.asList(0, 1);
    }
    Set<Integer>[] graph = constructGraph(n, edges);
    int[] dgs = new int[n];
    for (int[] edge : edges) {
      dgs[edge[0]]++;
      dgs[edge[1]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int u = 0; u < n; u++) {
      if (dgs[u] == 1) {
        queue.offer(u);
      }
    }
    int leftNodesCnt = n;
    while (!queue.isEmpty()) {
      if (leftNodesCnt <= 2) {
        break;
      }
      int curLevelSize = queue.size();
      for (int i = 0; i < curLevelSize; i++) {
        int u = queue.poll();
        assert graph[u].size() == 1;
        for (int v : graph[u]) {
          graph[u].remove(v);
          graph[v].remove(u);
          dgs[u]--;
          dgs[v]--;
          if (dgs[v] == 1) {
            queue.offer(v);
          }
        }
      }
      leftNodesCnt -= curLevelSize;
    }
    return new ArrayList<>(queue);
  }

  private Set<Integer>[] constructGraph(int nodesCnt, int[][] edges) {
    Set<Integer>[] graph = new Set[nodesCnt];
    for (int u = 0; u < nodesCnt; u++) {
      graph[u] = new HashSet<Integer>();
    }
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      graph[u].add(v);
      graph[v].add(u);
    }
    return graph;
  }
}
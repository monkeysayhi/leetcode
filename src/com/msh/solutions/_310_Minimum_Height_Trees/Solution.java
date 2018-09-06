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
    if (n <= 2) {
      return n == 1 ? Arrays.asList(0) : Arrays.asList(0, 1);
    }

    Set<Integer>[] graph = constructGraph(n, edges);

    Queue<Integer> leaves = new LinkedList<>();
    for (int u = 0; u < n; u++) {
      if (graph[u].size() == 1) {
        leaves.offer(u);
      }
    }

    int leftCnt = n;
    while (leftCnt > 2) {
      int curLevelCnt = leaves.size();
      for (int i = 0; i < curLevelCnt; i++) {
        int u = leaves.poll();
        leftCnt--;
        assert graph[u].size() == 1;
        int v = new ArrayList<>(graph[u]).get(0);
        graph[u].remove(v);
        graph[v].remove(u);
        if (graph[v].size() == 1) {
          leaves.offer(v);
        }
      }
    }
    return new ArrayList<>(leaves);
  }

  private Set<Integer>[] constructGraph(int n, int[][] edges) {
    Set<Integer>[] graph = new Set[n];
    for (int u = 0; u < n; u++) {
      graph[u] = new HashSet<>();
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
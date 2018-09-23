package com.msh.solutions._787_Cheapest_Flights_Within_K_Stops;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by monkeysayhi on 2018/9/23.
 */
public class Solution {
  private static final int INF = Integer.MAX_VALUE;
  // dij最短路 + 动规
  // 设 dp[v][s] 为“节点 v 步数 s 的最小成本”，则 dp[v][s] = min{dp[u][s - 1] + w[u][v]}
  // 状态数为 O(n * k)，决策时间O(n)，总时间复杂度 O(k * n^2)
  // 刷表法
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    if (flights == null || flights.length == 0) {
      return -1;
    }
    if (K == 0) {
      return 0;
    }

    int[][] g = new int[n][n];
    for (int u = 0; u < n; u++) {
      for (int v = 0; v < n; v++) {
        g[u][v] = INF;
      }
    }
    for (int[] e : flights) {
      g[e[0]][e[1]] = e[2];
    }
    int[][] dp = new int[n][K + 1];
    for (int u = 0; u < n; u++) {
      dp[u][0] = 0;
      for (int s = 1; s <= K; s++) {
        dp[u][s] = INF;
      }
    }

    Queue<Integer> q = new LinkedList<>();
    q.offer(src);
    for (int s = 1; s <= K; s++) {
      Set<Integer> vis = new HashSet<>();
      int curLevelSize = q.size();
      for (int i = 0; i < curLevelSize; i++) {
        int u = q.poll();
        for (int v = 0; v < n; v++) {
          if (g[u][v] != INF) {
            if (dp[v][s] == INF || dp[v][s] > dp[u][s - 1] + g[u][v]) {
              dp[v][s] = dp[u][s - 1] + g[u][v];
            }
            if (!vis.contains(v)) {
              vis.add(v);
              q.offer(v);
            }
          }
        }
      }
    }

    int minCost = INF;
    for (int s = 1; s <= K; s++) {
      if (dp[dst][s] != INF && (minCost == INF || minCost > dp[dst][s])) {
        minCost = dp[dst][s];
      }
    }
    return minCost == INF ? -1 : minCost;
  }
}
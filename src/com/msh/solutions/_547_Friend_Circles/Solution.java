package com.msh.solutions._547_Friend_Circles;

/**
 * Created by monkeysayhi on 2018/1/26.
 */
public class Solution {
  // 题目未描述清楚，实际上求的是最大环的个数（内部的小环不计数），即连通图的个数；经常不写邻接矩阵上的搜索，非常手生
  public int findCircleNum(int[][] M) {
    int[][] grid = M;
    if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
      return 0;
    }
    assert grid.length == grid[0].length;

    boolean[] vis = new boolean[grid.length];
    int connectedGraphCnt = 0;
    for (int i = 0; i < grid.length; i++) {
      if (!vis[i]) {
        dfs(grid, vis, i);
        connectedGraphCnt++;
      }
    }
    return connectedGraphCnt;
  }

  private void dfs(int[][] grid, boolean[] vis, int x) {
    vis[x] = true;
    for (int y = 0; y < grid[0].length; y++) {
      if (grid[x][y] == 1 && !vis[y]) {
        dfs(grid, vis, y);
      }
    }
  }
}
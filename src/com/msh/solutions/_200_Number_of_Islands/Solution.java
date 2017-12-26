package com.msh.solutions._200_Number_of_Islands;

/**
 * Created by monkeysayhi on 2017/12/26.
 */
public class Solution {
  // dfs 数连通块
  private final static int[][] DIRECTIONS = new int[][]{
      {0, -1}, {0, 1}, {1, 0}, {-1, 0}
  };

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0
        || grid[0] == null || grid[0].length == 0) {
      return 0;
    }

    int cnt = 0;
    boolean[][] vis = new boolean[grid.length][grid[0].length];
    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[0].length; y++) {
        if (grid[x][y] != '1' || vis[x][y]) {
          continue;
        }
        cnt++;
        dfs(grid, vis, x, y);
      }
    }
    return cnt;
  }

  private void dfs(char[][] grid, boolean[][] vis, int x, int y) {
    vis[x][y] = true;
    for (int[] drt : DIRECTIONS) {
      int nextX = x + drt[0];
      int nextY = y + drt[1];
      if (nextX >= 0 && nextX <= grid.length - 1
          && nextY >= 0 && nextY <= grid[0].length - 1) {
        if (grid[nextX][nextY] != '1' || vis[nextX][nextY]) {
          continue;
        }
        dfs(grid, vis, nextX, nextY);
      }
    }
  }
}

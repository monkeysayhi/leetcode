package com.msh.solutions._130_Surrounded_Regions;

/**
 * Created by monkeysayhi on 2018/1/30.
 */
public class Solution {
  // 从边界上的O开始，dfs标记可达的O，最后扫描一遍将未标记的元素置X
  public void solve(char[][] board) {
    char[][] grid = board;
    if (grid == null || grid.length == 0
        || grid[0] == null || grid[0].length == 0) {
      return;
    }

    boolean[][] vis = new boolean[grid.length][grid[0].length];

    for (int j = 0; j < grid[0].length; j++) {
      if (grid[0][j] == 'O' && !vis[0][j]) {
        mark(grid, 0, j, vis);
      }
    }
    for (int i = 0; i < grid.length; i++) {
      if (grid[i][grid[0].length - 1] == 'O' && !vis[i][grid[0].length - 1]) {
        mark(grid, i, grid[0].length - 1, vis);
      }
    }
    for (int j = 0; j < grid[0].length; j++) {
      if (grid[grid.length - 1][j] == 'O' && !vis[grid.length - 1][j]) {
        mark(grid, grid.length - 1, j, vis);
      }
    }
    for (int i = 0; i < grid.length; i++) {
      if (grid[i][0] == 'O' && !vis[i][0]) {
        mark(grid, i, 0, vis);
      }
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (!vis[i][j]) {
          grid[i][j] = 'X';
        }
      }
    }
  }

  private void mark(char[][] grid, int startX, int startY,
                    boolean[][] vis) {
    vis[startX][startY] = true;
    int[][] DIRECS = new int[][]{
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    for (int[] direc : DIRECS) {
      int x = startX + direc[0];
      int y = startY + direc[1];
      if (x >= 0 && x < grid.length
          && y >= 0 && y < grid[0].length
          && grid[x][y] == 'O' && !vis[x][y]) {
        mark(grid, x, y, vis);
      }
    }
  }
}
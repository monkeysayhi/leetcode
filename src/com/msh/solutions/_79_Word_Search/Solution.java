package com.msh.solutions._79_Word_Search;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  private static final int[][] DIRECS = new int[][]{
      {0, -1}, {-1, 0}, {0, 1}, {1, 0}
  };

  // 暴力dfs, O(n^4)，由于允许重复试探，非常容易错
  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0
        || board[0] == null || board[0].length == 0
        || word == null || word.length() == 0) {
      return false;
    }

    int rowCnt = board.length;
    int colCnt = board[0].length;
    char[] chars = word.toCharArray();
    for (int i = 0; i < rowCnt; i++) {
      for (int j = 0; j < colCnt; j++) {
        boolean[][] vis = new boolean[rowCnt][colCnt];
        if (dfs(board, chars, vis, i, j, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean dfs(char[][] grid, char[] chars, boolean[][] vis,
                      int startX, int startY, int step) {
    if (step == chars.length - 1) {
      return grid[startX][startY] == chars[step];
    }

    if (grid[startX][startY] != chars[step]) {
      return false;
    }
    // 如果已经使用，则vis = true
    vis[startX][startY] = true;
    for (int[] direc : DIRECS) {
      int x = startX + direc[0];
      int y = startY + direc[1];
      if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length
          || vis[x][y]) {
        continue;
      }
      if (dfs(grid, chars, vis, x, y, step + 1)) {
        return true;
      }
    }
    // 然后发现该方案行不通，则放弃该位，vis = false，以后还可以通过其他步骤走到这里
    vis[startX][startY] = false;
    return false;
  }
}
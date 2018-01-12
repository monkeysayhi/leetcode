package com.msh.solutions._64_Minimum_Path_Sum;

/**
 * Created by monkeysayhi on 2018/1/12.
 */
public class Solution {
  // dp[i][j]为以(i, j)为起点的矩阵的最小路径和，dp[i][j] = min{dp[i + 1][j], dp[i][j + 1]} + tbl[i][j]
  public int minPathSum(int[][] grid) {
    assert grid != null && grid.length >= 1;
    assert grid[0] != null && grid[0].length >= 1;

    int rowCnt = grid.length;
    int colCnt = grid[0].length;
    int[][] dp = new int[rowCnt][colCnt];
    for (int i = rowCnt - 1; i >= 0; i--) {
      for (int j = colCnt - 1; j >= 0; j--) {
        if (i == rowCnt - 1 && j == colCnt - 1) {
          dp[i][j] = grid[i][j];
        } else if (i == rowCnt - 1) {
          dp[i][j] = dp[i][j + 1] + grid[i][j];
        } else if (j == colCnt - 1) {
          dp[i][j] = dp[i + 1][j] + grid[i][j];
        } else {
          dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
        }
      }
    }
    return dp[0][0];
  }
}
package com.msh.solutions._63_Unique_Paths_2;

/**
 * Created by monkeysayhi on 2018/1/12.
 */
public class Solution {
  // dp[i][j]为以(i, j)为起点的矩阵的方案数，dp[i][j] = dp[i + 1][j] + dp[i][j + 1]
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int[][] vis = obstacleGrid;
    if (vis == null || vis.length == 0
        || vis[0] == null || vis[0].length == 0) {
      return 0;
    }
    if (vis[0][0] == 1) {
      return 0;
    }

    int rowCnt = vis.length;
    int colCnt = vis[0].length;
    int[][] dp = new int[rowCnt][colCnt];
    for (int i = rowCnt - 1; i >= 0; i--) {
      for (int j = colCnt - 1; j >= 0; j--) {
        if (vis[i][j] == 1) {
          continue;
        }
        if (i == rowCnt - 1 && j == colCnt - 1) {
          dp[i][j] = 1;
        } else if (i == rowCnt - 1) {
          if (vis[i][j + 1] == 0) {
            dp[i][j] = dp[i][j + 1];
          }
        } else if (j == colCnt - 1) {
          if (vis[i + 1][j] == 0) {
            dp[i][j] = dp[i + 1][j];
          }
        } else {
          dp[i][j] = 0;
          if (vis[i][j + 1] == 0) {
            dp[i][j] += dp[i][j + 1];
          }
          if (vis[i + 1][j] == 0) {
            dp[i][j] += dp[i + 1][j];
          }
        }
      }
    }
    return dp[0][0];
  }
}
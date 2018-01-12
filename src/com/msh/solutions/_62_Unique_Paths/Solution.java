package com.msh.solutions._62_Unique_Paths;

/**
 * Created by monkeysayhi on 2018/1/12.
 */
public class Solution {
  // dp[i][j]为以(i, j)为起点的矩阵的方案数，dp[i][j] = dp[i + 1][j] + dp[i][j + 1]
  public int uniquePaths(int m, int n) {
    if (m <= 0 || n <= 0) {
      return 0;
    }
    if (m == 1) {
      return 1;
    }
    if (n == 1) {
      return 1;
    }

    int[][] dp = new int[m][n];
    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        if (i == m - 1 || j == n - 1) {
          dp[i][j] = 1;
        } else {
          dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
        }
      }
    }
    return dp[0][0];
  }
}
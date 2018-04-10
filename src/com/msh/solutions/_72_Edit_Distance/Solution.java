package com.msh.solutions._72_Edit_Distance;

/**
 * Created by monkeysayhi on 2018/4/10.
 */
public class Solution {
  // 最优化，求最少，设 dp[i][j] 为“将s[...i]转换为p[...j]的最少步数”，则 dp[i][j] = min{insert: dp[i][j - 1] + 1, delete: dp[i - 1][j] + 1, replace: s[i] != p[j], dp[i - 1][j - 1] + 1, none: s[i] == p[j], dp[i - 1][j - 1]}
  // 边界，dp[0][0] = 0, dp[i][0] = i, dp[0][j] = j
  // 状态数o(n^2)，决策时间O(1)，总时间O(n^2)
  public int minDistance(String word1, String word2) {
    if (word1 == null || word2 == null) {
      return -1;
    }
    char[] s = word1.toCharArray();
    char[] p = word2.toCharArray();
    int m = s.length;
    int n = p.length;

    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 && j == 0) {
          dp[0][0] = 0;
        } else if (i == 0) {
          dp[0][j] = j;
        } else if (j == 0) {
          dp[i][0] = i;
        } else {
          dp[i][j] = Integer.MAX_VALUE;
          dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
          dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
          if (s[i - 1] != p[j - 1]) {
            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
          } else { // s[i - 1] == p[j - 1]
            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
          }
        }
      }
    }
    return dp[m][n];
  }
}
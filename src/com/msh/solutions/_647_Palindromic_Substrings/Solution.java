package com.msh.solutions._647_Palindromic_Substrings;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  // 最优化，求总数，设dp[i][j]为区间[i, j]是否是回文串
  // dp[i][j] = dp[i + 1][j - 1] && chars[i] == chars[j]
  // 最后统计有多少个true的区间
  public int countSubstrings(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;
    boolean[][] dp = new boolean[n][n];
    int cnt = 0;
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        if (i == j) {
          dp[i][j] = true;
        } else if (i + 1 == j) {
          dp[i][j] = chars[i] == chars[j];
        } else {
          dp[i][j] = dp[i + 1][j - 1] && chars[i] == chars[j];
        }
        cnt += dp[i][j] ? 1 : 0;
      }
    }
    return cnt;
  }
}
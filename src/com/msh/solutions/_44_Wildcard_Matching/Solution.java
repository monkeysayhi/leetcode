package com.msh.solutions._44_Wildcard_Matching;

/**
 * Created by monkeysayhi on 2018/4/5.
 */
public class Solution {
  // 最优化，求能否，设 dp[i][j] 为 “s[...i]与p[...j]是否匹配”
  // 则，dp[i][j] =
  // 1. if p[j] == '?': dp[i - 1][j - 1]
  // 2. elif p[j] == '*': or{*匹配空: dp[i][j - 1], *匹配单字符: dp[i - 1][j - 1], *匹配多字符: dp[i - 1][j]}
  // 3. else: s[i] == p[j] && dp[i - 1][j - 1]
  // 边界为 dp[0][0] == true, dp[0][*] = true
  // 答案为 dp[m][n]
  // 状态数为O(n^2)，决策时间O(1)，总时间O(n^2)
  public boolean isMatch(String s, String p) {
    if (s == null || p == null) {
      return false;
    }
    char[] org = s.toCharArray();
    int m = org.length;
    char[] reg = p.toCharArray();
    int n = reg.length;

    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    if (n >= 1 && reg[1 - 1] == '*') {
      dp[0][1] = true;
      for (int j = 2; j <= n; j++) {
        if (reg[j - 1] != reg[j - 1 - 1]) {
          break;
        }
        dp[0][j] = true;
      }
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (reg[j - 1] == '?') {
          dp[i][j] = dp[i - 1][j - 1];
        } else if (reg[j - 1] == '*') {
          dp[i][j] = dp[i][j - 1] || dp[i - 1][j - 1] || dp[i - 1][j];
        } else {
          dp[i][j] = org[i - 1] == reg[j - 1] && dp[i - 1][j - 1];
        }
      }
    }
    return dp[m][n];
  }
}
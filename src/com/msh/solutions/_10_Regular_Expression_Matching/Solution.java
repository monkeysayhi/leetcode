package com.msh.solutions._10_Regular_Expression_Matching;

/**
 * Created by monkeysayhi on 2018/4/4.
 */
public class Solution {
  // 最优化，求能否，dp[i][j] 为 “s[...i]与p[...j]能否匹配”
  // 则 dp[i][j] =
  // 1. if p[j] == '.' : dp[i - 1][j - 1]
  // 2. elif p[j] == '*' && p[j - 1] == '.' : or{.*匹配空字符串: dp[i][j - 2], .*匹配单个字符: dp[i - 1][j - 2], .*匹配多个字符: dp[i - 1][j]}
  // 3. elif p[j] == '*' : or{a*匹配空字符串: dp[i][j - 2], a*匹配单个a: s[i] == p[j - 1] && dp[i - 1][j - 2], a*匹配多个a: s[i] == p[j - 1] && dp[i - 1][j]}
  // 3. else : s[i] == p[j] && dp[i - 1][j - 1]
  // 边界为 dp[0][j]部分情况下为true,dp[i > 1][0] = false
  // 答案 dp[s.len][p.len]
  // 状态数O(n^2)，决策时间O(1)，则总时间O(n^2)
  public boolean isMatch(String s, String p) {
    // assume valid
    if (s == null || p == null) {
      return false;
    }

    char[] org = s.toCharArray();
    char[] reg = p.toCharArray();
    int m = org.length;
    int n = reg.length;
    boolean[][] dp = new boolean[m + 1][n + 1];

    // edge
    dp[0][0] = true;
    if (n >= 2 && reg[1] == '*') {
      assert reg[0] != '*';
      dp[0][2] = true;
      for (int j = 4; j <= n; j += 2) {
        if (reg[j - 1] != '*') {
          break;
        }
        assert reg[j - 2] != '*';
        dp[0][j] = true;
      }
    }

    // dp
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (reg[j - 1] == '*') {
          assert j >= 2;
          assert reg[j - 2] != '*';
          if (reg[j - 2] == '.') {
            dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j];
          } else {
            dp[i][j] = dp[i][j - 2]
                || (org[i - 1] == reg[j - 2] && dp[i - 1][j - 2])
                || (org[i - 1] == reg[j - 2] && dp[i - 1][j]);
          }
        } else if (reg[j - 1] == '.') {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = org[i - 1] == reg[j - 1] && dp[i - 1][j - 1];
        }
      }
    }

    // ans
    return dp[m][n];
  }
}
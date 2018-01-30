package com.msh.solutions._91_Decode_Ways;

/**
 * Created by monkeysayhi on 2018/1/30.
 */
public class Solution {
  // 暴力，(n/2) * 全排列数量 = O(n*2^n)
  // 最优化，求总数，dp[i]为截止到位置i的方案总数，dp[i] = dp[i - 1] && valid(chars[:-1]) + dp[i - 2] && valid(chars[:-2])
  public int numDecodings(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;
    if (n == 1) {
      return valid(chars, 0, 1) ? 1 : 0;
    }
    if (n == 2) {
      int cnt = 0;
      cnt += valid(chars, 0, 1) && valid(chars, 1, 1) ? 1 : 0;
      cnt += valid(chars, 0, 2) ? 1 : 0;
      return cnt;
    }

    int[] dp = new int[n];
    dp[0] = valid(chars, 0, 1) ? 1 : 0;
    dp[1] += valid(chars, 0, 1) && valid(chars, 1, 1) ? 1 : 0;
    dp[1] += valid(chars, 0, 2) ? 1 : 0;
    for (int i = 2; i < n; i++) {
      dp[i] += valid(chars, i, 1) ? dp[i - 1] : 0;
      dp[i] += valid(chars, i - 1, 2) ? dp[i - 2] : 0;
    }
    return dp[n - 1];
  }

  private boolean valid(char[] chars, int offset, int len) {
    if (len == 1) {
      return chars[offset] >= '1' && chars[offset] <= '9';
    }
    if (len == 2) {
      return (chars[offset] == '1' && chars[offset + 1] >= '0' && chars[offset + 1] <= '9')
          || (chars[offset] == '2' && chars[offset + 1] >= '0' && chars[offset + 1] <= '6');
    }
    return false;
  }
}
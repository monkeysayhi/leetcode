package com.msh.solutions._5_Longest_Palindromic_Substring;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  // 最优化最长子串，dp[i][j]表示子串[i,j]是否是回文串，dp[i][j] = dp[i + 1][j - 1] && chars[i] == chars[j] | i + 1 <= j - 1
  // 如果暴力，O(n^3) = 10^9，肯定超时
  public String longestPalindrome(String s) {
    if (s == null) {
      return null;
    }
    if (s.length() <= 1) {
      return s;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;
    boolean[][] dp = new boolean[n][n];
    int ansI = 0;
    int ansJ = 0;
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        if (i == j) {
          dp[i][j] = true;
        } else if (i + 1 == j) {
          dp[i][j] = chars[i] == chars[j];
        } else {
          dp[i][j] = dp[i + 1][j - 1] && chars[i] == chars[j];
        }
        // i是倒着更新的，所以这里用>=保证取到i最小的最长子串
        // badcase: "aaaaaa"，倒序更新i，导致创建大量String对象，
        // if (dp[i][j] && j - i + 1 >= longestPld.length()) {
        //     longestPld = new String(chars, i, j - i + 1);
        // }
        if (dp[i][j] && j - i + 1 >= ansJ - ansI + 1) {
          ansI = i;
          ansJ = j;
        }
      }
    }
    return new String(chars, ansI, ansJ - ansI + 1);
  }
}
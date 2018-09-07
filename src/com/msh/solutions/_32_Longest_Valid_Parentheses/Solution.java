package com.msh.solutions._32_Longest_Valid_Parentheses;

/**
 * Created by monkeysayhi on 2018/1/29.
 */
public class Solution {
  // solution 1: 最优化，求最长，设 dp[i] 为 “以位置i结尾的最长括号串的长度”，则 dp[i] =
  // if s[i] == '(': 0
  // else if s[i - 1] == '(': 2 + dp[i - 2]
  // else if s[i - 1] == ')' && dp[i - 1] > 0 && s[i - 1 - dp[i - 1]] == '(': 2 + dp[i - 1] + dp[i - (2 + dp[i - 1])]
  // AC：O(n)，因此，先想时间复杂度低的，，，不行再想高的
  public int longestValidParentheses(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;
    int[] dp = new int[n];
    dp[0] = 0;
    int maxLen = 0;
    for (int i = 1; i < n; i++) {
      if (chars[i] == '(') {
        dp[i] = 0;
      } else if (chars[i - 1] == '(') {
        dp[i] = 2;
        if (i - 2 >= 0 && dp[i - 2] > 0) {
          dp[i] += dp[i - 2];
        }
      } else if (chars[i - 1] == ')'
          && dp[i - 1] > 0 && i - 1 - dp[i - 1] >= 0 && chars[i - 1 - dp[i - 1]] == '(') {
        dp[i] = 2 + dp[i - 1];
        if (i - (2 + dp[i - 1]) >= 0) {
          dp[i] += dp[i - (2 + dp[i - 1])];
        }
      }
      maxLen = Math.max(maxLen, dp[i]);
    }
    return maxLen;
  }

//     // solution -1: 最优化，求最长，dp[i][j]为区间[i, j]是否是有效小括号串
//     // dp[i][j] = or{dp[i + 1][j - 1] && match(chars[i], chars[j]), dp[i][k] && dp[k + 1][j] | i < k < j - 1}
//     // TLE：测试用例有1500长度的字符串，O(n^3)超时。想其他办法。
//     public int longestValidParentheses(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
//         if (s.length() == 1) {
//             return 0;
//         }
//         if (s.length() == 2 && match(s.charAt(0), s.charAt(1))) {
//             return 2;
//         }

//         char[] chars = s.toCharArray();
//         int n = chars.length;
//         boolean[][] dp = new boolean[n][n];
//         int maxLen = 0;
//         for (int i = n - 1; i >= 0; i--) {
//             for (int j = i; j < n; j++) {
//                 if (i == j) {
//                     dp[i][j] = false;
//                 } else if (i + 1 == j) {
//                     dp[i][j] = match(chars[i], chars[j]);
//                 } else {
//                     dp[i][j] = dp[i + 1][j - 1] && match(chars[i], chars[j]);
//                     for (int k = i + 1; k < j - 1; k++) {
//                         if (!dp[i][j]) {
//                             dp[i][j] = dp[i][k] && dp[k + 1][j];
//                         }
//                     }
//                 }
//                 if (dp[i][j]) {
//                     maxLen = Math.max(maxLen, j - i + 1);
//                 }
//             }
//         }
//         return maxLen;
//     }

//     private boolean match(char c1, char c2) {
//         return c1 == '(' && c2 == ')';
//     }
}
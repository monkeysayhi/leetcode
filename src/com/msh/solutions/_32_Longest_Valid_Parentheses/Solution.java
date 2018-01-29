package com.msh.solutions._32_Longest_Valid_Parentheses;

/**
 * Created by monkeysayhi on 2018/1/29.
 */
public class Solution {
  // 最优化，求最长，dp[i]为以位置i结尾的最长子串长度，dp[i] = max{dp[i - 1 - dp[i - 1] - 1](dp[i - 1]), dp[i - 2]()}
  // AC：O(n)，因此，先想时间复杂度低的，，，不行再想高的
  public int longestValidParentheses(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    if (s.length() == 1) {
      return 0;
    }
    if (s.length() == 2 && s.equals("()")) {
      return 2;
    }

    char[] chars = s.toCharArray();
    int n = chars.length;
    int[] dp = new int[n];
    dp[0] = 0;
    dp[1] = chars[0] == '(' && chars[1] == ')' ? 2 : 0;
    int maxLen = Math.max(dp[0], dp[1]);
    for (int i = 2; i < n; i++) {
      dp[i] = 0;
      if (chars[i] == ')') {
        if (chars[i - 1] == ')' && dp[i - 1] > 0
            && i - 1 >= dp[i - 1] && chars[i - 1 - dp[i - 1]] == '(') {
          dp[i] = Math.max(dp[i], dp[i - 1] + 2);
          if (i - 2 >= dp[i - 1] && dp[i - 2 - dp[i - 1]] > 0) {
            dp[i] = Math.max(dp[i], dp[i - 2 - dp[i - 1]] + dp[i - 1] + 2);
          }
        }
        if (chars[i - 1] == '(') {
          dp[i] = Math.max(dp[i], dp[i - 2] + 2);
        }
      }
      maxLen = Math.max(maxLen, dp[i]);
    }
    return maxLen;
  }

//     // 最优化，求最长，dp[i][j]为区间[i, j]是否是有效小括号串
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
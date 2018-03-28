package com.msh.solutions._375_Guess_Number_Higher_or_Lower_2;

/**
 * Created by monkeysayhi on 2018/3/28.
 */
public class Solution {
  // 最优策略在最坏情况下的花销，最优化；不要误以为二分法肯定是最优的
  // 但是自己没想出来，看了[题解](https://leetcode.com/problems/guess-number-higher-or-lower-ii/discuss/84787/Java-DP-solution)
  // 设 dp[i][j] 为"目标值在范围[i, j]中，最优策略最坏情况下的花销"
  // 则 dp[i][j] = min{sum{0 | k就是目标值, k + max{dp[i][k - 1], dp[k + 1][j]} | k不是目标值} | i <= k <= j}
  // 外层的 min 是最优策略，中间的 sum 是两种决策，内层的 max 是最坏情况
  // 边界: dp[i][i] = 0
  // 答案: dp[1][n]
  // 状态数O(n^2)，决策时间O(n)，则总时间O(n^3)
  public int getMoneyAmount(int n) {
    if (n == 1) {
      return 0;
    }
    int[][] dp = new int[n + 1][n + 1];
    for (int i = n; i >= 1; i--) {
      for (int j = i; j <= n; j++) {
        if (i == j) {
          dp[i][j] = 0;
          continue;
        }
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
          if (k == i) {
            dp[i][j] = Math.min(dp[i][j], 0 + (k + dp[k + 1][j]));
            continue;
          }
          if (k == j) {
            dp[i][j] = Math.min(dp[i][j], 0 + (k + dp[i][k - 1]));
            continue;
          }
          dp[i][j] = Math.min(dp[i][j], 0 + (k + Math.max(dp[i][k - 1], dp[k + 1][j])));
        }
      }
    }
    return dp[1][n];
  }
}
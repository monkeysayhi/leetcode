package com.msh.solutions._375_Guess_Number_Higher_or_Lower_2;

/**
 * Created by monkeysayhi on 2018/3/28.
 */
public class Solution {
  // 保证赢 = 在最优策略、最坏情况下的花费
  // 最优化，求最大 —— 即最优策略下的最大花费（出现在最坏情况）；不要误以为二分法肯定是最优的
  // 完全没思路，看了[题解](https://leetcode.com/problems/guess-number-higher-or-lower-ii/discuss/84787/Java-DP-solution)
  // 设 dp[i][j] 为 “目标值在范围[i, j]内，最优策略在最坏情况下的花费”
  // 猜中的花费是0，没猜中才需要“利用最优策略最小化最大花费”，所以枚举没猜中的情况，外层min内层max
  // 则 dp[i][j] = min{k + max{dp[i][k - 1], dp[k + 1][j] | i <= k <= j}
  // 边界 dp[i][i] = 0
  // 答案 dp[1][n]
  // 状态数O(n^2)，决策时间O(n)，则总时间O(n^3)
  public int getMoneyAmount(int n) {
    if (n <= 1) {
      return 0;
    }

    int[][] dp = new int[n + 1][n + 1];
    for (int i = n; i >= 1; i--) {
      for (int j = i + 1; j <= n; j++) {
        dp[i][j] = Integer.MAX_VALUE;
        dp[i][j] = Math.min(dp[i][j], i + dp[i + 1][j]);
        for (int k = i + 1; k <= j - 1; k++) {
          dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k - 1], dp[k + 1][j]));
        }
        dp[i][j] = Math.min(dp[i][j], j + dp[i][j - 1]);
      }
    }
    return dp[1][n];
  }

//     // 错误示范：
//     // 则 dp[i][j] = max{0 | k猜中, k + min{dp[i][k - 1], dp[k + 1][j]} | k未猜中}
//     public int getMoneyAmount(int n) {
//         if (n <= 1) {
//             return 0;
//         }

//         int[][] dp = new int[n + 1][n + 1];
//         for (int i = n; i >= 1; i--) {
//             for (int j = i + 1; j <= n; j++) {
//                 dp[i][j] = Math.max(dp[i][j], i + dp[i + 1][j]);
//                 for (int k = i + 1; k <= j - 1; k++) {
//                     dp[i][j] = Math.max(dp[i][j], k + Math.min(dp[i][k - 1], dp[k + 1][j]));
//                 }
//                 dp[i][j] = Math.max(dp[i][j], j + dp[i][j - 1]);
//             }
//         }
//         return dp[1][n];
//     }
}
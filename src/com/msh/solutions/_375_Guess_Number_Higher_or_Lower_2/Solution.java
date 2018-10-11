package com.msh.solutions._375_Guess_Number_Higher_or_Lower_2;

/**
 * Created by monkeysayhi on 2018/3/28.
 */
public class Solution {
  // “必胜策略下最坏情况的花费”，而不是“最坏情况下必胜策略的花费”；不要误以为二分法肯定是最优的
  // 因为问题本身就是必胜的，所以只需要保证“最坏情况”，也就是“当前步猜错之后，继续猜的最小成本”
  // 设 dp[i][j] 为“答案在区间 [i...j] 中，最坏情况的最小成本”
  // 则 dp[i][j] = min{k + max{dp[i][k - 1], dp[k + 1][j]}}
  // 边界 dp[i][i] = 0
  // 答案 dp[1][n]
  // 状态数O(n^2)，决策时间O(n)，则总时间O(n^3)
  public int getMoneyAmount(int n) {
    if (n == 1) {
      return 0;
    }
    if (n == 2) {
      return 1;
    }
    int[][] dp = new int[n + 1][n + 1];
    for (int i = n; i >= 1; i--) {
      dp[i][i] = 0;
      for (int j = i + 1; j <= n; j++) {
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k + 1 <= j; k++) {
          dp[i][j] = Math.min(
              dp[i][j],
              k + Math.max(dp[i][k - 1], dp[k + 1][j])
          );
        }
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
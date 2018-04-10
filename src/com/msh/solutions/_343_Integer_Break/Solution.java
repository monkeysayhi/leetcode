package com.msh.solutions._343_Integer_Break;

/**
 * Created by monkeysayhi on 2018/4/10.
 */
public class Solution {
  // solution 2: 题解有数学方法，易证“任意大于4的数，其拆分积最大化方案中，至多有一个拆分因子最大为4，其他拆分因子均为3”. O(1)
  public int integerBreak(int n) {
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 1;
    }
    if (n == 3) {
      return 2;
    }
    if (n == 4) {
      return 4;
    }
    int left = n % 3;
    int times = n / 3;
    if (left == 0) {
      return (int) (Math.pow(3, times) + 1e-8);
    }
    if (left == 1) {
      return (int) (Math.pow(3, times - 1) + 1e-8) * 4;
    }
    return (int) (Math.pow(3, times) + 1e-8) * 2;
  }

  // // solution 1: 最优化，求最大，设 dp[i] 为“正数i能得到的最大积”，则 dp[i] = max{k, dp[k]} * max{i - k, dp[i - k]} | 1 <= k < i. O(n^2)
  // public int integerBreak(int n) {
  //     int[] dp = new int[n + 1];
  //     dp[1] = 1;
  //     for (int i = 2; i <= n; i++) {
  //         for (int k = 1; k < i; k++) {
  //             dp[i] = Math.max(dp[i],
  //                 Math.max(k, dp[k]) * Math.max(i - k, dp[i - k]));
  //         }
  //     }
  //     return dp[n];
  // }
}
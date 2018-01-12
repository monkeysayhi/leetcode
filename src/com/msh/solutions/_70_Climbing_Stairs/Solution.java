package com.msh.solutions._70_Climbing_Stairs;

/**
 * Created by monkeysayhi on 2018/1/12.
 */
public class Solution {
  // dp[i]为到达第i层阶梯的方案数，dp[i] = dp[i - 1] + dp[i - 2]
  public int climbStairs(int n) {
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }

    int[] dp = new int[1 + n];
    dp[1] = 1;
    dp[2] = 2;
    for (int i = 3; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }
}
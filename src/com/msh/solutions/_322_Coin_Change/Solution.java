package com.msh.solutions._322_Coin_Change;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  // 最优化，求最小，dp[i]为目标账户等于i时的最少硬币数，dp[i] = min{dp[i - nums[j]] + 1 | dp[i - nums[j]]可达}
  public int coinChange(int[] coins, int amount) {
    int[] nums = coins;
    int sum = amount;
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int[] dp = new int[1 + sum];
    dp[0] = 0;
    for (int i = 1; i <= sum; i++) {
      dp[i] = Integer.MAX_VALUE;
      for (int num : nums) {
        if (i >= num && dp[i - num] != Integer.MAX_VALUE) {
          dp[i] = Math.min(dp[i], dp[i - num] + 1);
        }
      }
    }
    return dp[sum] == Integer.MAX_VALUE ? -1 : dp[sum];
  }
}
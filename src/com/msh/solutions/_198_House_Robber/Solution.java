package com.msh.solutions._198_House_Robber;

/**
 * Created by monkeysayhi on 2018/1/26.
 */
public class Solution {
  // 很容易陷入求sum{nums[i], sum[i + 1]}的陷阱。。。
  // 最优化，求最大，dp[i]为截止到抢劫第i家后能抢到的最大钱数，dp[i] = max{dp[i - j] + nums[i] | 2 <= j <= i}
  public int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    if (nums.length == 2) {
      return Math.max(nums[0], nums[1]);
    }

    int n = nums.length;
    int[] dp = new int[1 + n];
    dp[0] = 0; // 之前一家都没抢
    dp[1] = nums[0];
    dp[2] = Math.max(nums[0], nums[1]);
    int max = Math.max(dp[1], dp[2]);
    for (int i = 3; i <= n; i++) {
      for (int j = 2; j <= i; j++) {
        dp[i] = Math.max(dp[i], dp[i - j] + nums[i - 1]);
      }
      max = Math.max(max, dp[i]);
    }
    return max;

  }
}
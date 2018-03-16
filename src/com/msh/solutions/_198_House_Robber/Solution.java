package com.msh.solutions._198_House_Robber;

/**
 * Created by monkeysayhi on 2018/1/26.
 */
public class Solution {
  // 很容易陷入求sum{nums[i], sum[i + 1]}的陷阱。。。
  // 最优化，求最大，dp[i]为顺序抢劫第i家后能抢到的最大钱数，dp[i] = nums[i] + max{dp[i - 2], dp[i - 3]}
  // 或者设rob[i]为顺序抢劫第i家后能抢到的最大钱数，nrob[i]为顺序不抢劫第i家后能抢到的最大钱数
  // 则 rob[i] = nrob[i - 1] + nums[i], nrob[i] = max{nrob[i - 1], rob[i - 1]}
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
    int[] dp = new int[n];
    dp[0] = nums[0];
    dp[1] = nums[1];
    dp[2] = nums[0] + nums[2];
    int max = Math.max(dp[1], dp[2]);
    for (int i = 3; i < n; i++) {
      dp[i] = nums[i] + Math.max(dp[i - 2], dp[i - 3]);
      max = Math.max(max, dp[i]);
    }
    return max;

  }
}
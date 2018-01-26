package com.msh.solutions._213_House_Robber_2;

/**
 * Created by monkeysayhi on 2018/1/26.
 */
public class Solution {
  // 最优化，求最大，dp[i]为截止到抢劫第i家后能抢到的最大钱数，dp[i] = max{dp[i - j] + nums[i] | 2 <= j <= i}；
  // 扫一遍nums[1...n-1]，一遍nums[2...n]，取最大值。这样既包括了所有情况，时间复杂度也没有增加
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
    if (nums.length == 3) {
      return Math.max(nums[0], Math.max(nums[1], nums[2]));
    }

    int n = nums.length;
    return Math.max(robInLine(nums, 0, n - 1),
        robInLine(nums, 1, n - 1));
  }

  private int robInLine(int[] nums, int offset, int len) {
    int[] dp = new int[1 + offset + len];
    dp[offset] = 0;
    dp[offset + 1] = nums[offset];
    dp[offset + 2] = Math.max(nums[offset], nums[offset + 1]);
    int max = Math.max(dp[offset + 1], dp[offset + 2]);
    for (int i = 3; i <= len; i++) {
      for (int j = 2; j <= i; j++) {
        dp[offset + i] = Math.max(dp[offset + i], dp[offset + i - j] + nums[offset + i - 1]);
      }
      max = Math.max(max, dp[offset + i]);
    }
    return max;
  }
}
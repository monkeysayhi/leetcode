package com.msh.solutions._213_House_Robber_2;

/**
 * Created by monkeysayhi on 2018/1/26.
 */
public class Solution {
  // 最优化，求最大，dp[i]为截止到抢劫第i家后能抢到的最大钱数，dp[i] = nums[i] + max{dp[i - 2], dp[i - 3]}
  // 扫一遍nums[0...n-3]，nums[0...n-2]，一遍nums[1...n-1]，取最大值。这样既包括了所有情况，时间复杂度也没有增加
  // 或者设rob[i]为顺序抢劫第i家后能抢到的最大钱数，nrob[i]为顺序不抢劫第i家后能抢到的最大钱数
  // 则 rob[i] = nrob[i - 1] + nums[i], nrob[i] = max{nrob[i - 1], rob[i - 1]}
  // 扫描逻辑相同
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
    int max = robInLine(nums, 0, n - 2);
    max = Math.max(max, robInLine(nums, 0, n - 1));
    max = Math.max(max, robInLine(nums, 1, n - 1));
    return max;
  }

  private int robInLine(int[] nums, int offset, int len) {
    if (len == 1) {
      return nums[offset];
    }
    if (len == 2) {
      return Math.max(nums[offset], nums[offset + 1]);
    }
    int[] dp = new int[len];
    dp[0] = nums[offset];
    dp[1] = nums[offset + 1];
    dp[2] = nums[offset] + nums[offset + 2];
    int max = Math.max(dp[1], dp[2]);
    for (int i = 3; i < len; i++) {
      dp[i] = nums[offset + i] + Math.max(dp[i - 2], dp[i - 3]);
      max = Math.max(max, dp[i]);
    }
    return max;
  }
}
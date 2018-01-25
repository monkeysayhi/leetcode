package com.msh.solutions._416_Partition_Equal_Subset_Sum;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  // 最优化，求能否，dp[i][j]为截止到位置i是否包含一个和等于j的子数组，dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]
  public boolean canPartition(int[] nums) {
    assert nums != null && nums.length > 0;
    if (nums.length == 1) {
      return false;
    }

    int n = nums.length;
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    if (sum % 2 == 1) {
      return false;
    }
    int target = sum / 2;
    int[][] dp = new int[n][1 + target];
    if (nums[0] <= target) {
      dp[0][nums[0]] = 1;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= target; j++) {
        if (dp[i - 1][j] == 1) {
          dp[i][j] = 1;
        }
        if (j > nums[i] && dp[i - 1][j - nums[i]] == 1) {
          dp[i][j] = 1;
        }
      }
      if (dp[i][target] == 1) {
        return true;
      }
    }
    return dp[n - 1][target] == 1;
  }
}
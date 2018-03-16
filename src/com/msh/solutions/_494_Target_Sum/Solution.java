package com.msh.solutions._494_Target_Sum;

/**
 * Created by monkeysayhi on 2018/1/24.
 */
public class Solution {
  // solution 2：最优化求总数，将设dp[i][j]为截止到第i个数，和等于j的方案数，则dp[i][j] = dp[i - 1][j + nums[i]] + dp[i - 1][j - nums[i]]
  // 由于j属于[-sum, sum]，需要映射到[0, sum + sum]
  public int findTargetSumWays(int[] nums, int S) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    // badcase: [1], 2
    if (S < -sum || S > sum) {
      return 0;
    }
    int n = nums.length;
    int[][] dp = new int[n][sum + sum + 1];
    dp[0][sum + nums[0]] += 1;
    dp[0][sum - nums[0]] += 1;
    for (int i = 1; i < n; i++) {
      for (int j = -sum; j <= sum; j++) {
        if (j + nums[i] <= sum) {
          dp[i][sum + j] += dp[i - 1][sum + j + nums[i]];
        }
        if (j - nums[i] >= -sum) {
          dp[i][sum + j] += dp[i - 1][sum + j - nums[i]];
        }
      }
    }
    return dp[n - 1][sum + S];
  }

//     // solution 1: 枚举正负号序列，回溯，O(2^n) = 2^20 = 1 * 10^6
//     public int findTargetSumWays(int[] nums, int S) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
//         return backtrack(nums, S, 0);
//     }

//     private int backtrack(int[] nums, int target, int cur) {
//         if (cur == nums.length) {
//             return target == 0 ? 1 : 0;
//         }
//         return backtrack(nums, target - nums[cur], cur + 1)
//             + backtrack(nums, target + nums[cur], cur + 1);
//     }
}
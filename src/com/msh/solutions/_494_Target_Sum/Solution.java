package com.msh.solutions._494_Target_Sum;

/**
 * Created by monkeysayhi on 2018/1/24.
 */
public class Solution {
  // solution 2：最优化求总数，将设dp[i][j]为截止到第i个数，和等于j的方案数，则
  // dp[i][j] = dp[i - 1][j + nums[i]] + dp[i - 1][j - nums[i]]
  // 由于j属于[-sum, sum]，需要映射到[0, sum + sum]
  // 引入0之后，变得非常讨厌！！！
  public int findTargetSumWays(int[] nums, int S) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // badcase: [0,1], 1
    int[] nonZeroNums = new int[nums.length];
    int nonZeroCnt = 0;
    for (int num : nums) {
      if (num != 0) {
        nonZeroNums[nonZeroCnt++] = num;
      }
    }
    int zeroCnt = nums.length - nonZeroCnt;

    int sum = 0;
    for (int num : nonZeroNums) {
      sum += num;
    }
    // badcase: [1], 2
    if (S < -sum || S > sum) {
      return 0;
    }
    // badcase: [0,0], 0
    if (nonZeroCnt == 0) {
      return S == 0 ? 1 << zeroCnt : 0;
    }
    int[][] dp = new int[nonZeroCnt][sum + sum + 1];
    dp[0][sum + nonZeroNums[0]] = 1;
    dp[0][sum - nonZeroNums[0]] = 1;
    for (int i = 1; i < nonZeroCnt; i++) {
      for (int j = -sum; j <= sum; j++) {
        if (j + nonZeroNums[i] <= sum) {
          dp[i][sum + j] += dp[i - 1][sum + j + nonZeroNums[i]];
        }
        if (j - nonZeroNums[i] >= -sum) {
          dp[i][sum + j] += dp[i - 1][sum + j - nonZeroNums[i]];
        }
      }
    }
    return dp[nonZeroCnt - 1][sum + S] * (1 << zeroCnt);
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
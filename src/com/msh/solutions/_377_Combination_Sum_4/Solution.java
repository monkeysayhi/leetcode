package com.msh.solutions._377_Combination_Sum_4;

/**
 * Created by monkeysayhi on 2018/4/12.
 */
// 这题有问题，(1, 1, 2)和(2, 1, 1)竟然算作两种，，应该被描述为“排列和”
public class Solution {
  // solution 1: 排列，只能枚举，统计数量，total、used统计数量，回溯控制顺序
  // solution 2: 看了题解，非常巧妙。最优化，求总数，设 dp[i] 为“加和为i的方案总数”，则 dp[i] = sum{dp[i - nums[j]] | 0 <= j < n}，nums[j]意为“最后一位放nums[j]”，以此覆盖全部排列方案
  // follow up: 放弃
  public int combinationSum4(int[] nums, int target) {
    assert target > 0;
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int[] dp = new int[target + 1];
    dp[0] = 1; // 仅为简化代码
    for (int i = 1; i <= target; i++) {
      for (int num : nums) {
        if (num <= i) {
          dp[i] += dp[i - num];
        }
      }
    }
    return dp[target];
  }

//     // solution -1: 求最值，总数，设dp[i][S]为“截止到第i个数加和等于S的方案数”，则dp[i][S] = sum{dp[i - 1][S - nums[i] * k] | nums[i] * k <= S && k >= 0}
//     public int combinationSum4(int[] nums, int target) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }

//         int n = nums.length;
//         int[][] dp = new int[n][target + 1];
//         for (int i = 0; i < n; i++) {
//             for (int k = 1; nums[i] * k <= target; k++) {
//                 dp[i][nums[i] * k] = 1;
//             }
//             if (i == 0) {
//                 continue;
//             }
//             for (int j = 1; j <= target; j++) {
//                 for (int k = 0; nums[i] * k <= j; k++) {
//                     dp[i][j] += dp[i - 1][j - nums[i] * k];
//                 }
//             }
//         }
//         return dp[n - 1][target];
//     }
}
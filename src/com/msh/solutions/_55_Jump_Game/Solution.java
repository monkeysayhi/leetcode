package com.msh.solutions._55_Jump_Game;

/**
 * Created by monkeysayhi on 2018/1/12.
 */
public class Solution {
  // AC，贪心，时间O(n): 定义farthest为可以探查的最远位置，则判断“n - 1是否可达”，等价于判断“farthest >= n - 1是否成立”
  public boolean canJump(int[] nums) {
    if (nums == null || nums.length == 0) {
      return false;
    }

    int farthest = nums[0];
    for (int i = 1; i <= farthest && i < nums.length; i++) {
      farthest = Math.max(farthest, nums[i] + i);
    }
    return farthest >= nums.length - 1;
  }

//     // TLE，动规，时间O(n^2): dp[i]为从起点1是否能跳到位置i，dp[i] = or{dp[i - j] && nums[i - j] >= j | 1 <= j <= i - 1}
//     public boolean canJump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return false;
//         }
//         if (nums.length == 1 && nums[0] == 0) {
//             return true;
//         }
//         if (nums[0] == 0) {
//             return false;
//         }

//         int n = nums.length;
//         boolean[] dp = new boolean[n + 1];
//         dp[1] = true;
//         if (!dp[1]) {
//             return false;
//         }
//         for (int i = 2; i <= n; i++) {
//             for (int j = 1; j <= i - 1; j++) {
//                 dp[i] |= dp[i - j] && nums[i - j - 1] >= j;
//             }
//         }
//         return dp[n];
//     }
}
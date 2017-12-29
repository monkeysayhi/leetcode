package com.msh.solutions._1_Two_Sum;

/**
 * Created by monkeysayhi on 2017/12/29.
 */
// solution 1, 暴力。时间O(n^2)，空间O(1)
public class Solution {
  // 暴力
  public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    return null;
  }
}

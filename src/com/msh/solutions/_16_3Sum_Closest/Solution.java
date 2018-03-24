package com.msh.solutions._16_3Sum_Closest;

import java.util.Arrays;

/**
 * Created by monkeysayhi on 2018/3/24.
 */
public class Solution {
  // solution 1: 暴力，枚举`<i, j, k>`。O(n^3)
  // solution 2: 排序，对每个数，求“2Sum Closest”。O(n^2)
  public int threeSumClosest(int[] nums, int target) {
    // assume valid
    if (nums.length == 3) {
      return nums[0] + nums[1] + nums[2];
    }
    Arrays.sort(nums);
    int closest = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length - 2; i++) {
      int curClosest = twoSumClosest(nums, i + 1, nums.length - 1, nums[i], target);
      if (curClosest == target) {
        return target;
      }
      if (closest == Integer.MIN_VALUE
          || Math.abs(curClosest - target) < Math.abs(closest - target)) {
        closest = curClosest;
      }
    }
    return closest;
  }

  private int twoSumClosest(int[] nums, int l, int r, int init, int target) {
    int closest = Integer.MIN_VALUE;
    while (l < r) {
      int sum = init + nums[l] + nums[r];
      if (closest == Integer.MIN_VALUE
          || Math.abs(sum - target) < Math.abs(closest - target)) {
        closest = sum;
      }
      if (sum > target) {
        r--;
      } else if (sum < target) {
        l++;
      } else {
        return target;
      }
    }
    return closest;
  }
}
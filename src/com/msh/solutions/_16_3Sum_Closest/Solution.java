package com.msh.solutions._16_3Sum_Closest;

import java.util.Arrays;

/**
 * Created by monkeysayhi on 2018/3/24.
 */
public class Solution {
  // solution 1: 暴力，枚举<i, j, k>，维护 cloest tuple，O(n^3)
  // solution 2: 排序，枚举 nums[k]，带着nums[k]两头双指针维护 cloest tuple，O(n^2)
  public int threeSumClosest(int[] nums, int target) {
    // assume valid
    if (nums.length == 3) {
      return nums[0] + nums[1] + nums[2];
    }

    Arrays.sort(nums);
    int n = nums.length;
    int cloestSum = nums[0] + nums[1] + nums[2];
    for (int k = 0; k + 2 < n; k++) {
      int sum = twoSumCloest(nums, k + 1, n - 1, k, target);
      if (Math.abs(sum - target) < Math.abs(cloestSum - target)) {
        cloestSum = sum;
      }
    }
    return cloestSum;
  }

  private int twoSumCloest(int[] nums, int i, int j,
                           int k, int target) {
    assert j - i >= 1;
    int cloestSum = nums[k] + nums[i] + nums[j];
    while (i < j) {
      int sum = nums[k] + nums[i] + nums[j];
      if (sum == target) {
        return sum;
      } else if (sum > target) {
        j--;
      } else {
        i++;
      }
      if (Math.abs(sum - target) < Math.abs(cloestSum - target)) {
        cloestSum = sum;
      }
    }
    return cloestSum;
  }
}
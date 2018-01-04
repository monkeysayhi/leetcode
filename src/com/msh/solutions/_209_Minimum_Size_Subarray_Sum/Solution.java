package com.msh.solutions._209_Minimum_Size_Subarray_Sum;

/**
 * Created by monkeysayhi on 2018/1/4.
 */
public class Solution {
  // 子数组 + 单头双指针
  public int minSubArrayLen(int s, int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0] >= s ? 1 : 0;
    }

    int[] sums = new int[nums.length];
    sums[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      sums[i] = sums[i - 1] + nums[i];
    }

    int minLen = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      if (sums[i] >= s) {
        minLen = i + 1;
        break;
      }
    }
    int i = 0;
    int j = 1;
    while (j < sums.length) {
      int subSum = sums[j] - sums[i];
      if (subSum < s) {
        j++;
      } else {
        int len = j - i;
        minLen = Math.min(minLen, j - i);
        i++;
        if (i == j) {
          j++;
        }
      }
    }
    if (minLen == Integer.MAX_VALUE) {
      return 0;
    }
    return minLen;
  }
}
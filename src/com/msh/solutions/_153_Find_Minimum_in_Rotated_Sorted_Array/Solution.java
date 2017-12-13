package com.msh.solutions._153_Find_Minimum_in_Rotated_Sorted_Array;

/**
 * Created by monkeysayhi on 2017/12/13.
 */
public class Solution {
  // 二分，收敛到“小于等于 nums[-1] 的第一个元素”
  // 假设数组是旋转的，则v必须取 nums[-1]，而不能取 nums[0]。取 nums[0] 无法定义收敛规则。
  public int findMin(int[] nums) {
    // no edge
    if (nums[0] < nums[nums.length - 1]) {
      return nums[0];
    }

    int v = nums[nums.length - 1];
    int l = 0;
    int r = nums.length;
    while (l < r) {
      int m = l + (r - l) / 2;
      if (nums[m] <= v) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return nums[l];
  }
}

package com.msh.solutions._162_Find_Peak_Element;

/**
 * Created by monkeysayhi on 2017/12/13.
 */
class Solution {
  // 二分，对于区间 [l, m, r]，朝着 max{m, m+1} 的方向收敛
  public int findPeakElement(int[] nums) {
    // no edge
    int l = 0;
    int r = nums.length;
    while (l < r) {
      int m = l + (r - l) / 2;
      // leetcode 不同于 lintcode 的一处，由条件“num[-1] = num[n] = -∞”，允许输入长度小于3的数组
      if (m == nums.length - 1 || nums[m] >= nums[m + 1]) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return l;
  }
}

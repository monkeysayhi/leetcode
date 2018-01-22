package com.msh.solutions._34_Search_for_a_Range;

/**
 * Created by monkeysayhi on 2018/1/22.
 */
public class Solution {
  // 二分求下界、上界
  public int[] searchRange(int[] nums, int target) {
    int lowerBound = bsearchL(nums, 0, nums.length, target);
    int upperBound = bsearchU(nums, 0, nums.length, target);
    if (lowerBound == upperBound) {
      return new int[]{-1, -1};
    }
    return new int[]{lowerBound, upperBound - 1};
  }

  private int bsearchL(int[] nums, int l, int r, int v) {
    while (l < r) {
      int m = l + (r - l) / 2;
      if (nums[m] >= v) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return r;
  }

  private int bsearchU(int[] nums, int l, int r, int v) {
    while (l < r) {
      int m = l + (r - l) / 2;
      if (nums[m] > v) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return r;
  }
}
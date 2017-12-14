package com.msh.solutions._33_Search_in_Rotated_Sorted_Array;

/**
 * Created by monkeysayhi on 2017/12/14.
 */
public class Solution {
  // 二分
  // 画图分析，nums = [4 5 6 7 0 1 2 3] ：
  // seg 1: 4 5 6 (左侧，递增)
  // seg 2: 4 6 0 (跨越左右，先增后减序列，波峰在左侧)
  // seg 3: 6 0 2 (跨越左右，先减后增序列，波谷在右侧)
  // seg 4: 1 2 3 (右侧，递增)
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      if (nums[0] != target) {
        return -1;
      }
      return 0;
    }

    int l = 0;
    int r = nums.length - 1;
    while (l < r) {
      int m = l + (r - l) / 2;
      if (nums[m] == target) {
        return m;
      }
      if (nums[l] == target) {
        return l;
      }
      if (nums[r] == target) {
        return r;
      }
      if (nums[l] < nums[m] && nums[r] < nums[m]) {
        if (nums[m] < target) {
          l = m + 1;
        } else {
          if (nums[l] > target) {
            l = m + 1;
          } else {
            r = m;
          }
        }
      } else if (nums[l] > nums[m] && nums[r] > nums[m]) {
        if (nums[m] > target) {
          r = m;
        } else {
          if (nums[r] < target) {
            r = m;
          } else {
            l = m + 1;
          }
        }
      } else {
        if (nums[m] > target) {
          r = m;
        } else {
          l = m + 1;
        }
      }
    }
    return -1;
  }
}

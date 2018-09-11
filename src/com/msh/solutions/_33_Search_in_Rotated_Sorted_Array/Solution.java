package com.msh.solutions._33_Search_in_Rotated_Sorted_Array;

/**
 * Created by monkeysayhi on 2017/12/14.
 */
public class Solution {
  // 二分
  // 画图分析，nums = [4 5 6 7 0 1 2 3] ：
  // seg 1: 4 5 6 / 1 2 3 (左侧/右侧，递增)
  // seg 2: 4 6 0 (跨越左右，先增后减序列，m在波峰左侧)
  // seg 3: 6 0 2 (跨越左右，先减后增序列，m在波谷右侧)
  public int search(int[] nums, int target) {
    // assume valid
    if (nums.length == 0) {
      return -1;
    }

    int n = nums.length;
    int l = 0;
    int r = n;
    int v = target;
    int p = nums[n - 1];
    while (l < r) {
      int m = l + (r - l) / 2;
      if (nums[m] == v) {
        return m;
      }
      if (nums[l] <= nums[m] && nums[m] <= nums[r - 1]) {
        if (nums[m] > v) {
          r = m;
        } else {
          l = m + 1;
        }
      } else if (nums[l] <= nums[m] && nums[m] >= nums[r - 1] && nums[l] > p) {
        if (nums[m] > v) {
          if (v > p) {
            r = m;
          } else {
            l = m + 1;
          }
        } else {
          l = m + 1;
        }
      } else if (nums[l] >= nums[m] && nums[m] <= nums[r - 1] && nums[r - 1] <= p) {
        if (nums[m] > v) {
          r = m;
        } else {
          if (v > p) {
            r = m;
          } else {
            l = m + 1;
          }
        }
      }
    }
    return -1;
  }
}

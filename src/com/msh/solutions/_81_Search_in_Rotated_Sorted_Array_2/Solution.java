package com.msh.solutions._81_Search_in_Rotated_Sorted_Array_2;

/**
 * Created by monkeysayhi on 2017/12/14.
 */
public class Solution {
  // 无法二分，只能暴力扫描
  // 结合 33. Search in Rotated Sorted Array 看
  // 如果 nums[l] == nums[m] == nums[r]，此时无法确定继续向哪个分支探查
  // badcase: 11131111 3
  public boolean search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return false;
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == target) {
        return true;
      }
    }
    return false;
  }
}

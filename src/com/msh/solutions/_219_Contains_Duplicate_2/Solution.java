package com.msh.solutions._219_Contains_Duplicate_2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by monkeysayhi on 2018/4/8.
 */
public class Solution {
  // solution 1: 暴力枚举(i, j)，判断相等 nums[i] == nums[j]. O(n * k)
  // solution 2: 排序，扫一遍，判断 |i - j| <= k. O(nlogn)
  // solution 3: Set维护大小为 k + 1 的窗口，扫一遍. O(n)
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    if (nums == null || nums.length < 2) {
      return false;
    }
    if (k <= 0) {
      return false;
    }

    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (set.size() == k + 1) {
        set.remove(nums[i - k - 1]);
      }
      if (set.contains(nums[i])) {
        return true;
      }
      set.add(nums[i]);
    }
    return false;
  }
}
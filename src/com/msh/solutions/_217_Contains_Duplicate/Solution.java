package com.msh.solutions._217_Contains_Duplicate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by monkeysayhi on 2018/4/8.
 */
public class Solution {
  // solution 1: 暴力统计. O(n^2)
  // solution 2: 排序，扫一遍. O(nlogn)
  // solution 3: Set扫一遍. O(n)
  public boolean containsDuplicate(int[] nums) {
    if (nums == null || nums.length < 2) {
      return false;
    }
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (set.contains(num)) {
        return true;
      }
      set.add(num);
    }
    return false;
  }
}
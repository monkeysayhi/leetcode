package com.msh.solutions._80_Remove_Duplicates_from_Sorted_Array_2;

/**
 * Created by monkeysayhi on 2017/12/29.
 */
public class Solution {
  // i、j从左侧开始，不等就cnt=1，nums[++i] = nums[j]；相等就cnt++，如果cnt<=2，也nums[++i] = nums[j]
  public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int i = 0;
    int cnt = 1;
    for (int j = 1; j < nums.length; j++) {
      if (nums[j] == nums[i]) {
        cnt++;
        if (cnt <= 2) {
          i++;
          nums[i] = nums[j];
        }
        continue;
      }
      cnt = 1;
      i++;
      nums[i] = nums[j];
    }
    return i + 1;
  }
}

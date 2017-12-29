package com.msh.solutions._26_Remove_Duplicates_from_Sorted_Array;

/**
 * Created by monkeysayhi on 2017/12/29.
 */
public class Solution {
  // i、j从左侧开始，不等就nums[++i] = nums[j]，最后返回 i+1
  public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int i = 0;
    for (int j = 0; j < nums.length; j++) {
      if (nums[j] == nums[i]) {
        continue;
      }
      i++;
      nums[i] = nums[j];
    }
    return i + 1;
  }
}

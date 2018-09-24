package com.msh.solutions._35_Search_Insert_Position;

/**
 * Created by monkeysayhi on 2018/9/24.
 */
public class Solution {
  // 二分查找求下界
  public int searchInsert(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int l = 0;
    int r = nums.length;
    while (l < r) {
      int m = l + (r - l) / 2;
      if (nums[m] >= target) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return r;
  }
}
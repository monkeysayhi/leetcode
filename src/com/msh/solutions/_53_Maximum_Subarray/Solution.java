package com.msh.solutions._53_Maximum_Subarray;

/**
 * Created by monkeysayhi on 2018/1/2.
 */
public class Solution {
  // 记录截止到每一位的最大值(>=0)，当遇到新的一位时，比较选或不选哪个更优；同时维护curMax
  public int maxSubArray(int[] nums) {
    assert nums != null && nums.length > 0;
    if (nums.length == 1) {
      return nums[0];
    }

    int curMax = nums[0];
    int[] maxs = new int[nums.length];
    maxs[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      maxs[i] = Math.max(maxs[i - 1] + nums[i], nums[i]);
      curMax = Math.max(maxs[i], curMax);
    }
    return curMax;
  }
}

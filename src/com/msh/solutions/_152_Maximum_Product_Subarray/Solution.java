package com.msh.solutions._152_Maximum_Product_Subarray;

/**
 * Created by monkeysayhi on 2018/1/2.
 */
public class Solution {
  private static class Info {
    private int min;
    private int max;

    Info(int min, int max) {
      this.min = min;
      this.max = max;
    }
  }

  // 记录截止到每一位的最大值(>=0)与最小值(<=0)，当遇到新的一位时，比较选或不选哪个更优；同时维护curMax
  public int maxProduct(int[] nums) {
    assert nums != null && nums.length > 0;
    if (nums.length == 1) {
      return nums[0];
    }

    Info[] infos = new Info[nums.length];
    int curMax = nums[0];
    infos[0] = new Info(nums[0], nums[0]);
    for (int i = 1; i < nums.length; i++) {
      infos[i] = new Info(0, 0);
      if (nums[i] < 0) {
        infos[i].min = Math.min(infos[i - 1].max * nums[i], nums[i]);
        infos[i].max = Math.max(infos[i - 1].min * nums[i], nums[i]);
      } else {
        infos[i].min = Math.min(infos[i - 1].min * nums[i], nums[i]);
        infos[i].max = Math.max(infos[i - 1].max * nums[i], nums[i]);
      }
      if (curMax < infos[i].max) {
        curMax = infos[i].max;
      }
    }
    return curMax;
  }
}

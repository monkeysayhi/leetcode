package com.msh.solutions._1_Two_Sum;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by monkeysayhi on 2017/12/29.
 */
// solution 2, 排序，从两头收缩。时间O(nlogn)，空间O(n)
public class FollowUp1 {
  private static class Info {
    private int val;
    private int idx;

    public Info(int val, int idx) {
      this.val = val;
      this.idx = idx;
    }
  }

  // 排序，从两头收缩
  public int[] twoSum(int[] nums, int target) {
    Info[] infos = new Info[nums.length];
    for (int i = 0; i < nums.length; i++) {
      infos[i] = new Info(nums[i], i);
    }
    Arrays.sort(infos, new Comparator<Info>() {
      public int compare(Info info1, Info info2) {
        return info1.val - info2.val;
      }
    });

    int l = 0;
    int r = infos.length - 1;
    while (l < r) {
      if (infos[l].val + infos[r].val > target) {
        r--;
      } else if (infos[l].val + infos[r].val < target) {
        l++;
      } else {
        int[] rs = new int[]{infos[l].idx, infos[r].idx};
        Arrays.sort(rs);
        return rs;
      }
    }
    return null;
  }
}

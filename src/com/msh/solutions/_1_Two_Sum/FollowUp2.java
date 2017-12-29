package com.msh.solutions._1_Two_Sum;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/29.
 */
// solution 3, 统计频数，O(1)查找。时间O(n)，空间O(n)
public class FollowUp2 {
  private static class Info {
    private int cnt = 0;
    private List<Integer> idxs = new ArrayList<>();
  }

  // 统计各值的出现的频数，然后对每个值，查找其对target的补数
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Info> stat = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (!stat.containsKey(nums[i])) {
        stat.put(nums[i], new Info());
      }
      stat.get(nums[i]).cnt++;
      stat.get(nums[i]).idxs.add(i);
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] * 2 == target) {
        Info info = stat.get(nums[i]);
        if (info.cnt < 2) {
          continue;
        }
        int[] rs = new int[]{info.idxs.get(0), info.idxs.get(1)};
        Arrays.sort(rs);
        return rs;
      }
      Info info = stat.get(target - nums[i]);
      if (info == null) {
        continue;
      }
      int[] rs = new int[]{stat.get(nums[i]).idxs.get(0), info.idxs.get(0)};
      Arrays.sort(rs);
      return rs;
    }
    return null;
  }
}

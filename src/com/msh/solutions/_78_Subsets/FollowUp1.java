package com.msh.solutions._78_Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by monkeysayhi on 2017/12/11.
 */
// [AC] 2. 二进制法, 循环写法。
public class FollowUp1 {
  // 直接使用整数的二进制形式代替vis数组
  public List<List<Integer>> subsets(int[] nums) {
    if (nums == null) {
      return null;
    }

    List<List<Integer>> results = new ArrayList<>();
    if (nums.length == 0) {
      results.add(new ArrayList<Integer>());
      return results;
    }

    if (nums.length > 31) {
      throw new UnsupportedOperationException("nums size limit: 31, given: " + nums.length);
    }

    Arrays.sort(nums);
    int resultCnt = 1 << nums.length;
    for (int vis = 0; vis < resultCnt; vis++) {
      List<Integer> tmpRs = new ArrayList<>();
      int tmpVis = vis;
      for (int j = 0; j < nums.length; j++, tmpVis >>= 1) {
        if ((tmpVis & 1) == 1) {
          tmpRs.add(nums[j]);
        }
      }
      results.add(tmpRs);
    }
    return results;
  }
}

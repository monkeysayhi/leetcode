package com.msh.solutions._46_Permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by monkeysayhi on 2017/12/8.
 */
class Solution {
  // 不可重集的排列, 回溯
  public List<List<Integer>> permute(int[] nums) {
    if (nums == null) {
      return null;
    }

    List<List<Integer>> results = new ArrayList<>();

    if (nums.length == 0) {
      results.add(new ArrayList<Integer>());
      return results;
    }

    Arrays.sort(nums);

    boolean[] vis = new boolean[nums.length];
    Integer[] tmpRs = new Integer[nums.length];
    int cur = 0;
    backtrack(nums, vis, cur, tmpRs, results);
    return results;
  }

  private void backtrack(int[] nums, boolean[] vis, int cur,
                         Integer[] tmpRs,
                         List<List<Integer>> results) {
    if (cur == nums.length) {
      results.add(new ArrayList<>(Arrays.asList(tmpRs)));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (vis[i]) {
        continue;
      }

      tmpRs[cur] = nums[i];
      vis[i] = true;
      backtrack(nums, vis, cur + 1, tmpRs, results);
      vis[i] = false;
    }
    return;
  }
}

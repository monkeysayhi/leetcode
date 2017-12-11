package com.msh.solutions._90_Subsets_2;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/11.
 */
class Solution {
  // 可重集的子集，回溯
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    if (nums == null) {
      return null;
    }

    List<List<Integer>> results = new ArrayList<>();
    if (nums.length == 0) {
      results.add(new ArrayList<Integer>());
      return results;
    }

    Arrays.sort(nums);
    Map<Integer, Integer> vis = new HashMap<>();
    Map<Integer, Integer> stat = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (!stat.containsKey(nums[i])) {
        stat.put(nums[i], 0);
        vis.put(nums[i], 0);
      }
      stat.put(nums[i], stat.get(nums[i]) + 1);
    }
    int cur = 0;
    backtrack(nums, stat, vis, cur, results);
    return results;
  }

  private void backtrack(int[] nums,
                         Map<Integer, Integer> stat, Map<Integer, Integer> vis,
                         int cur,
                         List<List<Integer>> results) {
    if (cur == nums.length) {
      List<Integer> tmpRs = new ArrayList<>(nums.length);
      for (int i = 0; i < nums.length; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) {
          continue;
        }
        for (int j = 0; j < vis.get(nums[i]); j++) {
          tmpRs.add(nums[i]);
        }
      }
      results.add(tmpRs);
      return;
    }

    for (int j = 0; j <= stat.get(nums[cur]); j++) {
      vis.put(nums[cur], j);
      backtrack(nums, stat, vis, cur + stat.get(nums[cur]), results);
    }
  }
}
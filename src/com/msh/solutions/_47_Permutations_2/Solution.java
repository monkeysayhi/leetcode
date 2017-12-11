package com.msh.solutions._47_Permutations_2;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/8.
 */
class Solution {
  // 可重集的排列, 回溯
  public List<List<Integer>> permuteUnique(int[] nums) {
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

    Integer[] tmpRs = new Integer[nums.length];
    int cur = 0;
    backtrack(nums, stat, vis, cur, tmpRs, results);
    return results;
  }

  private void backtrack(int[] nums,
                         Map<Integer, Integer> stat, Map<Integer, Integer> vis,
                         int cur,
                         Integer[] tmpRs,
                         List<List<Integer>> results) {
    if (cur == nums.length) {
      results.add(new ArrayList<>(Arrays.asList(tmpRs)));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      if (vis.get(nums[i]).equals(stat.get(nums[i]))) {
        continue;
      }

      tmpRs[cur] = nums[i];
      vis.put(nums[i], vis.get(nums[i]) + 1);
      backtrack(nums, stat, vis, cur + 1, tmpRs, results);
      vis.put(nums[i], vis.get(nums[i]) - 1);
    }
  }
}
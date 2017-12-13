package com.msh.solutions._78_Subsets;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/11.
 */
public class Solution {
  // 不可重集的子集，回溯
  public List<List<Integer>> subsets(int[] nums) {
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
    int cur = 0;
    backtrack(nums, vis, cur, results);
    return results;
  }

  private void backtrack(int[] nums, boolean[] vis, int cur,
                         List<List<Integer>> results) {
    if (cur == nums.length) {
      List<Integer> tmpRs = new ArrayList<>(nums.length);
      for (int j = 0; j < nums.length; j++) {
        if (vis[j]) {
          tmpRs.add(nums[j]);
        }
      }
      results.add(tmpRs);
      return;
    }

    vis[cur] = false;
    backtrack(nums, vis, cur + 1, results);
    vis[cur] = true;
    backtrack(nums, vis, cur + 1, results);
  }
}
package com.msh.solutions._40_Combination_Sum_2;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/12.
 */
public class Solution {
  // 枚举加法式，使用vis统计数量，枚举小于等于target的元素
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    int[] nums = candidates;
    if (nums == null) {
      return null;
    }

    List<List<Integer>> results = new ArrayList<>();
    if (nums.length == 0) {
      return results;
    }

    Arrays.sort(nums);
    if (nums[0] > target) {
      return results;
    }

    Map<Integer, Integer> stat = new HashMap<>();
    Map<Integer, Integer> vis = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (!stat.containsKey(nums[i])) {
        stat.put(nums[i], 0);
        vis.put(nums[i], 0);
      }
      stat.put(nums[i], 1 + stat.get(nums[i]));
    }

    int cur = 0;
    Stack<Integer> buf = new Stack<>();
    backtrack(nums, stat, vis, cur, target, buf, results);
    return results;
  }

  private void backtrack(int[] nums,
                         Map<Integer, Integer> stat, Map<Integer, Integer> vis,
                         int cur, int target,
                         Stack<Integer> buf, List<List<Integer>> results) {
    if (target == 0) {
      results.add(new ArrayList<>(buf));
      return;
    }

    for (int i = cur; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int chosenCnt = vis.get(nums[i]);
      if (chosenCnt == stat.get(nums[i])) {
        continue;
      }
      if (nums[i] > target) {
        break;
      }

      buf.push(nums[i]);
      target -= nums[i];
      vis.put(nums[i], chosenCnt + 1);
      backtrack(nums, stat, vis, i, target, buf, results);
      buf.pop();
      target += nums[i];
      vis.put(nums[i], chosenCnt);
    }
  }

  public static void main(String[] args) {
    int[] nums = {10, 1, 2, 7, 6, 1, 5};
    int target = 8;
    List<List<Integer>> results = new Solution().combinationSum2(nums, target);
    for (List<Integer> result : results) {
      System.out.println(Arrays.toString(result.toArray()));
    }
  }
}

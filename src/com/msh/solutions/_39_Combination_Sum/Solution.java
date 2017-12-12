package com.msh.solutions._39_Combination_Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by monkeysayhi on 2017/12/12.
 */
class Solution {
  // 枚举所有可能，回溯
  // 每次只需要尝试大于等于自己的元素
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
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

    Stack<Integer> buf = new Stack<>();
    int cur = 0;
    backtrack(nums, cur, target, buf, results);
    return results;
  }

  private void backtrack(int[] nums, int cur, int target,
                         Stack<Integer> buf,
                         List<List<Integer>> results) {
    if (target == 0) {
      results.add(new ArrayList<>(buf));
      return;
    }

    for (int i = cur; i < nums.length; i++) {
      if (nums[i] > target) {
        break;
      }
      target -= nums[i];
      buf.push(nums[i]);
      backtrack(nums, i, target, buf, results);
      buf.pop();
      target += nums[i];
    }
  }
}

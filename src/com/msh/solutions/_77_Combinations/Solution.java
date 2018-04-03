package com.msh.solutions._77_Combinations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/4/3.
 */
public class Solution {
  // 回溯，类似于子集问题，递归终止条件为“子集大小等于k”或“尝试到第n位”
  public List<List<Integer>> combine(int n, int k) {
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = i + 1;
    }
    Stack<Integer> buf = new Stack<>();
    List<List<Integer>> rs = new ArrayList<>();
    backtrack(nums, k, 0, buf, rs);
    return rs;
  }

  private void backtrack(int[] nums, int k, int cur,
                         Stack<Integer> buf, List<List<Integer>> rs) {
    if (buf.size() == k) {
      rs.add(new ArrayList<>(buf));
      return;
    }
    if (cur == nums.length) {
      return;
    }
    // 卡了一个很无聊的badcase: [20, 16]
    if ((nums.length - cur) + buf.size() < k) {
      return;
    }

    buf.push(nums[cur]);
    backtrack(nums, k, cur + 1, buf, rs);
    buf.pop();
    backtrack(nums, k, cur + 1, buf, rs);
  }
}
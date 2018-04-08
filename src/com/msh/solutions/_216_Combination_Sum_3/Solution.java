package com.msh.solutions._216_Combination_Sum_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/4/8.
 */
public class Solution {
  // 枚举所有方案，子集问题，回溯
  public List<List<Integer>> combinationSum3(int k, int n) {
    if (k <= 0 || n <= 0) {
      return new LinkedList<>();
    }
    boolean[] vis = new boolean[9 + 1];
    Stack<Integer> buf = new Stack<>();
    List<List<Integer>> rs = new LinkedList<>();
    backtrack(vis, n, k, 1, buf, rs);
    return rs;
  }

  private void backtrack(boolean[] vis, int sum, int cnt, int cur,
                         Stack<Integer> buf, List<List<Integer>> rs) {
    if (sum == 0 && cnt == 0) {
      rs.add(new ArrayList<>(buf));
      return;
    }
    if (sum < 0 || cnt < 0) {
      return;
    }
    for (int i = cur; i <= 9; i++) {
      if (vis[i]) {
        continue;
      }
      if (i > sum) {
        break;
      }
      vis[i] = true;
      buf.push(i);
      backtrack(vis, sum - i, cnt - 1, i + 1, buf, rs);
      buf.pop();
      vis[i] = false;
    }
  }
}
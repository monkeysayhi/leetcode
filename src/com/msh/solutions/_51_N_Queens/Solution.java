package com.msh.solutions._51_N_Queens;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/4/4.
 */
public class Solution {
  // 枚举，设 pos[i]是第i行皇后的列号，问题缩小到“求n的全排列”，然后剪掉45°方向可以攻击的枝
  public List<List<String>> solveNQueens(int n) {
    if (n == 0) {
      return new LinkedList<>();
    }
    List<List<String>> rs = new LinkedList<>();
    int[] buf = new int[n];
    boolean[] vis = new boolean[n];
    backtrack(n, vis, 0, buf, rs);
    return rs;
  }

  private void backtrack(int n, boolean[] vis, int cur,
                         int[] buf, List<List<String>> rs) {
    if (cur == n) {
      List<String> tmpRs = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        char[] chars = new char[n];
        for (int j = 0; j < n; j++) {
          if (j == buf[i]) {
            chars[j] = 'Q';
          } else {
            chars[j] = '.';
          }
        }
        tmpRs.add(new String(chars));
      }
      rs.add(tmpRs);
      return;
    }

    for (int i = 0; i < n; i++) {
      if (vis[i]) {
        continue;
      }
      if (can45Attack(buf, cur, i)) {
        continue;
      }
      buf[cur] = i;
      vis[i] = true;
      backtrack(n, vis, cur + 1, buf, rs);
      vis[i] = false;
    }
  }

  // 如果45°方向可以攻击，那么两个坐标的斜率为+1/-1
  private boolean can45Attack(int[] pos, int newRowNo, int newColNo) {
    for (int rowNo = 0; rowNo < newRowNo; rowNo++) {
      int colNo = pos[rowNo];
      if (Math.abs(newRowNo - rowNo) == Math.abs(newColNo - colNo)) {
        return true;
      }
    }
    return false;
  }
}
package com.msh.solutions._52_N_Queens_2;

/**
 * Created by monkeysayhi on 2018/4/6.
 */
public class Solution {
  // 回溯，枚举全排列，pos[i]为第i行皇后的列号 + 剪掉可斜向攻击的方案，统计方案数
  public int totalNQueens(int n) {
    // 规定
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return 1;
    }
    boolean[] vis = new boolean[n];
    int[] buf = new int[n];
    return backtrack(n, vis, 0, buf);
  }

  private int backtrack(int n, boolean[] vis, int cur, int[] buf) {
    if (cur == n) {
      return 1;
    }
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      if (vis[i]) {
        continue;
      }
      if (canXieAttack(buf, cur, i)) {
        continue;
      }
      buf[cur] = i;
      vis[i] = true;
      cnt += backtrack(n, vis, cur + 1, buf);
      vis[i] = false;
    }
    return cnt;
  }

  // 斜率为+1/-1，则斜向可攻击
  private boolean canXieAttack(int[] buf, int newRowNo, int newColNo) {
    for (int rowNo = 0; rowNo < newRowNo; rowNo++) {
      int colNo = buf[rowNo];
      if (Math.abs(rowNo - newRowNo) == Math.abs(colNo - newColNo)) {
        return true;
      }
    }
    return false;
  }
}
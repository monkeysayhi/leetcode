package com.msh.solutions._756_Pyramid_Transition_Matrix;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by monkeysayhi on 2018/9/23.
 */
public class Solution {
  private final static int M = 26;
  // 回溯，逐层枚举
  public boolean pyramidTransition(String bottom, List<String> allowed) {
    Set<Character>[][] es = new HashSet[M][M];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < M; j++) {
        es[i][j] = new HashSet<>();
      }
    }
    for (String s : allowed) {
      char a = s.charAt(0);
      char b = s.charAt(1);
      char c = s.charAt(2);
      es[a - 'A'][b - 'A'].add(c);
    }
    int n = bottom.length();
    char[][] buf = new char[n][n];
    int curRow = n - 1;
    for (int i = 0; i <= curRow; i++) {
      buf[curRow][i] = bottom.charAt(i);
    }
    int curCol = 0;
    return backtrack(es, curRow - 1, curCol, buf);
  }

  private boolean backtrack(Set<Character>[][] es, int curRow, int curCol, char[][] buf) {
    if (curRow == -1) {
      return true;
    }
    assert curCol <= curRow;
    char a = buf[curRow + 1][curCol];
    char b = buf[curRow + 1][curCol + 1];
    for (char c : es[a - 'A'][b - 'A']) {
      buf[curRow][curCol] = c;
      boolean can = false;
      if (curCol < curRow) {
        can = backtrack(es, curRow, curCol + 1, buf);
      } else {
        can = backtrack(es, curRow - 1, 0, buf);
      }
      if (can) {
        return true;
      }
    }
    return false;
  }
}
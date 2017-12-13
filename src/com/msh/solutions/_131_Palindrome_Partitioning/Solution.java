package com.msh.solutions._131_Palindrome_Partitioning;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2017/12/12.
 */
public class Solution {
  // 枚举所有结果，回溯法。
  // 回溯：vis记录划分位置
  // 判断子串是回文串：暴力
  public List<List<String>> partition(String s) {
    if (s == null) {
      return null;
    }

    List<List<String>> results = new ArrayList<>();
    char[] chars = s.toCharArray();
    if (chars.length == 0) {
      return results;
    }

    boolean[] vis = new boolean[chars.length + 1];
    int cur = 0;
    backtrack(chars, vis, cur, results, s);
    return results;
  }

  private void backtrack(char[] chars, boolean[] vis, int cur,
                         List<List<String>> results, String str) {
    if (cur == chars.length) {
      int lastSplit = 0;
      List<String> tmpRs = new ArrayList<>();
      for (int i = 1; i <= chars.length; i++) {
        if (vis[i]) {
          tmpRs.add(str.substring(lastSplit, i));
          lastSplit = i;
        }
      }
      results.add(tmpRs);
      return;
    }

    for (int i = cur + 1; i <= chars.length; i++) {
      if (!isPalindrome(chars, cur, i)) {
        continue;
      }
      vis[i] = true;
      backtrack(chars, vis, i, results, str);
      vis[i] = false;
    }
  }

  private boolean isPalindrome(char[] chars, int from, int to) {
    for (int i = 0; i < to - from; i++) {
      if (chars[to - i - 1] != chars[from + i]) {
        return false;
      }
    }
    return true;
  }
}

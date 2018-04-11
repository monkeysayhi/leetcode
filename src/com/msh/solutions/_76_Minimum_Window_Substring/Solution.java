package com.msh.solutions._76_Minimum_Window_Substring;

/**
 * Created by monkeysayhi on 2018/4/11.
 */
public class Solution {
  // solution -1: 暴力枚举(i, j)，判断s[i...j]是否包含t；通过预先统计字符数量，可O(1)完成判断. O(n^2)
  // solution -2: 最优化，求最小，设 dp[i] 为 “以位置i结尾的最小窗口的大小”，则 dp[i] = ，，没思路
  // solution 1: 双指针，维护窗口s[i...j]。1，从`i = 0, j = 0`开始；2，j增长到`s[i...j]包含t`；3，i增长到`s[i...j]不包含t`，过程中能扫描到所有窗口，取最小；4，重复2-4，直至`j == s.len - 1 && s[i...j]不包含t`. O(n)
  public String minWindow(String s, String t) {
    if (s == null || t == null
        || s.length() == 0 || t.length() == 0) {
      return "";
    }

    char[] chars1 = s.toCharArray();
    char[] chars2 = t.toCharArray();
    int len1 = chars1.length;
    int len2 = chars2.length;
    int[] stat1 = new int[128];
    int[] stat2 = new int[128];
    for (char ch : chars2) {
      stat2[ch]++;
    }

    int i = 0;
    int j = 0;
    stat1[chars1[0]]++;
    int minLen = Integer.MAX_VALUE;
    int[] minLenPair = null;
    while (j < len1) {
      // opt
      boolean quickNotContain = false;
      while (j < len1 && (quickNotContain || !contain(stat1, stat2))) {
        quickNotContain = false;
        j++;
        if (j < len1) {
          stat1[chars1[j]]++;
          if (stat2[chars1[j]] == 0 || stat2[chars1[j]] > stat1[chars1[j]]) {
            quickNotContain = true;
          }
        }
      }
      // opt
      boolean quickContain = false;
      while (j < len1 && (quickContain || contain(stat1, stat2))) {
        quickContain = false;
        if (minLen > j - i + 1) {
          minLen = j - i + 1;
          minLenPair = new int[]{i, j};
        }
        if (minLen == 1) {
          return "" + chars1[i];
        }
        if (stat1[chars1[i]] > 0) {
          stat1[chars1[i]]--;
        }
        i++;
        if (stat2[chars1[i - 1]] <= stat1[chars1[i - 1]]) {
          quickContain = true;
        }
      }
    }
    if (minLenPair == null) {
      return "";
    }
    return s.substring(minLenPair[0], minLenPair[1] + 1);
  }

  private boolean contain(int[] stat1, int[] stat2) {
    for (int i = 0; i < 128; i++) {
      // 题目定义的包含关系统计了数量
      if (stat1[i] < stat2[i]) {
        return false;
      }
    }
    return true;
  }
}
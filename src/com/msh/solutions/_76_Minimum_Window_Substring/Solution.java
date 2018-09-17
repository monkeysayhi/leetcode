package com.msh.solutions._76_Minimum_Window_Substring;

/**
 * Created by monkeysayhi on 2018/4/11.
 */
public class Solution {
  // solution -1: 暴力，枚举<i, j>的同时维护词频统计 stat，O(n^2)
  // solution -2: 最优化，没思路
  // solution -3: 维护窗口<i, j>和词频统计stat，s[i]一定是t中的字符，j增长时，如果s[j]是t中字符，则尝试右移i，直至stat即将remove元素。题意理解错误，这样统计的是集合t中的元素
  // solution 1: 维护窗口，不过是一个朴素的双指针思想。对于窗口<i, j>，如果不包含t，则增长j直至包含t，否则增长i直至不包含t，在此期间，可以得到所有包含t的窗口，记录最小窗口即可. O(n)

  // solution 1
  public String minWindow(String s, String t) {
    // assume valid
    char[] s1 = s.toCharArray();
    char[] s2 = t.toCharArray();

    int[] stat1 = new int[128];
    int[] stat2 = new int[128];
    for (char ch : s2) {
      stat2[ch]++;
    }

    int i = 0;
    int j = 0;
    int ansI = -1;
    int ansJ = -1;
    while (j < s1.length) {
      stat1[s1[j]]++;
      if (!contains(stat1, stat2)) {
        j++;
        continue;
      }
      while (contains(stat1, stat2)) {
        if (ansI == -1 || ansJ - ansI > j - i) {
          ansI = i;
          ansJ = j;
        }
        stat1[s1[i]]--;
        i++;
      }
      j++;
    }

    if (ansI == -1) {
      return "";
    }
    return new String(s1, ansI, ansJ - ansI + 1);
  }

  private boolean contains(int[] stat1, int[] stat2) {
    for (int i = 0; i < 128; i++) {
      if (stat1[i] < stat2[i]) {
        return false;
      }
    }
    return true;
  }

//     // solution 1.opt
//     public String minWindow(String s, String t) {
//         if (s == null || t == null
//             || s.length() == 0 || t.length() == 0) {
//             return "";
//         }

//         char[] chars1 = s.toCharArray();
//         char[] chars2 = t.toCharArray();
//         int len1 = chars1.length;
//         int len2 = chars2.length;
//         int[] stat1 = new int[128];
//         int[] stat2 = new int[128];
//         for (char ch : chars2) {
//             stat2[ch]++;
//         }

//         int i = 0;
//         int j = 0;
//         stat1[chars1[0]]++;
//         int minLen = Integer.MAX_VALUE;
//         int[] minLenPair = null;
//         while (j < len1) {
//             // opt
//             boolean quickNotContain = false;
//             while (j < len1 && (quickNotContain || !contain(stat1, stat2))) {
//                 quickNotContain = false;
//                 j++;
//                 if (j < len1) {
//                     stat1[chars1[j]]++;
//                     if (stat2[chars1[j]] == 0 || stat2[chars1[j]] > stat1[chars1[j]]) {
//                         quickNotContain = true;
//                     }
//                 }
//             }
//             // opt
//             boolean quickContain = false;
//             while (j < len1 && (quickContain || contain(stat1, stat2))) {
//                 quickContain = false;
//                 if (minLen > j - i + 1) {
//                     minLen = j - i + 1;
//                     minLenPair = new int[]{i, j};
//                 }
//                 if (minLen == 1) {
//                     return "" + chars1[i];
//                 }
//                 if (stat1[chars1[i]] > 0) {
//                     stat1[chars1[i]]--;
//                 }
//                 i++;
//                 if (stat2[chars1[i - 1]] <= stat1[chars1[i - 1]]) {
//                     quickContain = true;
//                 }
//             }
//         }
//         if (minLenPair == null) {
//             return "";
//         }
//         return s.substring(minLenPair[0], minLenPair[1] + 1);
//     }

//     private boolean contain(int[] stat1, int[] stat2) {
//         for (int i = 0; i < 128; i++) {
//             // 题目定义的包含关系统计了数量
//             if (stat1[i] < stat2[i]) {
//                 return false;
//             }
//         }
//         return true;
//     }

//     // solution -3
//     public String minWindow(String s, String t) {
//         // assume valid
//         char[] s1 = s.toCharArray();
//         char[] s2 = t.toCharArray();

//         Set<Character> set = new HashSet<>();
//         for (char ch : s2) {
//             set.add(ch);
//         }

//         Map<Character, Integer> stat = new HashMap<>();
//         int i = 0;
//         int j = 0;
//         int ansI = -1;
//         int ansJ = -1;
//         while (j < s1.length) {
//             if (i == j) {
//                 if (!set.contains(s1[i])) {
//                     i++;
//                     j++;
//                 } else {
//                     stat.put(s1[i], 1);
//                     if (stat.size() == set.size()) {
//                         if (ansI == -1 || ansJ - ansI > j - i) {
//                             ansI = i;
//                             ansJ = j;
//                         }
//                     }
//                     j++;
//                 }
//                 continue;
//             }
//             if (!set.contains(s1[j])) {
//                 j++;
//                 continue;
//             }

//             while (i < j) {
//                 if (!set.contains(s1[i])) {
//                     i++;
//                     continue;
//                 }
//                 if (s1[i] == s1[j]) {
//                     stat.put(s1[i], stat.get(s1[i]) - 1);
//                     if (stat.get(s1[i]) == 0) {
//                         stat.remove(s1[i]);
//                     }
//                     i++;
//                     continue;
//                 }
//                 if (stat.get(s1[i]) > 1) {
//                     stat.put(s1[i], stat.get(s1[i]) - 1);
//                     i++;
//                     continue;
//                 }
//                 break;
//             }

//             if (!stat.containsKey(s1[j])) {
//                 stat.put(s1[j], 0);
//             }
//             stat.put(s1[j], stat.get(s1[j]) + 1);
//             if (stat.size() == set.size()) {
//                 if (ansI == -1 || ansJ - ansI > j - i) {
//                     ansI = i;
//                     ansJ = j;
//                 }
//             }

//             j++;
//         }

//         if (ansI == -1) {
//             return "";
//         }
//         return new String(s1, ansI, ansJ - ansI + 1);
//     }
}
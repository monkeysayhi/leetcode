package com.msh.solutions._3_Longest_Substring_Without_Repeating_Characters;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  // solution 2: 双指针单头扫，维护一个非不可重集滑窗[i, j]，借助set实现查重，O(n)
  public int lengthOfLongestSubstring(String s) {
    if (s == null) {
      return 0;
    }
    if (s.length() <= 1) {
      return s.length();
    }

    char[] chars = s.toCharArray();
    Set<Character> set = new HashSet<>();
    int i = 0;
    int j = 1;
    set.add(chars[0]);
    int maxLen = 1;
    while (j < chars.length) {
      while (i < j && set.contains(chars[j])) {
        set.remove(chars[i]);
        i++;
      }
      set.add(chars[j]);
      j++;
      maxLen = Math.max(maxLen, set.size());
    }
    return maxLen;
  }

//     // solution 1: 暴力，扫描所有[i, j]，借助set实现查重，O(n^2)
//     public int lengthOfLongestSubstring(String s) {
//         if (s == null) {
//             return 0;
//         }
//         if (s.length() <= 1) {
//             return s.length();
//         }

//         char[] chars = s.toCharArray();
//         int maxLen = 0;
//         for (int i = 0; i < chars.length; i++) {
//             Set<Character> set = new HashSet<>();
//             set.add(chars[i]);
//             for (int j = i + 1; j < chars.length; j++) {
//                 if (set.contains(chars[j])) {
//                     break;
//                 }
//                 set.add(chars[j]);
//             }
//             maxLen = Math.max(maxLen, set.size());
//         }
//         return maxLen;
//     }
}
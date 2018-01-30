package com.msh.solutions._395_Longest_Substring_with_At_Least_K_Repeating_Characters;

/**
 * Created by monkeysayhi on 2018/1/30.
 */
public class Solution {
  // 暴力，枚举区间[i, j]，判断区间中的元素是否满足条件，O(n^3)

  // 最优化，求最长，dp[i]为截止到位置i的最长子串的长度，stat[i]为截止到位置i各出现次数不足k的元素的频数统计
  // dp[i] = if stat[i][chars[i]] < k: dp[i - 1]; else: dp[i - 1] + 1...错

  // 最优化，求最长，dp[i][k]为截止到位置i出现次数为k的最长子串的长度，stat[i]为截止到位置i各出现次数不足k的元素的频数统计
  // dp[i][k] = dp[i - 1][k]...空间复杂度太大

  // 看题解，分治，找到频数小于k的元素，划分，最后conquer结果。最坏O(n^2)，最好O(nlogn)
  public int longestSubstring(String s, int k) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    if (k > s.length()) {
      return 0;
    }
    if (k <= 1) {
      return s.length();
    }

    return dc(s.toCharArray(), 0, s.length(), k);
  }

  private int dc(char[] chars, int l, int r, int k) {
    if (r - l < k) {
      return 0;
    }

    int[] stat = new int[26];
    for (int i = l; i < r; i++) {
      stat[chars[i] - 'a']++;
    }

    int maxLen = 0;
    for (int i = 0; i < 26; i++) {
      // 不要忘记排除掉不存在的字母
      if (stat[i] >= k || stat[i] == 0) {
        continue;
      }
      char divideCh = (char) (i + 'a');
      for (int j = l; j < r; j++) {
        if (chars[j] == divideCh) {
          // 跳过无用的divideCh，能减少大量无用分支
          int leftR = j;
          int rightL = j + 1;
          while (rightL < r && stat[chars[rightL] - 'a'] < k) {
            rightL++;
          }
          return Math.max(
              dc(chars, l, leftR, k),
              // j从l开始，因此，右分支的左界应当至少是j + 1，否则会形成无限递归
              dc(chars, rightL, r, k)
          );
        }
      }
    }
    return r - l;
  }
}
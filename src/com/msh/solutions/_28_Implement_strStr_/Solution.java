package com.msh.solutions._28_Implement_strStr_;

/**
 * Created by monkeysayhi on 2017/12/8.
 */
class Solution {
  // 暴力, O(m * n)
  public int strStr(String haystack, String needle) {
    if (haystack == null || needle == null) {
      return -1;
    }
    if (haystack.length() < needle.length()) {
      return -1;
    }
    if (needle.length() == 0) {
      return 0;
    }

    int index = -1;
    for (int i = 0; i + needle.length() <= haystack.length(); i++) {
      boolean eq = true;
      for (int j = 0; j < needle.length(); j++) {
        if (haystack.charAt(i + j) != needle.charAt(j)) {
          eq = false;
          break;
        }
      }
      if (eq) {
        index = i;
        break;
      }
    }
    return index;
  }
}

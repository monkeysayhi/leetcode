package com.msh.solutions._8_String_to_Integer_atoi_;

/**
 * Created by monkeysayhi on 2018/2/1.
 */
public class Solution {
  // 模拟，corner case多
  // 题解错误，badcase："    -03242aaa"
  public int myAtoi(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }

    char[] s = str.toCharArray();
    int i = 0;

    // ignore prefix
    for (; i < s.length && s[i] == ' '; i++) {}
    if (i == s.length) {
      return 0;
    }

    // sign
    boolean positive = true;
    if (s[i] == '+') {
      i++;
    } else if (s[i] == '-') {
      positive = false;
      i++;
    }
    if (i == s.length || !isDigit(s[i])) {
      return 0;
    }

    // transfer + ignore suffix
    // allow multi '0' before positive digit
    int rs = 0;
    for (; i < s.length && isDigit(s[i]); i++) {
      int cur = s[i] - '0';
      if (!positive
          && (rs > -(Integer.MIN_VALUE / 10)
          || (rs == -(Integer.MIN_VALUE / 10) && cur >= -(Integer.MIN_VALUE % 10)))) {
        return Integer.MIN_VALUE;
      }
      if (positive
          && (rs > Integer.MAX_VALUE / 10
          || (rs == Integer.MAX_VALUE / 10 && cur >= Integer.MAX_VALUE % 10))) {
        return Integer.MAX_VALUE;
      }
      rs = rs * 10 + cur;
    }
    return positive ? rs : -rs;
  }

  private boolean isDigit(char ch) {
    return ch >= '0' && ch <= '9';
  }
}
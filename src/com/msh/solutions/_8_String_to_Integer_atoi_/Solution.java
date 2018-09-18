package com.msh.solutions._8_String_to_Integer_atoi_;

/**
 * Created by monkeysayhi on 2018/2/1.
 */
public class Solution {
  // 模拟，corner case多
  // 题解错误，badcase："    -03242aaa"
  public int myAtoi(String str) {
    if (str == null) {
      return 0;
    }

    char[] s = str.toCharArray();
    int i = 0;

    // 前导空白符
    for (; i < s.length; i++) {
      if (s[i] != ' ') {
        break;
      }
    }
    if (i == s.length || !(s[i] == '+' || s[i] == '-' || isDigit(s[i]))) {
      return 0;
    }
    assert i < s.length && (s[i] == '+' || s[i] == '-' || isDigit(s[i]));

    // 正负号
    boolean positive = true;
    if (s[i] == '+') {
      i++;
    } else if (s[i] == '-') {
      positive = false;
      i++;
    }

    // transfer && 后缀非数字符
    int rs = 0;
    for (; i < s.length; i++) {
      if (!isDigit(s[i])) {
        break;
      }
      int cur = s[i] - '0';
      if (!positive && rs == -(Integer.MIN_VALUE / 10) && cur == 10 - Integer.MIN_VALUE % 10) {
        return Integer.MIN_VALUE;
      }
      if (rs > Integer.MAX_VALUE / 10 || (rs == Integer.MAX_VALUE / 10 && cur > Integer.MAX_VALUE % 10)) {
        return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      rs = rs * 10 + cur;
    }
    return positive ? rs : -rs;
  }

  private boolean isDigit(char ch) {
    return ch >= '0' && ch <= '9';
  }
}
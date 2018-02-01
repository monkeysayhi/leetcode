package com.msh.solutions._8_String_to_Integer_atoi_;

/**
 * Created by monkeysayhi on 2018/2/1.
 */
public class Solution {
  // 先检查格式，再转换。坑非常多，实现时一定要多跟面试官沟通
  public int myAtoi(String str) {
    if (str == null) {
      // 规定：null返回0
      // throw new NullPointerException();
      return 0;
    }
    // 规定：允许以空白符开头结尾
    str = str.trim();
    int len = checkFormat(str);
    if (len <= 0) {
      // 规定：格式异常返回0
      // throw new RuntimeException("NumberFormatException");
      return 0;
    }

    char[] chars = str.toCharArray();
    int offset = 0;
    boolean isPositive = true;
    if (!Character.isDigit(chars[0])) {
      offset = 1;
      if (chars[0] == '-') {
        isPositive = false;
      }
    }

    long ans = 0;
    for (int i = 0; i < len; i++) {
      ans = ans * 10 + chars[offset + i] - '0';
      if (ans < 0
          || (isPositive && ans > Integer.MAX_VALUE)
          || (!isPositive && ans > Integer.MAX_VALUE + 1L)) {
        // 规定：溢出返回Integer.MAX_VALUE或Integer.MIN_VALUE，且区分正负溢出
        // throw new RuntimeException("NumberLimitOverflowException");
        return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
    }
    return (int) (isPositive ? ans : -ans);
  }

  private int checkFormat(String s) {
    char[] chars = s.toCharArray();
    if (chars.length == 0) {
      return 0;
    }
    if (!(Character.isDigit(chars[0]) || chars[0] == '+' || chars[0] == '-')) {
      return 0;
    }
    int offset = 0;
    if (chars[0] == '+' || chars[0] == '-') {
      offset = 1;
    }
    if (!(chars.length - offset >= 1)) {
      return 0;
    }
    // 规定：允许前导0，因此，不在此处检查前导0
    int len = 0;
    while (offset + len < chars.length) {
      if (!Character.isDigit(chars[offset + len])) {
        break;
      }
      len++;
    }
    if (len == 0) {
      return 0;
    }
    return len;
  }
}
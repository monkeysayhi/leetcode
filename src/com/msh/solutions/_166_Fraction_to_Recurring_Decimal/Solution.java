package com.msh.solutions._166_Fraction_to_Recurring_Decimal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by monkeysayhi on 2018/2/2.
 */
public class Solution {
  // 模拟
  // 需要好好理解无限循环小数与有限循环小数的区别。二者的小数部分都存在循环，**本质在于余数**，无限循环小数的余数是循环的
  // 分为整数与小数部分；小数部分需要统计余数的频率，如果发下余数重复出现，则为无限循环小数，循环节从该余数第一次出现的位置开始
  public String fractionToDecimal(int numerator, int denominator) {
    assert denominator != 0;
    boolean positive = numerator > 0 && denominator > 0
        || numerator < 0 && denominator < 0;
    long d1 = Math.abs((long) numerator);
    long d2 = Math.abs((long) denominator);
    if (d1 == 0) {
      return "0";
    }
    if (d1 % d2 == 0) {
      long rs = d1 / d2;
      return String.valueOf(positive ? rs : -rs);
    }

    StringBuilder sb = new StringBuilder();
    if (!positive) {
      sb.append("-");
    }
    long decimalPart = d1 / d2;
    sb.append(decimalPart);
    sb.append(".");
    d1 = d1 - decimalPart * d2;
    d1 *= 10;
    Map<Long, Integer> d1s = new HashMap<>();
    int pos = sb.length();
    while (d1 > 0) {
      if (d1s.containsKey(d1)) {
        int recurStart = d1s.get(d1);
        sb.insert(recurStart, '(');
        sb.append(')');
        break;
      }
      d1s.put(d1, pos);
      long cur = d1 / d2;
      assert cur >= 0 && cur <= 9;
      sb.append(cur);
      d1 = d1 - cur * d2;
      d1 *= 10;
      pos++;
    }
    return sb.toString();
  }
}
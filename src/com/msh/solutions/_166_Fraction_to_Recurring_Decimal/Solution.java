package com.msh.solutions._166_Fraction_to_Recurring_Decimal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by monkeysayhi on 2018/2/2.
 */
public class Solution {
  // 需要好好理解无限循环小数与有限循环小数的区别。二者的小数部分都存在循环，**本质在于余数**，无限循环小数的余数是循环的
  // 分为整数与小数部分；小数部分需要统计余数的频率，如果发下余数重复出现，则为无限循环小数，循环节从该余数第一次出现的位置开始
  public String fractionToDecimal(int numerator, int denominator) {
    assert denominator != 0;
    if (numerator == 0) {
      return "0";
    }

    // 标答很烂，考虑[-1, -2147483648]却没考虑[-2147483648, -1]
    long numeratorL = numerator;
    long denominatorL = denominator;

    StringBuilder sb = new StringBuilder();
    if ((numeratorL / Math.abs(numeratorL)) * (denominatorL / Math.abs(denominatorL)) < 0) {
      sb.append('-');
    }
    numeratorL = Math.abs(numeratorL);
    denominatorL = Math.abs(denominatorL);

    long d1 = numeratorL / denominatorL;
    long d2 = numeratorL % denominatorL;
    sb.append(d1);
    if (d2 == 0) {
      return sb.toString();
    }

    sb.append('.');
    int pos = sb.length();
    Map<Long, Integer> d2Stat = new HashMap<>();
    while (d2 > 0) {
      if (d2Stat.containsKey(d2)) {
        sb.insert(d2Stat.get(d2), "(").append(")");
        break;
      }
      d2Stat.put(d2, pos);
      numeratorL = d2 * 10;
      d1 = numeratorL / denominatorL;
      d2 = numeratorL % denominatorL;
      assert d1 >= 0 && d1 <= 9;
      sb.append(d1);
      pos++;
    }
    return sb.toString();
  }
}
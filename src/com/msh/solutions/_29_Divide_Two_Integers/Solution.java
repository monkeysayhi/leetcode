package com.msh.solutions._29_Divide_Two_Integers;

/**
 * Created by monkeysayhi on 2018/1/31.
 */
public class Solution {
  // 以2的次幂为底短除；为避免出现Integer.MIN_VALUE时的溢出问题，统一使用long。。。
  // 标答没考虑除0的问题
  public int divide(int dividend, int divisor) {
    if (divisor == 0) {
      return Integer.MAX_VALUE;
    }
    if (dividend == 0) {
      return 0;
    }
    boolean isPositive = false;
    if (dividend < 0 && divisor < 0 || dividend > 0 && divisor > 0) {
      isPositive = true;
    }
    long dividendL = Math.abs((long) dividend);
    long divisorL = Math.abs((long) divisor);
    if (divisorL == 1) {
      if (-dividendL == Integer.MIN_VALUE) {
        return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      return (int) (isPositive ? dividendL : -dividendL);
    }
    if (dividendL < divisorL) {
      return 0;
    }
    if (dividendL == divisorL) {
      return isPositive ? 1 : -1;
    }

    int bitLen = 0;
    while (divisorL << bitLen <= dividendL) {
      if (divisorL << bitLen < 0) {
        break;
      }
      bitLen++;
    }
    bitLen--;

    int ans = 0;
    while (dividendL > divisorL) {
      while (divisorL << bitLen > dividendL) {
        bitLen--;
      }
      ans += 1 << bitLen;
      dividendL -= divisorL << bitLen;
    }
    return isPositive ? ans : -ans;
  }
}
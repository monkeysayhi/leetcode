package com.msh.solutions._29_Divide_Two_Integers;

/**
 * Created by monkeysayhi on 2018/1/31.
 */
public class Solution {
  // 以2的次幂为底短除；为避免出现Integer.MIN_VALUE时的溢出问题，统一使用long。。。
  public int divide(int dividend, int divisor) {
    // assume valid
    if (dividend == 0) {
      return 0;
    }
    if (divisor == 1) {
      return dividend;
    }
    if (divisor == -1) {
      if (dividend == Integer.MIN_VALUE) {
        return Integer.MAX_VALUE;
      }
      return -dividend;
    }

    boolean positive = (dividend > 0 && divisor > 0)
        || (dividend < 0 && divisor < 0);
    long ddL = Math.abs((long) dividend);
    long dsL = Math.abs((long) divisor);

    int rs = (int) divideInt(ddL, dsL);
    return positive ? rs : -rs;
  }

  private long divideInt(long dd, long ds) {
    if (dd < ds) {
      return 0;
    }
    if (dd == ds) {
      return 1;
    }

    int startI = 0;
    while (dd > (ds << startI)) {
      startI++;
    }
    if (dd == (ds << startI)) {
      return 1 << startI;
    }
    long rs = 0;
    for (int i = startI - 1; i >= 0; i--) {
      if (dd < ds) {
        break;
      }
      if (dd > (ds << i)) {
        rs = rs | (1 << i);
        dd -= ds << i;
      }
    }
    return rs;
  }
}
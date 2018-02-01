package com.msh.solutions._50_Pow_x_n_;

/**
 * Created by monkeysayhi on 2018/2/1.
 */
public class Solution {
  // 快速幂。好几年不写都忘记怎么推了。。。
  public double myPow(double x, int n) {
    // 规定：0^0 = 1
    if (x == 0 && n == 0) {
      return 1;
    }
    // 防止n给到Integer.MIN_VALUE
    long nL = n;
    // 运算规则：pow(x, -n) == pow(1/x, n)
    if (nL < 0) {
      x = 1 / x;
      nL = -nL;
    }
    if (nL == 1) {
      return x;
    }
    if (x == 0) {
      return 0;
    }

    double ans = 1;
    while (nL > 0) {
      if ((nL & 1) == 1) {
        ans *= x;
      }
      x *= x;
      nL >>= 1;
    }
    return ans;
  }
}
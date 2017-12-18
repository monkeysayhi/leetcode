package com.msh.solutions._69_Sqrt_x;

/**
 * Created by monkeysayhi on 2017/12/18.
 */
public class Solution {
  public int mySqrt(int x) {
    if (x == 1) {
      return 1;
    }

    long v = x;
    long l = 1;
    long r = 1 + x / 2;
    while (l < r) {
      long m = l + (r - l) / 2;
      if (m * m > v) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return (int) (l - 1);
  }
}

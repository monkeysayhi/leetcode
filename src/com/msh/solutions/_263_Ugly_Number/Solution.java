package com.msh.solutions._263_Ugly_Number;

/**
 * Created by monkeysayhi on 2018/1/8.
 */
public class Solution {
  public boolean isUgly(int num) {
    if (num < 1) {
      return false;
    }
    if (num == 1) {
      return true;
    }
    while (num % 2 == 0) {
      num /= 2;
    }
    while (num % 3 == 0) {
      num /= 3;
    }
    while (num % 5 == 0) {
      num /= 5;
    }
    return num == 1;
  }
}

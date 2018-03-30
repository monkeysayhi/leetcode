package com.msh.solutions._43_Multiply_Strings;

/**
 * Created by monkeysayhi on 2018/3/30.
 */
public class Solution {
  // 模拟
  public String multiply(String num1, String num2) {
    // assume valid
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }

    char[] chars1 = num1.toCharArray();
    char[] chars2 = num2.toCharArray();
    char[] pro = new char[chars1.length + chars2.length];
    for (int j = chars2.length - 1; j >= 0; j--) {
      int k = pro.length - 1 - (chars2.length - 1 - j);
      int jinwei = 0;
      for (int i = chars1.length - 1; i >= 0; i--) {
        int tmpRs = jinwei + (chars1[i] - '0') * (chars2[j] - '0');
        if (pro[k] != 0) {
          tmpRs += pro[k] - '0';
        }
        jinwei = tmpRs / 10;
        int left = tmpRs % 10;
        pro[k] = (char) ('0' + left);
        k--;
      }
      if (jinwei > 0) {
        pro[k] = (char) ('0' + jinwei);
      }
    }

    // make result
    int start = 0;
    while (pro[start] == 0) {
      start++;
    }
    return new String(pro, start, pro.length - start);
  }
}
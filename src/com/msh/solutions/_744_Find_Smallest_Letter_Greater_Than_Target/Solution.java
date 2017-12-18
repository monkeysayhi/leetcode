package com.msh.solutions._744_Find_Smallest_Letter_Greater_Than_Target;

import java.util.Arrays;

/**
 * Created by monkeysayhi on 2017/12/18.
 */
public class Solution {
  public char nextGreatestLetter(char[] letters, char target) {
    char[] chars = letters;
    Arrays.sort(chars);

    int ub = bsearchU(chars, 0, chars.length, target);
    if (ub == chars.length) {
      ub = 0;
    }
    return chars[ub];
  }

  private int bsearchU(char[] chars, int l, int r, char v) {
    while (l < r) {
      int m = l + (r - l) / 2;
      if (chars[m] > v) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return l;
  }
}

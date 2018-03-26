package com.msh.solutions._386_Lexicographical_Numbers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/3/26.
 */
public class Solution {
  // solution 1: 排序, 时间O(nlogn)，空间O(1)
  // solution 2: 直接生成, 时间O(n)，空间O(1)
  public List<Integer> lexicalOrder(int n) {
    if (n <= 0) {
      return new ArrayList<>();
    }
    List<Integer> rs = new ArrayList<>(n);
    for (int firstDigit = 1; firstDigit <= 9; firstDigit++) {
      int num = firstDigit;
      if (num > n) {
        break;
      }
      rs.add(num);
      addOtherDigits(num, n, rs);
    }
    return rs;
  }

  private void addOtherDigits(int baseNum, int n, List<Integer> rs) {
    assert baseNum >= 1;
    for (int digit = 0; digit <= 9; digit++) {
      int num = baseNum * 10 + digit;
      if (num > n) {
        break;
      }
      rs.add(num);
      addOtherDigits(num, n, rs);
    }
  }
}
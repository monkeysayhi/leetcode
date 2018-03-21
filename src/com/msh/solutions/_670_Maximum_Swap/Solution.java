package com.msh.solutions._670_Maximum_Swap;

/**
 * Created by monkeysayhi on 2018/3/21.
 */
public class Solution {
  // 把最大的交换到最前（正序遍历i），最小的交换到最后（逆序遍历j）, O(n^2)
  public int maximumSwap(int num) {
    if (num == 0) {
      return 0;
    }
    char[] chars = String.valueOf(num).toCharArray();
    for (int i = 0; i < chars.length; i++) {
      int maxJ = i;
      for (int j = chars.length - 1; j >= i + 1; j--) {
        if (chars[j] > chars[maxJ]) {
          maxJ = j;
        }
      }
      if (maxJ != i) {
        swap(chars, i, maxJ);
        return Integer.valueOf(new String(chars));
      }
    }
    return Integer.valueOf(new String(chars));
  }

  private void swap(char[] chars, int i, int j) {
    char tmp = chars[i];
    chars[i] = chars[j];
    chars[j] = tmp;
  }
}
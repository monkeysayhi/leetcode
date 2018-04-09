package com.msh.solutions._318_Maximum_Product_of_Word_Lengths;

/**
 * Created by monkeysayhi on 2018/4/9.
 */
public class Solution {
  // 暴力枚举(i, j)，O(26)比较是否有公共字符，记录max. O(n^2)
  // 此处使用boolean[]统计字符，题解使用位运算能进一步减小常数项
  public int maxProduct(String[] words) {
    if (words == null || words.length < 2) {
      return 0;
    }

    boolean[][] stats = new boolean[words.length][26];
    for (int i = 0; i < words.length; i++) {
      for (char ch : words[i].toCharArray()) {
        stats[i][ch - 'a'] = true;
      }
    }

    int max = 0;
    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if (!shareCommonLetters(stats[i], stats[j])) {
          max = Math.max(max, words[i].length() * words[j].length());
        }
      }
    }
    return max;
  }

  private boolean shareCommonLetters(boolean[] stat1, boolean[] stat2) {
    for (int i = 0; i < 26; i++) {
      if (stat1[i] && stat2[i]) {
        return true;
      }
    }
    return false;
  }
}
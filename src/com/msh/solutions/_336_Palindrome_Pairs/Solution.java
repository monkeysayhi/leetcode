package com.msh.solutions._336_Palindrome_Pairs;

import java.util.*;

/**
 * Created by monkeysayhi on 2018/9/24.
 */
public class Solution {
  // solution 1: 暴力，枚举 Pair<i, j>，判断是否是回文串，O(k * n^2)
  // 显然，对于 w[i]，可以直接计算出其可能的回文串，然后 O(1) 查找即可
  // solution 2: 先查找对称的 Pair<i, j>，放入结果集，O(n * k)；再根据 长串 w[i] O(k^2) 构造回文串，O(1)查找，总 O(n * k^2)；最后，构造反转单词的集合 r，根据长串 r[i] 构造回文串并查找，总 O(n * k^2)。总O(n * k^2)
  public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> rs = new ArrayList<>();
    if (words == null || words.length == 0) {
      return rs;
    }

    Map<String, Integer> wordMap = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      wordMap.put(words[i], i);
    }

    for (String w : wordMap.keySet()) {
      String rw = reverse(w);
      if (w.length() > 0 && w.equals(rw) && wordMap.containsKey("")) {
        rs.add(Arrays.asList(wordMap.get(w), wordMap.get("")));
        rs.add(Arrays.asList(wordMap.get(""), wordMap.get(w)));
      }
    }

    for (String w : wordMap.keySet()) {
      String rw = reverse(w);
      if (!w.equals(rw) && wordMap.containsKey(rw)) {
        rs.add(Arrays.asList(wordMap.get(w), wordMap.get(rw)));
      }
    }

    findPairs(wordMap, false, rs);

    Map<String, Integer> reversedWordMap = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      reversedWordMap.put(reverse(words[i]), i);
    }
    findPairs(reversedWordMap, true, rs);

    return rs;
  }

  private String reverse(String s) {
    return new StringBuilder(s).reverse().toString();
  }

  private void findPairs(Map<String, Integer> wordMap, boolean reverse,
                         List<List<Integer>> rs) {
    for (String w : wordMap.keySet()) {
      List<String> diffWs = addingShorterChars2Palindrome(w);
      for (String diffW : diffWs) {
        if (wordMap.containsKey(diffW)) {
          if (!reverse) {
            rs.add(Arrays.asList(wordMap.get(w), wordMap.get(diffW)));
          } else {
            rs.add(Arrays.asList(wordMap.get(diffW), wordMap.get(w)));
          }
        }
      }
    }
  }

  private List<String> addingShorterChars2Palindrome(String w) {
    List<String> diffWs = new ArrayList<>();
    if (w.length() <= 1) {
      return diffWs;
    }
    char[] s = w.toCharArray();
    for (int len = s.length + 1; len <= s.length * 2 - 1; len++) {
      int l = (len - 1) / 2;
      int r = len / 2;
      while (l >= 0 && r <= s.length - 1 && s[l] == s[r]) {
        l--;
        r++;
      }
      if (r == s.length) {
        diffWs.add(reverse(w.substring(0, l + 1)));
      }
    }
    return diffWs;
  }
}
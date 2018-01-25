package com.msh.solutions._17_Letter_Combinations_of_a_Phone_Number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  // 最简单的枚举。。
  public List<String> letterCombinations(String digits) {
    if (digits == null) {
      return null;
    }
    if (digits.length() == 0
        || digits.indexOf('1') > 0 || digits.indexOf('0') > 0) {
      return new ArrayList<>();
    }

    Map<Integer, String> map = new HashMap<>();
    map.put(2, "abc");
    map.put(3, "def");
    map.put(4, "ghi");
    map.put(5, "jkl");
    map.put(6, "mno");
    map.put(7, "pqrs");
    map.put(8, "tuv");
    map.put(9, "wxyz");

    char[] buf = new char[digits.length()];
    List<String> results = new ArrayList<>();
    backtrack(digits.toCharArray(), map, 0, buf, results);
    return results;
  }

  private void backtrack(char[] digits, Map<Integer, String> map,
                         int cur, char[] buf,
                         List<String> results) {
    if (cur == buf.length) {
      results.add(String.valueOf(buf));
      return;
    }
    for (char ch : map.get(digits[cur] - '0').toCharArray()) {
      buf[cur] = ch;
      backtrack(digits, map, cur + 1, buf, results);
    }
  }
}
package com.msh.solutions._179_Largest_Number;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by monkeysayhi on 2018/1/29.
 */
public class Solution {
  // solution 1: 基数排序变种，O(n)，不好写
  // soltuion 2: 实现个比较器，cmp(s1 + s2, s2 + s1)即可
  public String largestNumber(int[] nums) {
    if (nums == null) {
      return null;
    }
    if (nums.length == 0) {
      return "";
    }

    String[] strs = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      strs[i] = String.valueOf(nums[i]);
    }
    Arrays.sort(strs, new Comparator<String>() {
      public int compare(String s1, String s2) {
        // 0必然放后面
        if (s1.equals("0")) {
          return 1;
        }
        if (s2.equals("0")) {
          return -1;
        }
        return cmp(s1 + s2, s2 + s1);
      }

      private int cmp(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
          int diff = s2.charAt(i) - s1.charAt(i);
          if (diff != 0) {
            return diff;
          }
        }
        return 0;
      }
    });

    // 防止全0
    if (strs[0].equals("0")) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    for (String str : strs) {
      sb.append(str);
    }
    return sb.toString();
  }
}
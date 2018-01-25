package com.msh.solutions._49_Group_Anagrams;

import java.util.*;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  // solution 2: 排序只是为了判断集合是否相等，也统计字符频数可以O(k)的时间判断，总时间O(n * k)
  public List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null) {
      return null;
    }
    if (strs.length == 0) {
      return new ArrayList<>();
    }

    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      String key = constructKey(cntFreq(str));
      if (!map.containsKey(key)) {
        map.put(key, new ArrayList<String>());
      }
      map.get(key).add(str);
    }

    List<List<String>> rs = new ArrayList<>();
    rs.addAll(map.values());
    return rs;
  }

  private int[] cntFreq(String s) {
    int[] stat = new int[26];
    for (char ch : s.toCharArray()) {
      stat[ch - 'a']++;
    }
    return stat;
  }

  private String constructKey(int[] stat) {
    return Arrays.toString(stat);
  }

//     // solution 1: 按照排序后的str做散列，O(n * klogk)
//     public List<List<String>> groupAnagrams(String[] strs) {
//         if (strs == null) {
//             return null;
//         }
//         if (strs.length == 0) {
//             return new ArrayList<>();
//         }

//         Map<String, List<String>> map = new HashMap<>();
//         for (String str : strs) {
//             char[] chars = str.toCharArray();
//             Arrays.sort(chars);
//             String sortedStr = new String(chars);
//             if (!map.containsKey(sortedStr)) {
//                 map.put(sortedStr, new ArrayList<String>());
//             }
//             map.get(sortedStr).add(str);
//         }

//         List<List<String>> rs = new ArrayList<>();
//         rs.addAll(map.values());
//         return rs;
//     }
}
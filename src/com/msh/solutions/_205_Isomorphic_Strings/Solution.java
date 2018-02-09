package com.msh.solutions._205_Isomorphic_Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by monkeysayhi on 2018/2/9.
 */
public class Solution {
  public boolean isIsomorphic(String s, String t) {
    // assume valid
    if (s.equals(t)) {
      return true;
    }
    return canMap(s, t) && canMap(t, s);
  }

  private boolean canMap(String s1, String s2) {
    Map<Character, Character> map = new HashMap<>();
    for (int i = 0; i < s1.length(); i++) {
      char c1 = s1.charAt(i);
      char c2 = s2.charAt(i);
      if (map.containsKey(c1)) {
        if (map.get(c1) != c2) {
          return false;
        }
      }
      map.put(c1, c2);
    }
    return true;
  }
}
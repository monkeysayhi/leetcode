package com.msh.solutions._756_Pyramid_Transition_Matrix;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by monkeysayhi on 2018/9/23.
 */
public class Solution {
  // 深搜（回溯），求深度是否能达到 len(bottom)
  public boolean pyramidTransition(String bottom, List<String> allowed) {
    if (bottom == null || bottom.length() == 0) {
      return true;
    }
    if (bottom.length() == 1) {
      return true;
    }
    if (allowed == null || allowed.size() == 0) {
      return false;
    }

    Set<Character>[][] rules = new Set[26][26];
    for (String rule : allowed) {
      char[] s = rule.toCharArray();
      addRule(rules, s[0], s[1], s[2]);
    }

    int totalLvl = bottom.length();
    char[][] buf = new char[totalLvl][totalLvl];
    int lastLevel = totalLvl - 1;
    for (int i = 0; i < bottom.length(); i++) {
      buf[lastLevel][i] = bottom.charAt(i);
    }
    return backtrack(rules, buf, lastLevel, 0);
  }

  private boolean backtrack(Set<Character>[][] rules, char[][] buf,
                            int lastLevel, int cur) {
    if (lastLevel == 0) {
      return true;
    }
    if (cur == lastLevel) {
      return backtrack(rules, buf, lastLevel - 1, 0);
    }

    Set<Character> chars = getRules(rules, buf[lastLevel][cur], buf[lastLevel][cur + 1]);
    for (char c : chars) {
      buf[lastLevel - 1][cur] = c;
      if (backtrack(rules, buf, lastLevel, cur + 1)) {
        return true;
      }
    }
    return false;
  }

  private Set<Character> getRules(Set<Character>[][] rules,
                                  char c1, char c2) {
    if (rules[c1 - 'A'][c2 - 'A'] == null) {
      return new HashSet<>();
    }
    return rules[c1 - 'A'][c2 - 'A'];
  }

  private void addRule(Set<Character>[][] rules,
                       char c1, char c2, char c) {
    if (rules[c1 - 'A'][c2 - 'A'] == null) {
      rules[c1 - 'A'][c2 - 'A'] = new HashSet<>();
    }
    rules[c1 - 'A'][c2 - 'A'].add(c);
  }
}
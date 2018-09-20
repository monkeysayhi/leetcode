package com.msh.solutions._127_Word_Ladder;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/26.
 */
public class Solution {
  // 相邻词为边，bfs 层序遍历
  // 暴力建图O(n^2)，遍历O(n)，换个思路O(n)建图
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    // assume valid
    String b = beginWord;
    String e = endWord;
    Set<String> words = new HashSet<>();
    for (String w : wordList) {
      if (!w.equals(b)) {
        words.add(w);
      }
    }
    if (!words.contains(e)) {
      return 0;
    }

    Map<String, Set<String>> g = constructGragh(b, e, words);
    Queue<String> q = new LinkedList<>();
    Set<String> vis = new HashSet<>();
    q.offer(b);
    vis.add(b);
    int level = 1;
    while (!q.isEmpty()) {
      level++;
      int curLevelSize = q.size();
      for (int i = 0; i < curLevelSize; i++) {
        String u = q.poll();
        for (String v : g.get(u)) {
          if (!vis.contains(v)) {
            if (v.equals(e)) {
              return level;
            }
            q.offer(v);
            vis.add(v);
          }
        }
      }
    }
    return 0;
  }

  private Map<String ,Set<String>> constructGragh(String b, String e, Set<String> words) {
    Map<String, Set<String>> g = new HashMap<>();
    Set<String> startWs = new HashSet<>(words);
    startWs.remove(e);
    startWs.add(b);
    Set<String> endWs = new HashSet<>(words);

    for (String u : startWs) {
      g.put(u, new HashSet<>());
      for (int i = 0; i < u.length(); i++) {
        char[] chars = u.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
          chars[i] = ch;
          String v = new String(chars);
          if (endWs.contains(v)) {
            g.get(u).add(v);
          }
        }
      }
    }
    return g;
  }
}
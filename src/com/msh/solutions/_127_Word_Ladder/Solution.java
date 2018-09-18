package com.msh.solutions._127_Word_Ladder;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/26.
 */
public class Solution {
  // 相邻词为边，bfs层序遍历，时间O(n * k)
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> words = new HashSet<>();
    for (String w : wordList) {
      if (!w.equals(beginWord)) {
        words.add(w);
      }
    }
    if (!words.contains(endWord)) {
      return 0;
    }

    Map<String, Set<String>> graph = contructGraph(beginWord, endWord, words);

    Queue<String> q = new LinkedList<>();
    Set<String> vis = new HashSet<>();
    q.add(beginWord);
    int level = 1;
    while (!q.isEmpty()) {
      level++;
      int curLevelSize = q.size();
      for (int i = 0; i < curLevelSize; i++) {
        String u = q.poll();
        vis.add(u);
        for (String v : graph.get(u)) {
          if (vis.contains(v)) {
            continue;
          }
          if (v.equals(endWord)) {
            return level;
          }
          q.offer(v);
        }
      }
    }
    return 0;
  }

  private Map<String, Set<String>> contructGraph(String b, String e, Set<String> words) {
    Map<String, Set<String>> graph = new HashMap<>();

    graph.put(b, new HashSet<>());
    for (String w : words) {
      graph.put(b, new HashSet<>());
    }

    // O(n * k)
    for (String w : words) {
      if (isNeighbor(b, w)) {
        graph.get(b).add(w);
      }
    }

    for (String w : words) {
      graph.put(w, new HashSet<>());
    }
    // TLE, O(n^2 * k)
    // for (String u : words) {
    //     for (String v : words) {
    //         if (isNeighbor(u, v)) {
    //             if (!u.equals(e)) {
    //                 graph.get(u).add(v);
    //             }
    //             graph.get(v).add(u);
    //         }
    //     }
    // }
    // AC, O(n * k * 26)
    for (String u : words) {
      if (u.equals(e)) {
        continue;
      }
      for (int i = 0; i < u.length(); i++) {
        char[] s = u.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
          if (ch == s[i]) {
            continue;
          }
          s[i] = ch;
          String v = new String(s);
          if (words.contains(v)) {
            graph.get(u).add(v);
          }
        }
      }
    }

    return graph;
  }

  private boolean isNeighbor(String s1, String s2) {
    if (s1.equals(s2)) {
      return false;
    }
    int diffCnt = 0;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        diffCnt++;
        if (diffCnt > 1) {
          return false;
        }
      }
    }
    return true;
  }
}
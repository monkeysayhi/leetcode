package com.msh.solutions._127_Word_Ladder;

import java.util.*;

/**
 * Created by monkeysayhi on 2017/12/26.
 */
public class Solution {
  // 相邻词为边，层序BFS，时间O(n^2)
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    String begin = beginWord;
    String end = endWord;
    List<String> words = wordList;
    if (begin == null || end == null || words == null || words.size() == 0) {
      return 0;
    }
    if (!words.contains(end)) {
      return 0;
    }

    Map<String, Set<String>> graph = constructGraph(begin, words);
    return bfsLevelorder(graph, begin, end);
  }

  private int bfsLevelorder(Map<String, Set<String>> graph,
                            String begin, String end) {
    Set<String> vis = new HashSet<>(graph.size());
    Queue<String> que = new LinkedList<>();
    int level = 1;
    que.offer(begin);
    while (!que.isEmpty()) {
      int curLevelSize = que.size();
      level++;
      for (int i = 0; i < curLevelSize; i++) {
        String u = que.poll();
        vis.add(u);
        for (String v : graph.get(u)) {
          if (!vis.contains(v)) {
            if (v.equals(end)) {
              return level;
            }
            que.offer(v);
          }
        }
      }
    }
    System.out.println("2");
    return 0;
  }

  private Map<String, Set<String>> constructGraph(String begin,
                                                  List<String> words) {
    Map<String, Set<String>> graph = new HashMap<>(words.size() + 1);
    for (String word : words) {
      graph.put(word, new HashSet<String>());
    }
    graph.put(begin, new HashSet<String>());

    for (String w1 : words) {
      for (String w2 : words) {
        if (!w1.equals(w2) && isNeighbor(w1, w2)) {
          graph.get(w1).add(w2);
          graph.get(w2).add(w1);
        }
      }
    }
    for (String w2 : words) {
      // 该题允许begin位于words中，但不算作相邻
      if (!begin.equals(w2) && isNeighbor(begin, w2)) {
        graph.get(begin).add(w2);
      }
    }
    return graph;
  }

  private boolean isNeighbor(String w1, String w2) {
    int diffCnt = 0;
    for (int i = 0; i < w1.length(); i++) {
      if (w1.charAt(i) != w2.charAt(i)) {
        diffCnt++;
        if (diffCnt > 1) {
          return false;
        }
      }
    }
    assert diffCnt == 1;
    return true;
  }
}
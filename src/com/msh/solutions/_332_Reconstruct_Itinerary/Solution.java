package com.msh.solutions._332_Reconstruct_Itinerary;

import java.util.*;

/**
 * Created by monkeysayhi on 2018/4/10.
 */
public class Solution {
  // solution 1: 回溯，按照字典序尝试分支，返回第一条能用掉所有边的路线. O(e!)
  // solution 2: 看[题解](https://www.jiuzhang.com/article/axnFcS/)得知欧拉回路有O(e)的解法，但图论内容不要求掌握
  public List<String> findItinerary(String[][] tickets) {
    if (tickets == null
        || tickets.length == 0 || tickets[0].length == 0) {
      return new ArrayList<>();
    }

    Map<String, List<String>> graph = constructGraph(tickets);
    return findItineraryInt(graph, tickets.length, "JFK");
  }

  private Map<String, List<String>> constructGraph(String[][] edges) {
    Map<String, List<String>> graph = new HashMap<>();
    for (String[] edge : edges) {
      String u = edge[0];
      String v = edge[1];
      if (!graph.containsKey(u)) {
        graph.put(u, new ArrayList<>());
      }
      // badcase: 某些节点没有后继节点
      if (!graph.containsKey(v)) {
        graph.put(v, new ArrayList<>());
      }
      graph.get(u).add(v);
    }
    for (String u : graph.keySet()) {
      // lexical order
      Collections.sort(graph.get(u));
    }
    return graph;
  }

  private List<String> findItineraryInt(Map<String, List<String>> graph, int edgeCnt,
                                        String start) {
    // badcase: 重复边
    Map<String, Integer> vis = new HashMap<>();
    for (String u : graph.keySet()) {
      for (String v : graph.get(u)) {
        String edge = edge(u, v);
        Integer vCnt = vis.get(edge);
        if (vCnt == null) {
          vCnt = 0;
          vis.put(edge, vCnt);
        }
        vis.put(edge, vCnt + 1);
      }
    }
    Stack<String> buf = new Stack<>();
    buf.push(start);
    // assert backtrack(graph, vis, edgeCnt, 0, start, buf);
    backtrack(graph, vis, edgeCnt, 0, start, buf);
    return new ArrayList<>(buf);
  }

  private String edge(String u, String v) {
    return u + "->" + v;
  }

  private boolean backtrack(Map<String, List<String>> graph, Map<String, Integer> vis,
                            int edgeCnt,
                            int cur, String u, Stack<String> buf) {
    if (cur == edgeCnt) {
      return true;
    }
    for (String v : graph.get(u)) {
      String edge = edge(u, v);
      int vCnt = vis.get(edge);
      if (vCnt == 0) {
        continue;
      }
      buf.push(v);
      vis.put(edge, vCnt - 1);
      if (backtrack(graph, vis, edgeCnt, cur + 1, v, buf)) {
        return true;
      }
      buf.pop();
      vis.put(edge, vCnt);
    }
    return false;
  }
}
package com.msh.solutions._56_Merge_Intervals;

import java.util.*;

/**
 * Created by monkeysayhi on 2018/1/24.
 */

class Interval {
  int start;
  int end;

  Interval() {
    start = 0;
    end = 0;
  }

  Interval(int s, int e) {
    start = s;
    end = e;
  }
}

public class Solution {
  // 按照intvs[i].start排序，然后扫描一遍
  public List<Interval> merge(List<Interval> intervals) {
    if (intervals == null) {
      return null;
    }
    if (intervals.size() == 0) {
      return new ArrayList<>();
    }

    Stack<Interval> stack = new Stack<>();
    Collections.sort(intervals, new Comparator<Interval>() {
      public int compare(Interval interval1, Interval interval2) {
        return interval1.start - interval2.start;
      }
    });
    stack.push(intervals.get(0));
    for (int i = 1; i < intervals.size(); i++) {
      Interval last = stack.peek();
      Interval cur = intervals.get(i);
      // badcase: 不合并[[1,2],[3,4]]
      if (last.end + 1 > cur.start) {
        // badcase: 合并[[1,4],[2,3]]为[1,4]
        last.end = Math.max(last.end, cur.end);
        continue;
      }
      stack.push(cur);
    }
    return stack;
  }
}
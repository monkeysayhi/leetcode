package com.msh.solutions._57_Insert_Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/4/9.
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
  // 线性扫描一遍，同时合并Interval

  // 简化版，看[题解](https://leetcode.com/problems/insert-interval/discuss/21600/Short-java-code)
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    if (newInterval == null) {
      return new ArrayList<>(intervals);
    }
    if (intervals == null || intervals.size() == 0) {
      return Arrays.asList(newInterval);
    }

    List<Interval> rs = new LinkedList<>();

    boolean merged = false;
    for (Interval interval : intervals) {
      if (newInterval.end < interval.start) {
        rs.add(newInterval);
        merged = true;
        rs.add(interval);
      } else if (newInterval.start > interval.end) {
        rs.add(interval);
      } else {
        newInterval.start = Math.min(newInterval.start, interval.start);
        newInterval.end = Math.max(newInterval.end, interval.end);
      }
    }
    if (!merged) {
      rs.add(newInterval);
    }
    return rs;
  }

//     // 原版，复杂、易错、丑陋
//     public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
//         if (newInterval == null) {
//             return intervals;
//         }
//         if (intervals == null || intervals.size() == 0) {
//             return Arrays.asList(newInterval);
//         }

//         List<Interval> rs = new LinkedList<>();

//         Interval mergedInterval = null;
//         boolean merged = false;
//         for (int i = 0; i < intervals.size(); i++) {
//             Interval interval = intervals.get(i);
//             if (merged) {
//                 rs.add(interval);
//                 continue;
//             }
//             // branch-1
//             if (newInterval.end < interval.start) {
//                 if (mergedInterval != null) {
//                     mergedInterval.end = newInterval.end;
//                     rs.add(mergedInterval);
//                     rs.add(interval);
//                     merged = true;
//                 } else {
//                     mergedInterval = new Interval(newInterval.start, newInterval.end);
//                     rs.add(mergedInterval);
//                     rs.add(interval);
//                     merged = true;
//                 }
//                 continue;
//             }
//             // branch-2
//             if (newInterval.start < interval.start && newInterval.end >= interval.start &&
// newInterval.end <= interval.end) {
//                 if (mergedInterval != null) {
//                     mergedInterval.end = interval.end;
//                     rs.add(mergedInterval);
//                     merged = true;
//                 } else {
//                     mergedInterval = new Interval(newInterval.start, interval.end);
//                     rs.add(mergedInterval);
//                     merged = true;
//                 }
//                 continue;
//             }
//             // branch-3
//             if (newInterval.start <= interval.start && newInterval.end >= interval.end) {
//                 if (mergedInterval != null) {
//                     if (i == intervals.size() - 1) {
//                         mergedInterval.end = newInterval.end;
//                         rs.add(mergedInterval);
//                         merged = true;
//                     } else {
//                         // waiting processed by branch-1 or branch-3
//                     }
//                 } else {
//                     mergedInterval = new Interval(newInterval.start, Integer.MIN_VALUE);
//                     if (i == intervals.size() - 1) {
//                         mergedInterval.end = newInterval.end;
//                         rs.add(mergedInterval);
//                         merged = true;
//                     } else {
//                         // waiting processed by branch-1 or branch-3
//                     }
//                 }
//                 continue;
//             }
//             // branch-4
//             if (newInterval.start >= interval.start && newInterval.end <= interval.end) {
//                 assert mergedInterval == null;
//                 mergedInterval = new Interval(interval.start, interval.end);
//                 rs.add(mergedInterval);
//                 merged = true;
//                 continue;
//             }
//             // branch-5
//             if (newInterval.start >= interval.start && newInterval.start <= interval.end &&
// newInterval.end > interval.end) {
//                 assert mergedInterval == null;
//                 mergedInterval = new Interval(interval.start, Integer.MIN_VALUE);
//                 if (i == intervals.size() - 1) {
//                     mergedInterval.end = newInterval.end;
//                     rs.add(mergedInterval);
//                     merged = true;
//                 } else {
//                     // waiting processed by branch-1 or branch-2
//                 }
//                 continue;
//             }
//             // branch-6
//             if (newInterval.start > interval.end) {
//                 assert mergedInterval == null;
//                 if (i == intervals.size() - 1) {
//                     mergedInterval = new Interval(newInterval.start, newInterval.end);
//                     rs.add(interval);
//                     rs.add(mergedInterval);
//                     merged = true;
//                 } else {
//                     rs.add(interval);
//                     // waiting processed by other branch
//                 }
//                 continue;
//             }
//         }
//         return rs;
//     }
}
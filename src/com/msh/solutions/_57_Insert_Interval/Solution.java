package com.msh.solutions._57_Insert_Interval;

import java.util.ArrayList;
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
  // 简洁，思路不复杂
  // solution 2: 在 intervals 上移动游标时，一定先碰到 newInterval 的左界，再碰到 newInterval 的右界，有相交就合并入 newInterval
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    // assume valid
    List<Interval> rs = new ArrayList<>(intervals.size() + 1);
    if (intervals.size() == 0) {
      rs.add(newInterval);
      return rs;
    }

    boolean merged = false;
    for (Interval itv : intervals) {
      if (itv.end < newInterval.start) {
        rs.add(itv);
      } else if (itv.start > newInterval.end) {
        if (!merged) {
          rs.add(newInterval);
          merged = true;
        }
        rs.add(itv);
      } else { // overlap with left or right
        newInterval.start = Math.min(newInterval.start, itv.start);
        newInterval.end = Math.max(newInterval.end, itv.end);
      }
    }
    if (!merged) {
      rs.add(newInterval);
    }

    return rs;
  }

//     // 稍微麻烦，但思路十分清晰
//     // solution 1: 找到 newInterval 应该插入的位置，[left, right]，合并
//     public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
//         // assume valid
//         List<Interval> rs = new ArrayList<>();
//         int n = intervals.size();
//         if (n == 0) {
//             rs.add(newInterval);
//             return rs;
//         }

//         int left = -1;
//         int right = -1;
//         for (int i = 0; i < n; i++) {
//             Interval itv = intervals.get(i);
//             if (itv.start <= newInterval.start) {
//                 left = i;
//             }
//             if (right == -1 && itv.end >= newInterval.end) {
//                 right = i;
//             }
//         }

//         if (left == right) {
//             if (left == -1) {
//                 rs.add(newInterval);

//             } else {
//                 rs.addAll(intervals);
//             }
//             return rs;
//         }
//         if (left != -1) {
//             for (int i = 0; i < left; i++) {
//                 rs.add(intervals.get(i));
//             }
//             rs.add(intervals.get(left));
//             tryMerge(rs, rs.get(rs.size() - 1), newInterval);
//         } else {
//             rs.add(newInterval);
//         }
//         if (right != -1) {
//             tryMerge(rs, rs.get(rs.size() - 1), intervals.get(right));
//             for (int i = right + 1; i < n; i++) {
//                 rs.add(intervals.get(i));
//             }
//         }

//         return rs;
//     }

//     private void tryMerge(List<Interval> rs, Interval itv1, Interval itv2) {
//         if (itv1.end >= itv2.start) {
//             itv1.start = Math.min(itv1.start, itv2.start);
//             itv1.end = Math.max(itv1.end, itv2.end);
//         } else {
//             rs.add(itv2);
//         }
//     }
}
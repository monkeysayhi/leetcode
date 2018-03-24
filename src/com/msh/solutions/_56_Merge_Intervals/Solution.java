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
//     // solution 1: 按照intvs[i].start排序，然后扫描一遍
//     public List<Interval> merge(List<Interval> intervals) {
//         if (intervals == null) {
//             return null;
//         }
//         if (intervals.size() == 0) {
//             return new ArrayList<>();
//         }

//         Stack<Interval> stack = new Stack<>();
//         Collections.sort(intervals, new Comparator<Interval>() {
//             public int compare(Interval interval1, Interval interval2) {
//                 return interval1.start - interval2.start;
//             }
//         });
//         stack.push(intervals.get(0));
//         for (int i = 1; i < intervals.size(); i++) {
//             Interval last = stack.peek();
//             Interval cur = intervals.get(i);
//             // badcase: 不合并[[1,2],[3,4]]
//             if (last.end + 1 > cur.start) {
//                 // badcase: 合并[[1,4],[2,3]]为[1,4]
//                 last.end = Math.max(last.end, cur.end);
//                 continue;
//             }
//             stack.push(cur);
//         }
//         return stack;
//     }

  private class Index implements Comparable<Index> {
    private boolean isLeftPos;
    private int pos;
    private Index(boolean isLeftPos, int pos) {
      this.isLeftPos = isLeftPos;
      this.pos = pos;
    }

    public int compareTo(Index index) {
      return this.pos - index.pos;
    }
  }

  // solution 2: 将区间左、右pos拆开，按照pos排序；扫描height，遇到左pos就height++，右pos就height--；height>0时的段构成区间
  // 注意兼容区间长度为0的case，如[1,1]
  public List<Interval> merge(List<Interval> intervals) {
    if (intervals == null) {
      return null;
    }
    if (intervals.size() == 0) {
      return new ArrayList<>();
    }

    int n = intervals.size();
    List<Index> indexes = new ArrayList<>(n * 2);
    for (Interval interval : intervals) {
      indexes.add(new Index(true, interval.start));
      indexes.add(new Index(false, interval.end));
    }
    Collections.sort(indexes);

    List<Interval> rs = new ArrayList<>();
    int i = 0;
    int height = 0;
    int lastStart = -1;
    int lastEnd = -1;
    while (i < n * 2) {
      int lastHeight = height;
      int curPos = indexes.get(i).pos;
      while (i < n * 2 && indexes.get(i).pos == curPos) {
        height += indexes.get(i).isLeftPos ? 1 : -1;
        i++;
      }
      assert height >= 0;
      if (lastHeight == 0 && height > 0) {
        lastStart = curPos;
      } else if (lastHeight > 0 && height == 0) {
        lastEnd = curPos;
        rs.add(new Interval(lastStart, lastEnd));
      } else if (lastHeight == 0 && height == 0) {
        rs.add(new Interval(curPos, curPos));
      }
    }
    return rs;
  }
}
package com.msh.solutions._264_Ugly_Number_2;

/**
 * Created by monkeysayhi on 2018/1/8.
 */
public class Solution {
  // solution 1: dij 最短路，依赖最小堆，O(nlgn)；看范围有可能注意溢出
  // solution 2: 看题解，记录最大丑数和各丑因子的追及状态，O(n)

  // solution 2
  public int nthUglyNumber(int n) {
    if (n == 1) {
      return 1;
    }

    int[] ps = new int[]{2, 3, 5};
    int[] maxFactors = new int[]{0, 0, 0};
    int[] uglyNums = new int[n];
    uglyNums[0] = 1;
    for (int i = 1; i < n; i++) {
      int lastMaxUglyNum = uglyNums[i - 1];
      for (int j = 0; j < ps.length; j++) {
        // assume not overflow
        while (uglyNums[maxFactors[j]] * ps[j] <= lastMaxUglyNum) {
          maxFactors[j]++;
        }
      }
      uglyNums[i] = Integer.MAX_VALUE;
      for (int j = 0; j < ps.length; j++) {
        uglyNums[i] = Math.min(uglyNums[i], uglyNums[maxFactors[j]] * ps[j]);
      }
    }
    return uglyNums[n - 1];
  }

//     // solution 1
//     public int nthUglyNumber(int n) {
//         // assume valid
//         if (n == 1) {
//             return 1;
//         }

//         int[] ps = new int[]{2, 3, 5};
//         PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//         minHeap.offer(1);
//         Set<Integer> vis = new HashSet<>();
//         vis.add(1);
//         for (int i = 1; i < n; i++) {
//             int u = minHeap.poll();
//             for (int p : ps) {
//                 if (u > Integer.MAX_VALUE / p) {
//                     continue;
//                 }
//                 int v = u * p;
//                 if (!vis.contains(v)) {
//                     minHeap.offer(v);
//                     vis.add(v);
//                 }
//             }
//         }
//         return minHeap.poll();
//     }
}
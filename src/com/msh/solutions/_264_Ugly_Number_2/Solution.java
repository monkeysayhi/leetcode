package com.msh.solutions._264_Ugly_Number_2;

/**
 * Created by monkeysayhi on 2018/1/8.
 */
public class Solution {
  // solution 2: 记录最大丑数和各素因子的追及状态，O(n)
  public int nthUglyNumber(int n) {
    assert n >= 1;
    if (n == 1) {
      return 1;
    }

    int[] primes = new int[]{2, 3, 5};

    int[] nextUglyNumFactors = new int[primes.length];
    int[] uglyNums = new int[n];
    uglyNums[0] = 1;
    for (int i = 1; i < n; i++) {
      int curMaxUglyNum = uglyNums[i - 1];
      for (int j = 0; j < primes.length; j++) {
        while (uglyNums[nextUglyNumFactors[j]] * primes[j] <= curMaxUglyNum) {
          nextUglyNumFactors[j]++;
        }
      }
      int nextMaxUglyNum = Integer.MAX_VALUE;
      for (int j = 0; j < primes.length; j++) {
        if (uglyNums[nextUglyNumFactors[j]] * primes[j] < nextMaxUglyNum) {
          nextMaxUglyNum = uglyNums[nextUglyNumFactors[j]] * primes[j];
        }
      }
      uglyNums[i] = nextMaxUglyNum;
    }
    return uglyNums[n - 1];
  }

//     // solution 1: 最小堆实现 dij 最短路，O(nlogn)
//     public int nthUglyNumber(int n) {
//         assert n >= 1;
//         if (n == 1) {
//             return 1;
//         }

//         PriorityQueue<Long> minHeap = new PriorityQueue<>(n);
//         Set<Long> set = new HashSet<>(n);
//         minHeap.offer(1L);
//         set.add(1L);
//         int[] primes = new int[]{2, 3, 5};
//         for (; n > 1; n--) {
//             long min = minHeap.poll();
//             for (int i = 0; i < primes.length; i++) {
//                 long uglyNum = min * primes[i];
//                 if (!set.contains(uglyNum)) {
//                     minHeap.offer(uglyNum);
//                     set.add(uglyNum);
//                 }
//             }
//         }
//         return minHeap.poll().intValue();
//     }
}
package com.msh.solutions._373_Find_K_Pairs_with_Smallest_Sums;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by monkeysayhi on 2018/4/11.
 */
public class Solution {

  private static class Pair implements Comparable<Pair> {
    private int num1;
    private int num2;
    private int idx1;
    private int idx2;

    private Pair(int num1, int num2, int idx1, int idx2) {
      this.num1 = num1;
      this.num2 = num2;
      this.idx1 = idx1;
      this.idx2 = idx2;
    }

    public int compareTo(Pair pair) {
      return (this.num1 + this.num2) - (pair.num1 + pair.num2);
    }
  }

  // solution 1: 暴力，枚举(i, j)，排序，选出最小的前k个pair. O(n^2)
  // solution -2: 同时遍历nums1、nums2，枚举(i, j)，curMin = min{nums1[i] * nums2[j], nums1[l1] * nums[j], nums1[i] * nums2[l2]}，记录前k个pair. O(k). WA
  // solution 2: 看[题解](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/122343/minheap-c++-12ms-beats-99)，mmp. O(klogn)
  public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    if (nums1 == null || nums2 == null
        || nums1.length == 0  || nums2.length == 0) {
      return new ArrayList<>();
    }
    if (k == 0) {
      return new ArrayList<>();
    }
    k = Math.min(k, nums1.length * nums2.length);

    List<int[]> rs = new ArrayList<>();
    PriorityQueue<Pair> minHeap = new PriorityQueue<>();
    for (int j = 0; j < nums2.length; j++) {
      minHeap.offer(new Pair(nums1[0], nums2[j], 0, j));
    }
    while (k-- > 0) {
      Pair pair = minHeap.poll();
      rs.add(new int[]{pair.num1, pair.num2});
      if (pair.idx1 < nums1.length - 1) {
        int i = pair.idx1 + 1;
        int j = pair.idx2;
        minHeap.offer(new Pair(nums1[i], nums2[j], i, j));
      }
    }
    return rs;
  }
}
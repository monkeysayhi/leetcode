package com.msh.solutions._454_4Sum_2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by monkeysayhi on 2018/1/30.
 */
public class Solution {
  // 暴力：在四个list上枚举，O(n^4)
  // 些微优化：在三个list上枚举，二分查找另一个list
  // 继续优化：在各两个list上枚举预先计算出各种和的方案数，然后求 sum{A X B} + sum{C X D} = 0 的方案数，O(n^2)
  // 整体的优化思路是将子问题平均摊分开
  // 除此之外，本身使用 Java HashMap 的实现也有成本，可以去掉一个map，直接在枚举C、D的时候统计数量
  public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    if (A == null || B == null || C == null || D == null
        || A.length == 0) {
      return 0;
    }

    Map<Integer, Integer> stat1 = statFreq(A, B);
    Map<Integer, Integer> stat2 = statFreq(C, D);
    int cnt = 0;
    for (int sum1 : stat1.keySet()) {
      if (stat2.containsKey(-sum1)) {
        cnt += stat1.get(sum1) * stat2.get(-sum1);
      }
    }
    return cnt;
  }

  private Map<Integer, Integer> statFreq(int[] nums1, int[] nums2) {
    Map<Integer, Integer> stat = new HashMap<>();
    for (int n1 : nums1) {
      for (int n2 : nums2) {
        int sum = n1 + n2;
        if (!stat.containsKey(sum)) {
          stat.put(sum, 0);
        }
        stat.put(sum, stat.get(sum) + 1);
      }
    }
    return stat;
  }
}
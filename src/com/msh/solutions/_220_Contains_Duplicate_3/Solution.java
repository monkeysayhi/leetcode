package com.msh.solutions._220_Contains_Duplicate_3;

import java.util.TreeSet;

/**
 * Created by monkeysayhi on 2018/4/8.
 */
class Solution {
  // solution 1: 枚举(i, j)，判断|nums[i] - nums[j]|. O(n * k)
  // solution 2: 排序，枚举(nums[i], nums[j])，判断|i - j|. O(n * t) + O(nlogn)
  // 前两种solution能找到所有方案，但这里指要求判断能否，一般可放宽寻找的目标，来减少搜索范围
  // solution 3: 维护不大于k的滑窗，如果滑窗内元素的差值都大于t（称为“不包含”）就j++，如果窗口大小达到k，就先i++，再检查窗口。[i...j-1]窗口内的元素必然不包含，因此只需要扫描一遍检查nums[j]与[i...j-1]内的元素的差值是否不大于t，就完成了新窗口[i...j]的检查。总O(n * k)
  // solution 4: 整体思路相同，但用TreeSet维护窗口（窗口内元素必然不重复，否则存在差值0），这样维护和检查窗口的时间都是O(logk)，总O(n * logk)
  // solution 5: 看[题解](https://leetcode.com/problems/contains-duplicate-iii/discuss/61645/AC-O(N)-solution-in-Java-using-buckets-with-explanation). 按照 (t + 1) 将窗口内的值分到多个桶中，在得到解之前，每个桶最多有一个值（否则，桶内存在解），则，对于新值nums[j]对应的桶bucket而言，差值不大于t的nums[i']一定位于bucket、bucket + 1、bucket - 1三者之一。画一下过程就明白了. O(n)

  // solution 4
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    // assume valid
    if (nums == null || nums.length < 2) {
      return false;
    }
    TreeSet<Long> wnd = new TreeSet<>();
    wnd.add(Long.valueOf(nums[0]));
    int i = 0;
    int j = 1;
    for (; j < nums.length; j++) {
      if (j - i == k + 1) {
        wnd.remove(Long.valueOf(nums[i]));
        i++;
      }
      long nJ = Long.valueOf(nums[j]);
      Long lowerBound = wnd.ceiling(nJ - t);
      Long upperBound = wnd.floor(nJ + t);
      if (lowerBound != null && Math.abs(nJ - lowerBound) <= t
          || upperBound != null && Math.abs(nJ - upperBound) <= t) {
        return true;
      }
      wnd.add(nJ);
    }
    return false;
  }

  // // solution 3
  // public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
  //     // assume valid
  //     if (nums == null || nums.length < 2) {
  //         return false;
  //     }
  //     int i = 0;
  //     int j = 1;
  //     for (; j < nums.length; j++) {
  //         if (j - i == k + 1) {
  //             i++;
  //         }
  //         for (int i1 = i; i1 < j; i1++) {
  //             if (Math.abs((long) nums[i1] - nums[j]) <= t) {
  //                 return true;
  //             }
  //         }
  //     }
  //     return false;
  // }

//     // solution 5
//     public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//         if (nums == null || nums.length < 2) {
//             return false;
//         }
//         if (k <= 0 || t < 0) {
//             return false;
//         }

//         // 注意溢出的case，不要脸
//         long tL = t;
//         Map<Long, Integer> buckets = new HashMap<>();
//         for (int i = 0; i < nums.length; i++) {
//             if (i >= k + 1) {
//                 long toRemoved = bucketNo(nums[i - k - 1], tL + 1);
//                 buckets.remove(toRemoved);
//             }
//             long toAdded = bucketNo(nums[i], tL + 1);
//             if (buckets.containsKey(toAdded)) {
//                 return true;
//             }
//             if (buckets.containsKey(toAdded + 1)
//                 && Math.abs((long) buckets.get(toAdded + 1) - nums[i]) <= tL) {
//                 return true;
//             }
//             if (buckets.containsKey(toAdded - 1)
//                 && Math.abs((long) buckets.get(toAdded - 1) - nums[i]) <= tL) {
//                 return true;
//             }
//             buckets.put(toAdded, nums[i]);
//         }
//         return false;
//     }
}
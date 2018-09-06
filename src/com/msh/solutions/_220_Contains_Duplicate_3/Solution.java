package com.msh.solutions._220_Contains_Duplicate_3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by monkeysayhi on 2018/4/8.
 */
public class Solution {
  // solution 1: 枚举(i, j)，判断|nums[i] - nums[j]|. O(n * k)
  // solution 2: 排序，枚举(nums[i], nums[j])，判断|i - j|. O(n * t) + O(nlogn)
  // 前两种solution能找到所有方案，但这里指要求判断能否，一般可放宽寻找的目标，来减少搜索范围
  // solution 3: 看[题解](https://leetcode.com/problems/contains-duplicate-iii/discuss/61641/C++-using-set-(less-10-lines)-with-simple-explanation.). 使用 TreeMap 维护一个大小不超过 k 、元素值的差小于 t 的有序窗口（并统计次数），每次加入新元素时 nums[j]，检查窗口中是否存在元素 nums[x] ，满足 nums[j] - t <= nums[x] <= nums[j] + t（实际上，可以用 TreeSet ，因为窗口内必然不存在相等的元素，否则窗口内部就找到了目标 pair），存在即解. O(nlogk)
  // solution 4: 看[题解](https://leetcode.com/problems/contains-duplicate-iii/discuss/61645/AC-O(N)-solution-in-Java-using-buckets-with-explanation). 按照 (t + 1) 将窗口内的值分到多个桶中，则对于新值nums[j]对应的桶bucket而言，差值不大于t的nums[i']一定位于bucket、bucket + 1、bucket - 1三者之一。画一下过程就明白了。

  // solution 4
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (nums == null || nums.length < 2) {
      return false;
    }
    if (k <= 0 || t < 0) {
      return false;
    }

    // 注意溢出的case，不要脸
    long tL = t;
    Map<Long, Integer> buckets = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (i >= k + 1) {
        long toRemoved = bucketNo(nums[i - k - 1], tL + 1);
        buckets.remove(toRemoved);
      }
      long toAdded = bucketNo(nums[i], tL + 1);
      if (buckets.containsKey(toAdded)) {
        return true;
      }
      if (buckets.containsKey(toAdded + 1)
          && Math.abs((long) buckets.get(toAdded + 1) - nums[i]) <= tL) {
        return true;
      }
      if (buckets.containsKey(toAdded - 1)
          && Math.abs((long) buckets.get(toAdded - 1) - nums[i]) <= tL) {
        return true;
      }
      buckets.put(toAdded, nums[i]);
    }
    return false;
  }

  private long bucketNo(int num, long bucketCnt) {
    assert bucketCnt > 0L;
    return ((long) num + Integer.MIN_VALUE + 1L) / bucketCnt;
  }

//     // solution 3
//     public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//         if (nums == null || nums.length < 2) {
//             return false;
//         }
//         if (k <= 0 || t < 0) {
//             return false;
//         }

//         // 注意溢出的case，不要脸
//         long tL = t;
//         TreeSet<Long> set = new TreeSet<>();
//         int n = nums.length;
//         set.add(Long.valueOf(nums[0]));
//         int i = 0;
//         int j = 1;
//         while (j < n) {
//             if (set.size() == k + 1) {
//                 set.remove(Long.valueOf(nums[i]));
//                 i++;
//             }
//             Long upperBound = set.floor(nums[j] + tL);
//             Long lowerBound = set.ceiling(nums[j] - tL);
//             if ((upperBound != null && Math.abs(upperBound - nums[j]) <= tL)
//                 || (lowerBound != null && Math.abs(lowerBound - nums[j]) <= tL)) {
//                 return true;
//             }
//             set.add(Long.valueOf(nums[j]));
//             j++;
//         }
//         return false;
//     }
}
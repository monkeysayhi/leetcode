package com.msh.solutions._128_Longest_Consecutive_Sequence;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by monkeysayhi on 2018/1/29.
 */
public class Solution {
  // 暴力：直接用set记录所有元素，然后从最小到最大扫一遍
  // 但如果max - min + 1 过大（如大于2亿），还是会超时
  // 参考题解学到一个好思路——参考可重集中的选举方法，遍历set选举出序列的最小元素，作为“最长序列的扫描起点”，这样最长 2 * n的时间就能扫描完，拜托了元素值的关系
  public int longestConsecutive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (int num : nums) {
      max = Math.max(max, num);
      min = Math.min(min, num);
    }
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }

    int maxLen = 0;
    for (int num : set) {
      if (num != min && set.contains(num - 1)) {
        continue;
      }
      int len = 1;
      while (set.contains(num + len)) {
        len++;
      }
      maxLen = Math.max(maxLen, len);
    }
    return maxLen;
  }

//     // 先统计各元素出现次数，再扫一遍统计最长连续序列
//     // RE：max - min + 1 超过数组的最大长度
//     // 改造之后也得TLE：可改造为一个正数数组，一个负数数组，最后conquer一下。但如果两个数组都过长（如2亿），仍然会TLE
//     public int longestConsecutive(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }

//         int max = Integer.MIN_VALUE;
//         int min = Integer.MAX_VALUE;
//         for (int num : nums) {
//             max = Math.max(max, num);
//             min = Math.min(min, num);
//         }
//         int[] stat = new int[max - min + 1];
//         for (int num : nums) {
//             stat[num - min]++;
//         }

//         int maxLen = 0;
//         int i = 0;
//         while (i < stat.length) {
//             int len = 0;
//             while (i + len < stat.length && stat[i + len] > 0) {
//                 len++;
//             }
//             maxLen = Math.max(maxLen, len);
//             i += len + 1;
//         }
//         return maxLen;
//     }
}
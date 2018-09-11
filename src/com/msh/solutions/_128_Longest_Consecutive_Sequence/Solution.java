package com.msh.solutions._128_Longest_Consecutive_Sequence;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by monkeysayhi on 2018/1/29.
 */
public class Solution {
  // solution -1: 暴力，直接用set记录所有元素，然后从min到max扫一遍。但如果max - min + 1 过大（如大于2亿），还是会超时
  // solution -2: 排序，顺序遍历，O(nlogn)
  // solution 1: 最优化，求最长，记忆化搜索，设 dp[i] 为“以元素 i 为最大元素的最长升序序列”，则 dp[i] = 1 + max{0, dp[i - 1] | i - 1 存在}，O(n)
  // solution 2: 参考题解学到一个好思路——参考可重集中的选举方法，遍历set选举出序列的最小元素，作为“最长序列的扫描起点”，这样最长 2 * n的时间就能扫描完，摆脱了元素值的关系

  // solution 1
  public int longestConsecutive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return 1;
    }

    int n = nums.length;
    Map<Integer, Integer> dp = new HashMap<>();
    for (int i = 0; i < n; i++) {
      dp.put(nums[i], 0);
    }

    int maxLen = 1;
    for (int i = 0; i < n; i++) {
      maxLen = Math.max(maxLen, lcs(nums[i], dp));
    }

    return maxLen;
  }

  private int lcs(int num, Map<Integer, Integer> dp) {
    assert dp.containsKey(num);
    if (dp.get(num) != 0) {
      return dp.get(num);
    }

    if (num == Integer.MIN_VALUE) {
      dp.put(num, 1);
    } else if (!dp.containsKey(num - 1)) {
      dp.put(num, 1);
    } else {
      dp.put(num, 1 + lcs(num - 1, dp));
    }
    return dp.get(num);
  }

//     // solution 2
//         public int longestConsecutive(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }

//         int max = Integer.MIN_VALUE;
//         int min = Integer.MAX_VALUE;
//         for (int num : nums) {
//             max = Math.max(max, num);
//             min = Math.min(min, num);
//         }
//         Set<Integer> set = new HashSet<>();
//         for (int num : nums) {
//             set.add(num);
//         }

//         int maxLen = 0;
//         for (int num : set) {
//             if (num != min && set.contains(num - 1)) {
//                 continue;
//             }
//             int len = 1;
//             while (set.contains(num + len)) {
//                 len++;
//             }
//             maxLen = Math.max(maxLen, len);
//         }
//         return maxLen;
//     }
}
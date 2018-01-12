package com.msh.solutions._300_Longest_Increasing_Subsequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/1/12.
 */
public class Solution {
  // follow up，神，时间O(nlogn)：暂时不要求掌握，详见[76. Longest Increasing Subsequence](http://www.lintcode.com/en/problem/longest-increasing-subsequence/)
  // 定义 minEndElems[i] 长度为 i 的 LIS 的中的最小末尾元素
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int n = nums.length;
    List<Integer> minEndElems = new ArrayList<>(n);
    minEndElems.add(nums[0]);
    for (int i = 1; i < n; i++) {
      // find post of the first minEndElem gt nums[i], replace it with nums[i] because
      // there must exist a LIS ending with smaller(than minEndElem) elem nums[i]

      // badcase for bsearchU: 1 1 1 1 1
      int pos = bsearchL(minEndElems, nums[i]);
      if (pos > minEndElems.size() - 1) {
        minEndElems.add(nums[i]);
      } else {
        minEndElems.set(pos, nums[i]);
      }
    }

    return minEndElems.size();
  }

  private int bsearchL(List<Integer> nums, int v) {
    int l = 0;
    int r = nums.size();
    while (l < r) {
      int m = l + (r - l) / 2;
      if (nums.get(m) >= v) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return l;
  }

//     // basic，动规，时间O(n^2)：dp[i]为以nums[i]结尾的LIS的长度，dp[i] = max{1, 1 + dp[j] | 1 <= j < i && nums[j] <= nums[i]}
//     public int lengthOfLIS(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
//         if (nums.length == 1) {
//             return 1;
//         }

//         int n = nums.length;
//         int[] dp = new int[n];
//         dp[0] = 1;
//         for (int i = 1; i < n; i++) {
//             dp[i] = 1;
//             for (int j = 0; j < i; j++) {
//                 // 隐含表示LIS中相邻元素不可相等
//                 if (nums[j] < nums[i]) {
//                     dp[i] = Math.max(dp[i], dp[j] + 1);
//                 }
//             }
//         }

//         int max = dp[0];
//         for (int i = 1; i < n; i++) {
//             max = Math.max(max, dp[i]);
//         }
//         return max;
//     }
}

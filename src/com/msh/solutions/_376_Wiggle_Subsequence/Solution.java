package com.msh.solutions._376_Wiggle_Subsequence;

/**
 * Created by monkeysayhi on 2018/3/23.
 */
public class Solution {
  // solution 2: 对任意i < j < k，如果存在num[j] < nums[i] && nums[j] < nums[k]，则一定满足min{num(i...k)} < nums[i] && min{num(i...k)} < nums[k]，大于号同理
  // 也就是数波峰波谷，O(n)
  public int wiggleMaxLength(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return 1;
    }

    int n = nums.length;

    int prev = -1;
    int cur = 0;
    while (cur + 1 < n && nums[cur + 1] == nums[cur]) {
      cur++;
    }
    if (cur == n - 1) {
      return 1;
    }

    int peekCnt = 0;
    int troughCnt = 0;
    while (cur < n) {
      int next = cur + 1;
      while (next + 1 < n && nums[next + 1] == nums[next]) {
        next++;
      }
      if ((prev < 0 && next <= n - 1 && nums[cur] > nums[next])
          || (prev >= 0 && next > n - 1 && nums[prev] < nums[cur])
          || (prev >= 0 && next <= n - 1 && nums[prev] < nums[cur] && nums[cur] > nums[next])) {
        peekCnt++;
      }
      if ((prev < 0 && next <= n - 1 && nums[cur] < nums[next])
          || (prev >= 0 && next > n - 1 && nums[prev] > nums[cur])
          || (prev >= 0 && next <= n - 1 && nums[prev] > nums[cur] && nums[cur] < nums[next])) {
        troughCnt++;
      }
      prev = cur;
      cur = next;
    }
    return peekCnt + troughCnt;
  }

//     // solution 1: 求最值，最大，设，up[i]为以nums[i]上升结尾的最长序列长度，down[i]为以nums[i]下降结尾的最长序列长度
//     // 则，up[i] = max{1, 1 + down[j] | nums[j] < nums[i]}，down[i] = max{1, 1 + up[j] | nums[j] > nums[i]}
//     // 状态数O(n)，决策时间O(1)，总时间O(n^2)
//     public int wiggleMaxLength(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
//         if (nums.length == 1) {
//             return 1;
//         }

//         int n = nums.length;
//         int[] up = new int[n];
//         int[] down = new int[n];
//         up[0] = 1;
//         down[0] = 1;
//         int max = 1;
//         for (int i = 1; i < n; i++) {
//             up[i] = 1;
//             down[i] = 1;
//             for (int j = 0; j < i; j++) {
//                 if (nums[j] < nums[i]) {
//                     up[i] = Math.max(up[i], 1 + down[j]);
//                 }
//                 if (nums[j] > nums[i]) {
//                     down[i] = Math.max(down[i], 1 + up[j]);
//                 }
//             }
//             max = Math.max(max, up[i]);
//             max = Math.max(max, down[i]);
//         }
//         return max;
//     }
}
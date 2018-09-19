package com.msh.solutions._376_Wiggle_Subsequence;

/**
 * Created by monkeysayhi on 2018/3/23.
 */
public class Solution {
  // solution 1: 最优化，求最长
  // 设 up[i] 为“截止为位置i，末尾为上升趋势的最长序列长度”，同理设 down[i]，则：
  // 1. up[i] = max{down[j] + 1 || nums[j] < nums[i]}
  // 2. down[i] = max{up[j] + 1 || nums[j] > nums[i]}
  // 状态数 O(n)，决策时间数 O(n)，总时间 O(n^2)
  // solution 2: 看题解。对任意i < j < k，如果存在num[j] < nums[i] && nums[j] < nums[k]，则一定满足min{num(i...k)} < nums[i] && min{num(i...k)} < nums[k]，大于号同理。也就是数波峰波谷，O(n)

  // solution 2
  public int wiggleMaxLength(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return 1;
    }

    int n = nums.length;
    int[] neNums = new int[n];
    int m = 0;
    for (int i = 0; i < n; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      neNums[m] = nums[i];
      m++;
    }
    if (m == 1) {
      return 1;
    }

    int peekCnt = 0;
    int troughCnt = 0;
    for (int i = 0; i < m; i++) {
      if (i == 0) {
        peekCnt += neNums[i] > neNums[i + 1] ? 1 : 0;
        troughCnt += neNums[i] < neNums[i + 1] ? 1 : 0;
      } else if (i == m - 1) {
        peekCnt += neNums[i - 1] > neNums[i] ? 1 : 0;
        troughCnt += neNums[i - 1] < neNums[i] ? 1 : 0;
      } else {
        peekCnt += neNums[i - 1] < neNums[i] && neNums[i] > neNums[i + 1] ? 1 : 0;
        troughCnt += neNums[i - 1] > neNums[i] && neNums[i] < neNums[i + 1] ? 1 : 0;
      }
    }
    return peekCnt + troughCnt;
  }

  // // solution 1
  // public int wiggleMaxLength(int[] nums) {
  //     if (nums == null || nums.length == 0) {
  //         return 0;
  //     }
  //     int n = nums.length;
  //     int[] up = new int[n];
  //     int[] down = new int[n];
  //     up[0] = 1;
  //     down[0] = 1;
  //     int maxLen = Math.max(up[0], down[0]);
  //     for (int i = 1; i < n; i++) {
  //         up[i] = 1;
  //         down[i] = 1;
  //         for (int j = i - 1; j >= 0; j--) {
  //             if (nums[j] > nums[i]) {
  //                 down[i] = Math.max(down[i], up[j] + 1);
  //             } if (nums[j] < nums[i]) {
  //                 up[i] = Math.max(up[i], down[j] + 1);
  //             }
  //         }
  //         maxLen = Math.max(maxLen, up[i]);
  //         maxLen = Math.max(maxLen, down[i]);
  //     }
  //     return maxLen;
  // }
}
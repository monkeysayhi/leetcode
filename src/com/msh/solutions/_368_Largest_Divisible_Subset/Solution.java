package com.msh.solutions._368_Largest_Divisible_Subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/4/11.
 */
public class Solution {
  // solution 1: 暴力，枚举所有子集，然后检测子集中的元素是否可除. O(n^2 * n!)
  // solution 2: 仔细观察，发现最大集中的元素对 (lager, smaller) 一定满足 `lager == k * smaller`，且符合传递律。排序后枚举最小者，然后观察其k倍是否存在；选出这些k倍元素，依次从中选取最小者，将非k倍元素过滤掉；直至遍历完最小元素. O(nlogn + n^(n + n ^ n)) = O(n^3)
  // solution 3: 最优化，求最大，先排逆序，设 dp[i] 为“以nums[i]为最小底最大集的大小”的，则 dp[i] = max{1 + dp[j] | 0 <= j < i && nums[i] = k * nums[j]}. O(n^2)
  public List<Integer> largestDivisibleSubset(int[] nums) {
    if (nums == null || nums.length < 1) {
      return new ArrayList<>();
    }

    Arrays.sort(nums);
    int n = nums.length;
    for (int i = 0; i < n / 2; i++) {
      int tmp = nums[i];
      nums[i] = nums[n - i - 1];
      nums[n - i - 1] = tmp;
    }

    int[] dp = new int[n];
    int[] lastIdxs = new int[n];
    dp[0] = 1;
    lastIdxs[0] = -1;
    for (int i = 1; i < n; i++) {
      dp[i] = 1;
      lastIdxs[i] = -1;
      for (int j = 0; j < i; j++) {
        if (nums[j] % nums[i] > 0) {
          continue;
        }
        if (dp[i] < 1 + dp[j]) {
          dp[i] = 1 + dp[j];
          lastIdxs[i] = j;
        }
      }
    }

    int maxIdx = 0;
    for (int i = 1; i < n; i++) {
      if (dp[maxIdx] < dp[i]) {
        maxIdx = i;
      }
    }
    List<Integer> rs = new ArrayList<>(n);
    int idx = maxIdx;
    while (true) {
      rs.add(nums[idx]);
      idx = lastIdxs[idx];
      if (idx == -1) {
        break;
      }
    }
    return rs;
  }
}
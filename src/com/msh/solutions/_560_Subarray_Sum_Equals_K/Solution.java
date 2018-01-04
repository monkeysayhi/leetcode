package com.msh.solutions._560_Subarray_Sum_Equals_K;

/**
 * Created by monkeysayhi on 2018/1/4.
 */
public class Solution {
  // 记录截止到每一位的sum的频数，当遇到新的一位sums[i]时，判断数组sums[...i - 1]中sums[i] - k出现的次数
  // 利用了sum生成的顺序，使得找到的sums[j] = sums[i] - k, j 一定小于 i
  public int subarraySum(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0] == k ? 1 : 0;
    }

    Map<Integer, Integer> stat = new HashMap<>(nums.length);
    stat.put(0, 1);
    int sum = 0;
    int cnt = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (stat.containsKey(sum - k)) {
        cnt += stat.get(sum - k);
      }
      if (!stat.containsKey(sum)) {
        stat.put(sum, 0);
      }
      stat.put(sum, 1 + stat.get(sum));
    }
    return cnt;
  }
}

package com.msh.solutions._560_Subarray_Sum_Equals_K;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by monkeysayhi on 2018/1/4.
 */
public class Solution {
  // solution 1: 暴力：枚举i,j，计算子数组和，O(n^3)
  // solution 2: 先计算preSum，再枚举i,j直接判断，O(n^2)
  // solution 3: 先计算preSum，记录preSum位置，按照preSum排序，然后单头双指针，O(nlogn)
  // solution 4: 继续优化：先计算preSum，然后统计preSum出现的索引位置，再枚举preSum[i]，并查找其后是否存在preSum[j] == preSum[i] + k；可边枚举边倒序统计，这样直接判断索引位数即可；实际上不需要索引位置，知道数量即可，那么可改为倒序统计频数。还可以枚举preSum[j]，查找其前是否存在preSum[i] == preSum[j] - k，这样正序枚举正序统计即可。 O(n)
  // 下面是正序统计的实现
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

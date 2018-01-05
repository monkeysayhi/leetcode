package com.msh.solutions._561_Array_Partition_1;

import java.util.Arrays;

/**
 * Created by monkeysayhi on 2018/1/5.
 */
public class Solution {
  // 公式
  // sum(sorted(nums)[::2])
  public int arrayPairSum(int[] nums) {
    // no edge
    Arrays.sort(nums);
    int minSum = 0;
    for (int i = 0; i < nums.length; i += 2) {
      minSum += nums[i];
    }
    return minSum;
  }
}
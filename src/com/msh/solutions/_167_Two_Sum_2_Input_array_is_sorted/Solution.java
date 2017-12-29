package com.msh.solutions._167_Two_Sum_2_Input_array_is_sorted;

/**
 * Created by monkeysayhi on 2017/12/29.
 */
public class Solution {
  // 两头收缩
  public int[] twoSum(int[] numbers, int target) {
    int l = 0;
    int r = numbers.length - 1;
    while (l < r) {
      int sum = numbers[l] + numbers[r];
      if (sum < target) {
        l++;
      } else if (sum > target) {
        r--;
      } else {
        return new int[]{l + 1, r + 1};
      }
    }
    return null;
  }
}

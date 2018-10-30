package com.msh.solutions._41_First_Missing_Positive;

/**
 * Created by monkeysayhi on 2018/2/4.
 */
public class Solution {
  // 硬想还是想不到。切记不要影响，耐心针对暴力方法中的重复子问题分析
  // solution -1: 暴力，使用集合维护正数元素，最后扫一遍nums，对于每一个正数元素num，都检查num - 1是否存在，维护最小的不存在正数，如果扫描完还是不存在，则返回 n + 1
  // 要求常量空间，则可考虑如何利用输入数组维护状态
  // 分析暴力方法的时候得知，答案的范围为 [1...n + 1] （可反证）。看起来用输入数组加一个变量就够了
  // solution 1: 利用输入数组的索引 i 和值 v，扫描输入数组，将元素值为 v 的元素 nums[i] 交换到 nums[v - 1]。显然，只需要交换值在 [1...n] 内的元素。最后扫描一遍，找到第一个不满足 nums[i] == i + 1 的索引 ans，返回 ans + 1，如果找不到就返回 n + 1
  public int firstMissingPositive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 1;
    }
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      while (nums[i] >= 1 && nums[i] <= n && nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
        swap (nums, i, nums[i] - 1);
      }
    }
    for (int i = 0; i < n; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return n + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }
}
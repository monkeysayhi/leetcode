package com.msh.solutions._18_4Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/3/30.
 */
public class Solution {
  // solution 1: 暴力枚举<i, j, k, l>，判断sum是否等于target。O(n^4)
  // solution 2: 暴力枚举<i, j, k>，在剩余子数组中二分搜索`target - sum`。O(n^3logn)
  // solution 3: 暴力枚举<i, j>，在剩余子数组中做`2Sum(target - sum)`。O(n^3)
  // solution 4: 先求出`Map<2sum, List<Elem(i, j)>>`，O(n^2)，显然List是按照<i, j>排序的；然后暴力枚举<i, j>，在Map中查找`target  - sum`对应的有序List，在List中二分查找所有大于i的pair。O(n^2logn)
  // maybe solution 5: 如果能在O(n^2)的时间构造一种类似`Map<2sum, List<Elem(i, j)>>`的数据结构，使的查找时间从O(logn)降到O(1)，就能将总时间优化到O(n^2)

  // 下面采用 solution 3
  public List<List<Integer>> fourSum(int[] nums, int target) {
    if (nums == null || nums.length < 4) {
      return new ArrayList<>();
    }

    Arrays.sort(nums);
    List<List<Integer>> rs = new ArrayList<>();
    Integer[] buf = new Integer[4];
    for (int i = 0; i <= nums.length - 4; i++) {
      // just need unique pair
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      buf[0] = nums[i];
      for (int j = i + 1; j <= nums.length - 3; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }
        buf[1] = nums[j];
        twoSum(nums, j + 1, nums.length - 1, target, buf, rs);
      }
    }
    return rs;
  }

  private void twoSum(int[] nums, int l, int r, int target,
                      Integer[] buf, List<List<Integer>> rs) {
    target -= buf[0] + buf[1];
    while (l < r) {
      int sum = nums[l] + nums[r];
      if (sum < target) {
        l++;
      } else if (sum > target) {
        r--;
      } else {
        buf[2] = nums[l];
        buf[3] = nums[r];
        rs.add(new ArrayList<>(Arrays.asList(buf)));
        // just need unique pair
        l++;
        while (l < r && nums[l] == nums[l - 1]) {
          l++;
        }
        r--;
        while (l < r && nums[r] == nums[r + 1]) {
          r--;
        }
      }
    }
  }
}
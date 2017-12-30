package com.msh.solutions._15_3Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by monkeysayhi on 2017/12/30.
 */
public class Solution {
  // 取第一个元素，去重，对后面的元素求 twoSum
  public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length < 3) {
      return new ArrayList<>();
    }

    List<List<Integer>> results = new ArrayList<>();
    Arrays.sort(nums);
    Integer[] buf = new Integer[3];
    int r = nums.length - 1;
    for (int l = 0; r - l + 1 >= 3; l++) {
      if (l > 0 && nums[l] == nums[l - 1]) {
        continue;
      }
      buf[0] = nums[l];
      twoSum(nums, l + 1, r, 0 - nums[l], buf, results);
    }
    return results;
  }

  private void twoSum(int[] nums, int l, int r, int target,
                      Integer[] buf, List<List<Integer>> results) {
    while (l < r) {
      int sum = nums[l] + nums[r];
      if (sum < target) {
        l++;
      } else if (sum > target) {
        r--;
      } else {
        buf[1] = nums[l];
        buf[2] = nums[r];
        results.add(new ArrayList<>(Arrays.asList(buf)));
        l++;
        r--;
        while (l < r && nums[l] == nums[l - 1]) {
          l++;
        }
        while (l < r && nums[r] == nums[r + 1]) {
          r--;
        }
      }
    }
  }
}

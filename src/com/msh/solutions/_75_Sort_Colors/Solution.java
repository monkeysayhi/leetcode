package com.msh.solutions._75_Sort_Colors;

/**
 * Created by monkeysayhi on 2018/1/5.
 */
public class Solution {
  // solution 2: 多段划分 lt、eq、gt
  public void sortColors(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return;
    }

    partition3Seg(nums, 1);
  }

  private void partition3Seg(int[] nums, int key) {
    int lt = -1;
    int eq = -1;
    int gt = -1;
    for (int cur = 0; cur < nums.length; cur++) {
      if (nums[cur] < key) {
        if (lt == eq && eq == gt) {
          lt++;
          eq++;
          gt++;
        } else if (lt == eq && eq < gt) {
          lt++;
          eq++;
          gt++;
          swap(nums, lt, cur);
        } else {
          lt++;
          eq++;
          gt++;
          swap(nums, lt, cur);
          swap(nums, eq, cur);
        }
      } else if (nums[cur] == key) {
        if (eq == gt) {
          eq++;
          gt++;
        } else {
          eq++;
          gt++;
          swap(nums, eq, cur);
        }
      } else {
        gt++;
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  // // solution 1: 直接排序
  // public void sortColors(int[] nums) {
  //     if (nums == null || nums.length <= 1) {
  //         return;
  //     }
  //     Arrays.sort(nums);
  // }
}
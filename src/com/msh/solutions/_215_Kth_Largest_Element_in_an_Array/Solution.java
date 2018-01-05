package com.msh.solutions._215_Kth_Largest_Element_in_an_Array;

/**
 * Created by monkeysayhi on 2018/1/5.
 */
public class Solution {
  // 朴素快速选择，逆序partition
  public int findKthLargest(int[] nums, int k) {
    // no edge
    return quickselect(nums, 0, nums.length, k);
  }

  private int quickselect(int[] nums, int l, int r, int k) {
    if (l == r) {
      return nums[l];
    }

    int m = partition(nums, l, r);
    int foundK = m - l + 1;
    if (foundK < k) {
      return quickselect(nums, m + 1, r, k - foundK);
    } else if (foundK > k) {
      return quickselect(nums, l, m, k);
    }
    return nums[m];
  }

  private int partition(int[] nums, int l, int r) {
    int key = nums[l];
    int gt = l;
    for (int cur = l + 1; cur < r; cur++) {
      if (nums[cur] > key) {
        swap(nums, gt + 1, cur);
        gt++;
      }
    }
    swap(nums, l, gt);
    gt--;
    return gt + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
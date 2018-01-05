package com.msh.solutions._75_Sort_Colors;

/**
 * Created by monkeysayhi on 2018/1/5.
 */
// http://www.lintcode.com/en/problem/sort-colors-ii/
// k种颜色，k <= n，排序
public class FollowUp1 {
  // 注意到k <= n，则可考虑 O(nlogk) 的算法，比如彩虹排序
  public void sortColors(int[] nums, int k) {
    if (nums == null || nums.length <= 1) {
      return;
    }
    rainbowSort(nums, 0, nums.length, 1, k);
  }

  private void rainbowSort(int[] nums, int l, int r, int min, int max) {
    if (l == r || l + 1 == r) {
      return;
    }
    if (min == max) {
      return;
    }

    int mid = min + (max - min) / 2;
    int i = l;
    int j = r - 1;
    while (i < j) {
      // mid 可能等于 min，因此，必须让小于等于 mid 的值位于左侧，这样左侧至少有一个元素
      while (i < j && nums[i] <= mid) {
        i++;
      }
      while (i < j && nums[j] > mid) {
        j--;
      }
      if (i < j) {
        swap(nums, i, j);
        i++;
        j--;
      }
    }
    // 要保证两侧都没有丢下元素
    if (nums[i] <= mid) {
      rainbowSort(nums, l, i + 1, min, mid);
      rainbowSort(nums, i + 1, r, mid + 1, max);
    } else {
      rainbowSort(nums, l, i, min, mid);
      rainbowSort(nums, i, r, mid + 1, max);
    }
    // // 一个简单的办法是，让两个分支都处理nums[i]；但这样会破坏已处理的结果，其他题目慎用
    // rainbowSort(nums, l, i + 1, min, mid);
    // rainbowSort(nums, i, r, mid + 1, max);
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}

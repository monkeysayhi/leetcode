package com.msh.solutions._4_Median_of_Two_Sorted_Arrays;

/**
 * Created by monkeysayhi on 2017/12/14.
 */
public class Solution {
  // 二分推进
  // 将找中位数问题转换为找第k小的数的问题
  // 为了在多个数组中找出第k小的数，可以先在某个数组中去掉前k/2小的数，再在剩下的两个子数组中寻找第k/2小的数
  // 反证：设第 k 小的数为 x ，如果 nums1[k/2] > x && nums2[k/2] > x 或 其中一个等于 x ，则 x 最大为第 k - 1 小的数，矛盾。
  // 因此，必然满足 nums1[k/2] < x && nums1[k/2] >= x 或相反，则先去掉 nums1 中的前 k/2 小的数，就缩小了 k/2 的搜索范围。
  // PS：此题对索引操作比较多，手写时一定要脑跑数据测试
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int len = nums1.length + nums2.length;
    if ((len & 1) == 1) {
      return findKthElem(nums1, 0, nums2, 0, len / 2);
    } else {
      return (findKthElem(nums1, 0, nums2, 0, len / 2 - 1)
          + findKthElem(nums1, 0, nums2, 0, len / 2)
      ) / 2.0;
    }
  }

  private int findKthElem(int[] nums1, int start1,
                          int[] nums2, int start2,
                          int k) {
    if (start1 == nums1.length) {
      return nums2[start2 + k];
    }
    if (start2 == nums2.length) {
      return nums1[start1 + k];
    }

    if (k == 0) {
      return nums1[start1] < nums2[start2] ? nums1[start1] : nums2[start2];
    }

    if (k == 1) {
      if (nums1[start1] < nums2[start2]) {
        return findKthElem(nums1, start1 + 1, nums2, start2, 0);
      } else {
        return findKthElem(nums1, start1, nums2, start2 + 1, 0);
      }
    }

    int mid1 = Integer.MAX_VALUE;
    if (start1 + k / 2 - 1 < nums1.length) {
      mid1 = nums1[start1 + k / 2 - 1];
    }
    int mid2 = Integer.MAX_VALUE;
    if (start2 + k / 2 - 1 < nums2.length) {
      mid2 = nums2[start2 + k / 2 - 1];
    }
    if (mid1 < mid2) {
      return findKthElem(nums1, start1 + k / 2, nums2, start2, k - k / 2);
    } else {
      return findKthElem(nums1, start1, nums2, start2 + k / 2, k - k / 2);
    }
  }
}

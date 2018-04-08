package com.msh.solutions._229_Majority_Element_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/4/8.
 */
public class Solution {
  // solution -1: 枚举i，统计nums[i]的出现次数. 时间O(n^2)，空间O(1)
  // solution -2: 排序，把相等元素挤在一起，扫一遍统计相等元素的出现次数. 时间O(nlogn)，空间O(1)
  // solution -3: 扫一遍，map记录元素数量. 时间O(n)，空间O(n)
  // 都没有利用上 lb(n/3)
  // 看easy版的[题解](https://leetcode.com/problems/majority-element/discuss/51613/O(n)-time-O(1)-space-fastest-solution)，利用了数量关系，进行相互抵消，只有大于 lb(n/2) 的元素在全部抵消后还能被剩下. 时间O(n)，空间O(1)
  // solution 1: 实际上easy版的题解描述了一个“选举”过程。每个元素都对自己投“支持”，对他人投“反对”，同时只维护“净支持”票数最高的人；那么此处可维护两个净支持票数最高的人，扫描后得到他们的净支持票数，然后再分别统计两人的支持票是否满足大于 lb(n/3)
  public List<Integer> majorityElement(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new ArrayList<>(0);
    }
    if (nums.length == 1) {
      return Arrays.asList(nums[0]);
    }

    int n = nums.length;
    int majority1 = nums[0];
    int leftMCnt1 = 1;
    int majority2 = nums[1];
    int leftMCnt2 = 1;
    for (int i = 1; majority2 == majority1 && i < n; i++) {
      leftMCnt1++;
      if (nums[i] != majority2) {
        majority2 = nums[i];
      }
    }
    if (majority1 == majority2) {
      return Arrays.asList(majority1);
    }

    // 统计净支持票数
    for (int i = leftMCnt1 + 1; i < n; i++) {
      if (nums[i] == majority1) {
        leftMCnt1++;
        continue;
      }
      if (nums[i] == majority2) {
        leftMCnt2++;
        continue;
      }
      if (leftMCnt1 == 0) {
        majority1 = nums[i];
        leftMCnt1 = 1;
      } else if (leftMCnt2 == 0) {
        majority2 = nums[i];
        leftMCnt2 = 1;
      } else {
        leftMCnt1--;
        leftMCnt2--;
      }
    }

    // 统计支持票数
    int mCnt1 = 0;
    int mCnt2 = 0;
    for (int i = 0; i < n; i++) {
      if (nums[i] == majority1) {
        mCnt1++;
      } else if (nums[i] == majority2) {
        mCnt2++;
      }
    }

    // ans
    if (majority1 == majority2) {
      if (mCnt1 > n / 3) {
        return Arrays.asList(majority1);
      }
      return new ArrayList<>(0);
    } else {
      List<Integer> rs = new ArrayList<>(2);
      if (mCnt1 > n / 3) {
        rs.add(majority1);
      }
      if (mCnt2 > n / 3) {
        rs.add(majority2);
      }
      return rs;
    }
  }
}
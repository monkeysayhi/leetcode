package com.msh.solutions._169_Majority_Element;

/**
 * Created by monkeysayhi on 2018/4/8.
 */
public class Solution {
  // solution 1: 枚举i，统计nums[i]的出现次数. 时间O(n^2)，空间O(1)
  // solution 2: 排序，把相等元素挤在一起，扫一遍统计相等元素的出现次数. 时间O(nlogn)，空间O(1)
  // solution 3: 扫一遍，map记录元素数量. 时间O(n)，空间O(n)
  // 都没有利用上 lb(n/2)
  // solution 4: 看[题解](https://leetcode.com/problems/majority-element/discuss/51613/O(n)-time-O(1)-space-fastest-solution)，利用了数量关系，进行相互抵消，只有大于 lb(n/2) 的元素在全部抵消后还能被剩下. 时间O(n)，空间O(1)
  public int majorityElement(int[] nums) {
    assert nums != null;
    int majority = nums[0];
    int leftMCnt = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == majority) {
        leftMCnt++;
        continue;
      }
      if (leftMCnt > 0) {
        leftMCnt--;
      } else {
        majority = nums[i];
        leftMCnt = 1;
      }
    }
    return majority;
  }
}
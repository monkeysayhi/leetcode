package com.msh.solutions._41_First_Missing_Positive;

/**
 * Created by monkeysayhi on 2018/2/4.
 */
public class Solution {
  // 没有完全想出来，看的两篇题解理解了，http://blog.csdn.net/liuchonge/article/details/73199068，http://bangbingsyb.blogspot.com/2014/11/leetcode-first-missing-positive.html
  // 依旧是如何原地保存中间状态的问题：
  // 1. 负数和0：不需要记录
  // 2. 正数：将 nums[i] 放到 nums[i] - 1 位置上（从0开始）
  // 最后遍历一遍，找到第一个不满足 `nums[i] == i + 1` 的元素，返回 i + 1
  // 对于重复的正数元素，也不需要记录，可当做负数与0处理
  // 如果存在大于nums.length的元素，则ans一定满足 `ans <= nums.length`，否则数组长度将大于nums.length。因此，大于nums.length的元素也不需要记录
  public int firstMissingPositive(int[] nums) {
    // assume valid
    for (int i = 0; i < nums.length; i++) {
      while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
        swap(nums, i, nums[i] - 1);
      }
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  // // 非常量空间：set记录，从1开始遍历，检查缺失者
  // public int firstMissingPositive(int[] nums) {
  //     // assume valid
  //     Set<Integer> set = new HashSet<>();
  //     for (int num : nums) {
  //         if (num >= 1) {
  //             set.add(num);
  //         }
  //     }
  //     int ans = 1;
  //     while (set.contains(ans)) {
  //         ans++;
  //     }
  //     return ans;
  // }
}
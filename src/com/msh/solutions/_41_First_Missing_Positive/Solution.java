package com.msh.solutions._41_First_Missing_Positive;

/**
 * Created by monkeysayhi on 2018/2/4.
 */
public class Solution {
  // solution 1: 暴力，一遍找最小值 minN，维护 set，第二遍从 min(minN + 1, 1)开始在 set 中查找值，找到缺失的最小值。时间O(n)，空间O(n)
  // 用与n相关的数据结构存储状态就一定是非常量空间。但是状态数确实与n相关，所以要从原数组上找突破。注意到原数组不要求不变，可以想象怎么通过修改原数组保留状态 —— 进而想到只找正数，可以直接用坐标表示有无元素（没有完全想出来，看的两篇题解理解了，http://blog.csdn.net/liuchonge/article/details/73199068，http://bangbingsyb.blogspot.com/2014/11/leetcode-first-missing-positive.html）
  // solution 2: 遍历数组，对j = nums[i]，与nums[j]交换，交换后满足nums[j] == j，如果nums[i]不等于i，则继续交换。理想状态下，只需要交换最多O(n)次（同时，遍历也是O(n)次），就能通过查看是否满足 nums[i] == [i] ，来判断元素是否存在。那么第一个不满足的i，就是缺失的正数。然后考虑几种异常case：如果nums[i] == nums[j]，则不需要交换，否则死循环；如果nums[i]等于i，则不需要交换；如果nums[i] < 0，则不需要交换：如果nums[i] > n，则不需要交换，因为如果前n个数都不缺失，数组也就满了，直接可以得知缺失 n + 1；对于 nums[i] in {0, n}，考虑corner case，要求的是缺失的正数，所以保留0的状态无意义，那么nums[0]位置可以放1，从而nums[n - 1]位置放n，交换的时候处理一下。时间O(n)，空间O(1)
  public int firstMissingPositive(int[] nums) {
    assert nums != null;
    if (nums.length == 0) {
      return 1;
    }

    int n = nums.length;
    for (int i = 0; i < n; i++) {
      while (nums[i] >= 1 && nums[i] <= n
          && nums[i] - 1 != i && nums[i] != nums[nums[i] - 1]) {
        swap(nums, i, nums[i] - 1);
      }
    }

    boolean full = true;
    for (int i = 0; i < n; i++) {
      if (nums[i] - 1 != i) {
        return i + 1;
      }
    }
    assert full;
    return n + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[j];
    nums[j] = nums[i];
    nums[i] = tmp;
  }

  // // solution -1: 非常量空间：set记录全部正数元素，从1开始（单独检查），遍历所有e，检查e + 1是否存在
  // public int firstMissingPositive(int[] nums) {
  //     // assume valid
  //     Set<Integer> set = new HashSet<>();
  //     for (int num : nums) {
  //         if (num >= 1) {
  //             set.add(num);
  //         }
  //     }
  //     if (!set.contains(1)) {
  //         return 1;
  //     }
  //     int minMissingPnum = Integer.MAX_VALUE;
  //     for (Integer pnum : set) {
  //         // 一定满足 minMissingPnum < Integer.MAX_VALUE，所以可忽略该case
  //         if (pnum == Integer.MAX_VALUE) {
  //             continue;
  //         }
  //         if (pnum + 1 < minMissingPnum && !set.contains(pnum + 1)) {
  //             minMissingPnum = pnum + 1;
  //         }
  //     }
  //     return minMissingPnum;
  // }
}
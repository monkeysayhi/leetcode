package com.msh.solutions._287_Find_the_Duplicate_Number;

/**
 * Created by monkeysayhi on 2018/1/24.
 */
public class Solution {
  // solution -1: 如果可以破坏原数组，还可以仿照[41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/description/)，将元素值交换到对应位置，来如果发现有元素与目标位置的元素值相等，则找到重复元素。时间O(n)空间O(1)，但会破坏原数组

//     // solution -2: 如果重复元素只出现2次，则可以利用数学的平方差公式，和相减得到 a - b,平方和相减得到 a^2 - b^2，则 a + b = (a^2 - b^2) / (a - b)，继而求得a、b，判断重复a还是重复b。时间O(n)空间O(1)，缺点是求和或平方和的时候可能溢出
//     public int findDuplicate(int[] nums) {
//         assert nums != null && nums.length >= 1;
//         int n = nums.length - 1;

//         int sum0 = 0;
//         int sumSquares0 = 0;
//         for (int i = 1; i <= n; i++) {
//             sum0 += i;
//             sumSquares0 += i * i;
//         }
//         int sum = 0;
//         int sumSquares = 0;
//         for (int num : nums) {
//             sum += num;
//             sumSquares += num * num;
//         }

//         // 设b为重复者
//         int aDiffB = sum0 - sum;
//         int a2DiffB2 = sumSquares0 - sumSquares;
//         int aPlusB = a2DiffB2 / aDiffB;
//         int b = (aPlusB - aDiffB) / 2;
//         return b;
//     }

  // solution 3: O(n)，神·快慢指针，http://keithschwarz.com/interesting/code/?dir=find-duplicate，未掌握

  // solution 2: O(nlogn)，二分法变形，二分缩小扫描"重复区间"的范围
  public int findDuplicate(int[] nums) {
    assert nums != null && nums.length >= 1;
    int r = nums.length - 1; // inclusive
    int l = 1;
    while (l < r) {
      int m = l + (r - l) / 2;
      int leCnt = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] <= m) {
          leCnt++;
        }
      }
      if (leCnt > m) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return r;
  }

  // // solution 1: O(n^2)，暴力
  // public int findDuplicate(int[] nums) {
  //     assert nums != null && nums.length >= 1;
  //     int n = nums.length - 1;
  //     for (int i = 1; i <= n; i++) {
  //         int cnt = 0;
  //         for (int j = 0; j < nums.length; j++) {
  //             if (nums[j] == i) {
  //                 cnt++;
  //                 if (cnt > 1) {
  //                     return i;
  //                 }
  //             }
  //         }
  //     }
  //     assert true;
  //     return -1;
  // }
}
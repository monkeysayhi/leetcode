package com.msh.solutions._287_Find_the_Duplicate_Number;

/**
 * Created by monkeysayhi on 2018/1/24.
 */
public class Solution {
  // solution 2: O(nlogn)，二分法变形，二分缩小扫描"重复区间"的范围
  // solutuon 3: O(n)，神·快慢指针，http://keithschwarz.com/interesting/code/?dir=find-duplicate
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
package com.msh.solutions._238_Product_of_Array_Except_Self;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  // solution 2: 基于solution1优化，pLs用output记(正序更新)，pRs用变量pR记录（倒序更新），空间O(1)
  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    assert n > 1;

    int[] output = new int[n];
    output[0] = nums[0];
    for (int i = 1; i < n; i++) {
      output[i] = output[i - 1] * nums[i];
    }
    int pR = nums[n - 1];
    output[n - 1] = output[n - 2];
    for (int i = n - 2; i >= 1; i--) {
      output[i] = output[i - 1] * pR;
      pR *= nums[i];
    }
    output[0] = pR;
    return output;
  }

//     // solution 1: 记录两个方向的累积积products，则output[i] = pLs[i - 1] * pRs[i + 1]，空间O(n)
//     public int[] productExceptSelf(int[] nums) {
//         int n = nums.length;
//         assert n > 1;

//         int[] pLs = new int[n];
//         pLs[0] = nums[0];
//         for (int i = 1; i < n; i++) {
//             pLs[i] = pLs[i - 1] * nums[i];
//         }
//         int[] pRs = new int[n];
//         pRs[n - 1] = nums[n - 1];
//         for (int i = n - 2; i >= 0; i--) {
//             pRs[i] = pRs[i + 1] * nums[i];
//         }

//         int[] output = new int[n];
//         for (int i = 0; i < n; i++) {
//             if (i == 0) {
//                 output[i] = pRs[i + 1];
//             } else if (i == n - 1) {
//                 output[i] = pLs[i - 1];
//             } else {
//                 output[i] = pLs[i - 1] * pRs[i + 1];
//             }
//         }
//         return output;
//     }
}
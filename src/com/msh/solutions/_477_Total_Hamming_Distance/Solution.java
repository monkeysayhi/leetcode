package com.msh.solutions._477_Total_Hamming_Distance;

/**
 * Created by monkeysayhi on 2018/3/22.
 */
public class Solution {
  // solution 1: 看题解，O(n)
  // 如果haming(n1 & 1, n2 & 1) == 1，则n1、n2在最低bit位上必然一个为0，一个为1；其他位同理。
  // 则可以统计每位上所有数的0、1的数量，`zeroCnt * oneCnt`就是该位上的汉明距离，累加32位求和
  public int totalHammingDistance(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int sum = 0;
    for (int i = 0; i < 32; i++) {
      int zeroCnt = 0;
      for (int num : nums) {
        if ((num & (1 << i)) == 0) {
          zeroCnt++;
        }
      }
      sum += zeroCnt * (nums.length - zeroCnt);
    }
    return sum;
  }

//     // solution -1: 暴力，O(n^2)。二数异或，求二进制中1的数量
//     public int totalHammingDistance(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
//         int sum = 0;
//         for (int n1 : nums) {
//             for (int n2 : nums) {
//                 // n1 == n2无所谓；为<n1, n2>, <n2, n1>去重
//                 if (n1 <= n2) {
//                     sum += hammingDistance(n1, n2);
//                 }
//             }
//         }
//         return sum;
//     }

//     private int hammingDistance(int n1, int n2) {
//         int bOneCnt = 0;
//         for (int num = n1 ^ n2; num != 0; num = num >> 1) {
//             if ((num & 1) == 1) {
//                 bOneCnt++;
//             }
//         }
//         return bOneCnt;
//     }
}
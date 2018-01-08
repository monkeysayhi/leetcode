package com.msh.solutions._313_Super_Ugly_Number;

/**
 * Created by monkeysayhi on 2018/1/8.
 */
// 题目有问题。实际用到的测试用例不包括n=1e6的情况，但该情况次代码会溢出
public class Solution {
  // 记录最大丑数和各素因子的追及状态，O(n)
  public int nthSuperUglyNumber(int n, int[] primes) {
    // no error
    if (n == 1) {
      return 1;
    }

    long[] uglyNums = new long[n];
    int[] nextUglyNumFactors = new int[primes.length];
    uglyNums[0] = 1L;
    for (int i = 1; i < n; i++) {
      long curMaxUglyNum = uglyNums[i - 1];
      for (int j = 0; j < primes.length; j++) {
        while (uglyNums[nextUglyNumFactors[j]] * primes[j] <= curMaxUglyNum) {
          nextUglyNumFactors[j]++;
        }
      }
      long nextUglyNum = Long.MAX_VALUE;
      for (int j = 0; j < primes.length; j++) {
        if (uglyNums[nextUglyNumFactors[j]] * primes[j] < nextUglyNum) {
          nextUglyNum = uglyNums[nextUglyNumFactors[j]] * primes[j];
        }
      }
      uglyNums[i] = nextUglyNum;
    }
    return (int) uglyNums[n - 1];
  }
}

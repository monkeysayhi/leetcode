package com.msh.solutions._334_Increasing_Triplet_Subsequence;

/**
 * Created by monkeysayhi on 2018/1/30.
 */
public class Solution {
  // 看题解才转过弯来，，，不好理解，不过分析正确性的过程有价值
  // http://www.cnblogs.com/grandyang/p/5194599.html
  public boolean increasingTriplet(int[] nums) {
    if (nums == null || nums.length < 3) {
      return false;
    }

    int leN1 = Integer.MAX_VALUE;
    int eqN2 = Integer.MAX_VALUE;
    for (int n : nums) {
      if (leN1 >= n) {
        leN1 = n;
      } else if (eqN2 >= n) {
        // 关键是eqN2的变化，和依据eqN2作出的判断
        eqN2 = n;
      } else {
        // eqN3 = n;
        return true;
      }
    }
    return false;
  }
}
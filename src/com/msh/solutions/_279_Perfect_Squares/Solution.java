package com.msh.solutions._279_Perfect_Squares;

/**
 * Created by monkeysayhi on 2018/1/12.
 */
public class Solution {
  // dp[i]为加和为i所需的最少平方数的数量，dp[i] = min{dp[i - s[j]] + 1}
  public int numSquares(int n) {
    boolean[] sqTbl = new boolean[n + 1];
    for (int i = 1; i * i <= n; i++) {
      sqTbl[i * i] = true;
    }

    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      if (sqTbl[i]) {
        dp[i] = 1;
        continue;
      }
      dp[i] = Integer.MAX_VALUE;
      for (int j = 1; j < i; j++) {
        if (sqTbl[j]) {
          dp[i] = Math.min(dp[i], dp[i - j] + 1);
        }
      }
    }
    return dp[n];
  }
}

package com.msh.solutions._96_Unique_Binary_Search_Trees;

/**
 * Created by monkeysayhi on 2018/1/13.
 */
public class Solution {
  // 给定树结构后，由于不存在重复元素，且中序dfs的序列是固定的，从而有且只有一个BST
  // dp[i]为i个不重复元素能构成的BST的数量，dp[i] = sum{dp[j] * dp[1] * dp[i - j - 1] | 0 <= j < i}
  public int numTrees(int n) {
    if (n <= 0) {
      return 0;
    }

    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] += dp[j] * dp[1] * dp[i - j - 1];
      }
    }
    return dp[n];
  }
}
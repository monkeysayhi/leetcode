package com.msh.solutions._120_Triangle;

import java.util.List;

/**
 * Created by monkeysayhi on 2018/1/12.
 */
public class Solution {
  // solution 1: dp[i][j]为以第i层、第j个节点为根的子三角的最小路径和，dp[i][j] = min{dp[i + 1][j], dp[i + 1][j + 1]} +
  // tri[i][j]
  // 空间O(n^2)
  public int minimumTotal(List<List<Integer>> triangle) {
    List<List<Integer>> tri = triangle;
    assert tri != null && tri.size() != 0;

    int rowCnt = tri.size();
    int colCnt = tri.get(rowCnt - 1).size();
    int[][] dp = new int[rowCnt][colCnt];
    for (int j = 0; j < tri.get(rowCnt - 1).size(); j++) {
      dp[rowCnt - 1][j] = tri.get(rowCnt - 1).get(j);
    }
    for (int i = rowCnt - 2; i >= 0; i--) {
      for (int j = 0; j < tri.get(i).size(); j++) {
        dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + tri.get(i).get(j);
      }
    }
    return dp[0][0];
  }

//     // solution 2: 在solution 1的基础上，改为滚动数组
//     // 空间O(n)
//     public int minimumTotal(List<List<Integer>> triangle) {
//         List<List<Integer>> tri = triangle;
//         assert tri != null && tri.size() != 0;

//         int rowCnt = tri.size();
//         int colCnt = tri.get(rowCnt - 1).size();
//         int[] dp = new int[colCnt];
//         for (int j = 0; j < tri.get(rowCnt - 1).size(); j++) {
//             dp[j] = tri.get(rowCnt - 1).get(j);
//         }
//         for (int i = rowCnt - 2; i >= 0; i--) {
//             for (int j = 0; j < tri.get(i).size(); j++) {
//                 dp[j] = Math.min(dp[j], dp[j + 1]) + tri.get(i).get(j);
//             }
//         }
//         return dp[0];
//     }
}
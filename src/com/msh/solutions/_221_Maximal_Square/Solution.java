package com.msh.solutions._221_Maximal_Square;

/**
 * Created by monkeysayhi on 2018/1/24.
 */
public class Solution {
  // 最优化，dp[i][j]为以(i, j)为右下角最大正方形的边长，dp[i][j] =
  // if mat[i][j] == '1' : 1 + min{dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]};
  // else : 0
  public int maximalSquare(char[][] matrix) {
    char[][] mat = matrix;
    if (mat == null || mat.length == 0
        || mat[0] == null || mat[0].length == 0) {
      return 0;
    }

    int rowCnt = mat.length;
    int colCnt = mat[0].length;
    int[][] dp = new int[rowCnt][colCnt];
    int maxLen = 0;
    for (int i = 0; i < rowCnt; i++) {
      for (int j = 0; j < colCnt; j++) {
        if (mat[i][j] == '0') {
          dp[i][j] = 0;
          continue;
        }
        if (i == 0 || j == 0) {
          dp[i][j] = 1;
        } else {
          dp[i][j] = 1 + Math.min(
              dp[i - 1][j - 1], Math.min(
                  dp[i - 1][j],
                  dp[i][j - 1]
              ));
        }
        maxLen = Math.max(maxLen, dp[i][j]);
      }
    }
    return maxLen * maxLen;
  }
}
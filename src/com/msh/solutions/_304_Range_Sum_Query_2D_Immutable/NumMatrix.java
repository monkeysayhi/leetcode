package com.msh.solutions._304_Range_Sum_Query_2D_Immutable;

/**
 * Created by monkeysayhi on 2018/4/9.
 */
// 二维矩阵上的 preSum，可以认为是动规
// 定义 preSum[i][j] 为“以(i, j)矩形(0, 0, i, j)的和”，则 preSum[i][j] = mat[i][j] + preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1]
// sumRegion(r1, c1, r2, c2) = preSum[r2][c2] - preSum[r1 - 1][c2] - preSum[r2][c1 - 1] + preSum[r1 - 1][c1 - 1]
public class NumMatrix {
  private int[][] preSum;

  public NumMatrix(int[][] matrix) {
    // TODO: check args
    init(matrix);
  }

  private void init(int[][] mat) {
    if (mat == null || mat.length == 0 || mat[0].length == 0) {
      return;
    }
    int m = mat.length;
    int n = mat[0].length;
    preSum = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        preSum[i][j] = mat[i - 1][j - 1]
            + preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    // assume valid
    int r1 = row1 + 1;
    int c1 = col1 + 1;
    int r2 = row2 + 1;
    int c2 = col2 + 1;
    return preSum[r2][c2] - preSum[r1 - 1][c2] - preSum[r2][c1 - 1] + preSum[r1 - 1][c1 - 1];
  }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
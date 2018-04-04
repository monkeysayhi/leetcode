package com.msh.solutions._59_Spiral_Matrix_2;

/**
 * Created by monkeysayhi on 2018/4/4.
 */
public class Solution {
  // 模拟
  public int[][] generateMatrix(int n) {
    // 实际上题目并没有限制 n < 0
    if (n <= 0) {
      return new int[0][0];
    }
    n = Math.abs(n);
    int[][] rs = new int[n][n];
    int top = 0;
    int bottom = n - 1;
    int left = 0;
    int right = n - 1;
    int k = 1;
    int limit = n * n;
    while (k <= limit) {
      // top, left -> right
      for (int j = left; k <= limit && j <= right; j++) {
        rs[top][j] = k++;
      }
      top++;
      // right, top -> bottom
      for (int i = top; k <= limit && i <= bottom; i++) {
        rs[i][right] = k++;
      }
      right--;
      // bottom, right -> left
      for (int j = right; k <= limit && j >= left; j--) {
        rs[bottom][j] = k++;
      }
      bottom--;
      // left, bottom -> top
      for (int i = bottom; k <= limit && i >= top; i--) {
        rs[i][left] = k++;
      }
      left++;
    }
    return rs;
  }
}
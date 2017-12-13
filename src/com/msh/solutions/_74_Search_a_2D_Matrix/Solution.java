package com.msh.solutions._74_Search_a_2D_Matrix;

/**
 * Created by monkeysayhi on 2017/12/13.
 */
public class Solution {
  // 二分
  public boolean searchMatrix(int[][] matrix, int target) {
    int[][] mat = matrix;
    if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) {
      return false;
    }
    return bsearch(mat, target);
  }

  private boolean bsearch(int[][] mat, int v) {
    int l = 0;
    int r = mat.length * mat[0].length;
    while (l < r) {
      int m = l + (r - l) / 2;
      int val = getVal(mat, m);
      if (val == v) {
        return true;
      }
      if (val > v) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return false;
  }

  private int getVal(int[][] mat, int idx) {
    int colCnt = mat[0].length;
    return mat[idx / colCnt][idx % colCnt];
  }
}

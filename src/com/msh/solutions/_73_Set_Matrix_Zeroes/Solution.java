package com.msh.solutions._73_Set_Matrix_Zeroes;

/**
 * Created by monkeysayhi on 2018/1/28.
 */
public class Solution {
  // solution 1/2: 暴力扫描，必然需要记录状态，因为将来扫到的0可能是“原始0”或“修改0”
  // 状态总数不确定，因此，无法用额外空间记录状态。因此，要设法利用mat本身记录
  // 最后还是没想出来，，，看题解少想了一点，还要在扫描的时候统一状态：用一个状态记录该行是否需要扫描，一个状态记录该列是否需要扫描
  // solution 3: 题解，https://leetcode.com/problems/set-matrix-zeroes/discuss/26013/
  // row0记录第0行的状态，col0记录第0列的状态（0或非0），然后用mat[0][1:]记录第1列及之后列的状态（0或非0），mat[1:][0]记录第1行及之后行的状态（0或非0）
  public void setZeroes(int[][] matrix) {
    int[][] mat = matrix;
    if (mat == null || mat.length == 0
        || mat[0] == null || mat[0].length == 0) {
      return;
    }

    // 统计状态
    boolean row0 = false;
    for (int j = 0; j < mat[0].length; j++) {
      if (mat[0][j] == 0) {
        row0 = true;
        break;
      }
    }
    boolean col0 = false;
    for (int i = 0; i < mat.length; i++) {
      if (mat[i][0] == 0) {
        col0 = true;
        break;
      }
    }
    for (int i = 1; i < mat.length; i++) {
      for (int j = 1; j < mat[0].length; j++) {
        if (mat[i][j] == 0) {
          mat[0][j] = 0;
          mat[i][0] = 0;
        }
      }
    }

    // 修改
    for (int j = 1; j < mat[0].length; j++) {
      if (mat[0][j] == 0) {
        for (int i = 1; i < mat.length; i++) {
          mat[i][j] = 0;
        }
      }
    }
    for (int i = 1; i < mat.length; i++) {
      if (mat[i][0] == 0) {
        for (int j = 1; j < mat[0].length; j++) {
          mat[i][j] = 0;
        }
      }
    }
    if (row0) {
      for (int j = 0; j < mat[0].length; j++) {
        mat[0][j] = 0;
      }
    }
    if (col0) {
      for (int i = 0; i < mat.length; i++) {
        mat[i][0] = 0;
      }
    }
  }
}
package com.msh.solutions._240_Search_a_2D_Matrix_2;

/**
 * Created by monkeysayhi on 2017/12/13.
 */
public class Solution {
  // solution 1: 暴力扫描整个矩阵, O(m*n)
  // solution 2: 以从左下角或右上角为中心，能将水平方向和垂直方向的元素连成一个升序数组，从中点开始线性扫描，O(m+n)（面试时答到这里就足够）
  // solution 3: 以从左下角或右上角为中心，能将水平方向和垂直方向的元素连成一个升序数组，从中点开始二分查下界（该方法也要掌握）
  public boolean searchMatrix(int[][] matrix, int target) {
    int[][] mat = matrix;
    if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) {
      return false;
    }

    int centerX = 0;
    int centerY = mat[0].length - 1;
    while (true) {
      int val = mat[centerX][centerY];
      if (val == target) {
        return true;
      }
      if (val < target) {
        if (centerX == mat.length - 1) {
          return false;
        }
        centerX++;
      } else {
        if (centerY == 0) {
          return false;
        }
        centerY--;
      }
    }
  }
}

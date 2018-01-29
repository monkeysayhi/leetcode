package com.msh.solutions._54_Spiral_Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/1/29.
 */
public class Solution {
  // 模拟
  public List<Integer> spiralOrder(int[][] matrix) {
    int[][] mat = matrix;
    if (mat == null) {
      return null;
    }
    if (mat.length == 0 || mat[0] == null || mat[0].length == 0) {
      return new ArrayList<>();
    }

    List<Integer> rs = new ArrayList<>();
    int top = 0;
    int bottom = mat.length;
    int left = 0;
    int right = mat[0].length;
    while (top < bottom || left < right) {
      // >
      if (top < bottom) {
        for (int j = left; j < right; j++) {
          rs.add(mat[top][j]);
        }
        top++;
      }
      // v
      if (left < right) {
        for (int i = top; i < bottom; i++) {
          rs.add(mat[i][right - 1]);
        }
        right--;
      }
      // <
      if (top < bottom) {
        for (int j = right - 1; j >= left; j--) {
          rs.add(mat[bottom - 1][j]);
        }
        bottom--;
      }
      // ^
      if (left < right) {
        for (int i = bottom - 1; i >= top; i--) {
          rs.add(mat[i][left]);
        }
        left++;
      }
    }
    return rs;
  }
}
package com.msh.solutions._48_Rotate_Image;

/**
 * Created by monkeysayhi on 2018/1/28.
 */
public class Solution {
  // soltuion 1: 面试时感性找到的trick，先对翻，再斜翻
  // 虽然不好证明，但非常容易写，面试首选
  public void rotate(int[][] matrix) {
    int[][] mat = matrix;
    if (mat == null || mat.length == 0
        || mat[0] == null || mat[0].length == 0) {
      return;
    }

    duifan(mat);
    xiefan(mat);
  }

  private void duifan(int[][] mat) {
    int n = mat.length;
    for (int i = 0; i < n / 2; i++) {
      for (int j = 0; j < n; j++) {
        swap(mat, i, j, n - 1 - i, j);
      }
    }
  }

  private void xiefan(int[][] mat) {
    int n = mat.length;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        swap(mat, i, j, j, i);
      }
    }
  }

  private void swap(int[][] mat, int x1, int y1, int x2, int y2) {
    int tmp = mat[x1][y1];
    mat[x1][y1] = mat[x2][y2];
    mat[x2][y2] = tmp;
  }

//     // soltuion 2: 标准的坐标映射，一次转一圈，mat[i][j]换到mat[j][n - 1 - i]
//     // 公式容易错，面试次选
//     public void rotate(int[][] matrix) {
//         int[][] mat = matrix;
//         if (mat == null || mat.length == 0
//             || mat[0] == null || mat[0].length == 0) {
//             return;
//         }

//         int l = 0;
//         int r = mat.length - 1;
//         while (l < r) {
//             rotate(mat, l, r);
//             l++;
//             r--;
//         }
//     }

//     private void rotate(int[][] mat, int l, int r) {
//         int n = r - l + 1;
//         int j = 0;
//         for (int i = 0; i < n - 1; i++) {
//             int tmp = mat[l + j][l + n - 1 - i];
//             mat[l + j][l + n - 1 - i] = mat[l + i][l + j];
//             mat[l + i][l + j] = mat[l + n - 1 - j][l + i];
//             mat[l + n - 1 - j][l + i] = mat[l + n - 1 - i][l + n - 1 - j];
//             mat[l + n - 1 - i][l + n - 1 - j] = tmp;
//         }
//     }
}
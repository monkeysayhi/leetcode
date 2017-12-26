package com.msh.solutions._302_Smallest_Rectangle_Enclosing_Black_Pixels;

/**
 * Created by monkeysayhi on 2017/12/26.
 */
// 暴力法太简单，该题目一般会要求时间复杂度低于O(n*m)
public class Solution {
  // 只有一个黑色区域，则给定黑点的四个方向上必将都是0、1构成的有序序列（01或10）
  // 扩展成行（或列）后，仍是有序的“有1行”和“无1行”
  // 在多个行上二分查找第一个"有1行"（升序），该位置就是区域的一个边界；其他边界同理
  public int minArea(char[][] image, int x, int y) {
    char[][] grid = image;
    if (grid == null || grid.length == 0
        || grid[0] == null || grid[0].length == 0) {
      return 0;
    }

    int top = bsearchLVertical(grid, 0, x);
    int bottom = bsearchRVerticalReverse(grid, x + 1, grid.length);
    int left = bsearchLHorizontal(grid, 0, y);
    int right = bsearchRHorizontalReverse(grid, y + 1, grid[0].length);
    return (bottom - top) * (right - left);
  }

  private int bsearchLVertical(char[][] grid, int l, int r) {
    while (l < r) {
      int m = l + (r - l) / 2;
      if (existsBlockPixelsHorizontal(grid, m)) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return r;
  }

  private int bsearchRVerticalReverse(char[][] grid, int l, int r) {
    while (l < r) {
      int m = l + (r - l) / 2;
      if (!existsBlockPixelsHorizontal(grid, m)) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return r;
  }

  private int bsearchLHorizontal(char[][] grid, int l, int r) {
    while (l < r) {
      int m = l + (r - l) / 2;
      if (existsBlockPixelsVertical(grid, m)) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return r;
  }

  private int bsearchRHorizontalReverse(char[][] grid, int l, int r) {
    while (l < r) {
      int m = l + (r - l) / 2;
      if (!existsBlockPixelsVertical(grid, m)) {
        r = m;
      } else {
        l = m + 1;
      }
    }
    return r;
  }

  private boolean existsBlockPixelsHorizontal(char[][] grid, int x) {
    for (int y = 0; y < grid[0].length; y++) {
      if (grid[x][y] == '1') {
        return true;
      }
    }
    return false;
  }

  private boolean existsBlockPixelsVertical(char[][] grid, int y) {
    for (int x = 0; x < grid.length; x++) {
      if (grid[x][y] == '1') {
        return true;
      }
    }
    return false;
  }

  // // 暴力扫描，O(n*m)
  // public int minArea(char[][] image, int x, int y) {
  //     char[][] grid = image;
  //     if (grid == null || grid.length == 0
  //         || grid[0] == null || grid[0].length == 0) {
  //         return 0;
  //     }

  //     int top = image.length;
  //     int bottom = -1;
  //     int left = image[0].length;
  //     int right = -1;
  //     for (int i = 0; i < image.length; i++) {
  //         for (int j = 0; j < image[0].length; j++) {
  //             if (image[i][j] == '1') {
  //                 if (i < top) {
  //                     top = i;
  //                 }
  //                 if (i > bottom) {
  //                     bottom = i;
  //                 }
  //                 if (j < left) {
  //                     left = j;
  //                 }
  //                 break;
  //             }
  //         }
  //         for (int j = image[0].length - 1; j > right && j >= left; j--) {
  //             if (image[i][j] == '1') {
  //                 if (j > right) {
  //                     right = j;
  //                 }
  //                 break;
  //             }
  //         }
  //     }

  //     return (bottom - top) * (right - left);
  // }
}

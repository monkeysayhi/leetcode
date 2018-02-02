package com.msh.solutions._36_Valid_Sudoku;

/**
 * Created by monkeysayhi on 2018/2/2.
 */
public class Solution {
  // 完整的数独规则：
  //   1. Each row must have the numbers 1-9 occuring just once.
  //   2. Each column must have the numbers 1-9 occuring just once.
  //   3. And the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.
  // 题目声明数独可以被部分填充，只需要验证这部分填充的数独是否是有效的。不管是否有解，只关心目前是否有效
  // solution 1: 暴力，先扫所有行，再扫所有列，最后扫所有子矩阵(可以只扫一遍)，使用set判重，时间O(n^2)，空间O(n^2)
  // 必然要扫描全矩阵，因此时间上无法优化。设法优化空间。
  // solution 2: 分为三次扫描，从而复用判重的set，空间优化到O(n)
  public boolean isValidSudoku(char[][] board) {
    char[][] grid = board;
    if (grid == null || grid.length != 9
        || grid[0] == null || grid[0].length != 9) {
      return false;
    }

    return validRows(grid) && validCols(grid) && validSubBoxes(grid);
  }

  private boolean validRows(char[][] grid) {
    for (int i = 0; i < 9; i++) {
      boolean[] stat = new boolean[9 + 1];
      for (int j = 0; j < 9; j++) {
        if (grid[i][j] == '.') {
          continue;
        }
        int val = grid[i][j] - '0';
        if (stat[val] == true) {
          return false;
        }
        stat[val] = true;
      }
    }
    return true;
  }

  private boolean validCols(char[][] grid) {
    for (int j = 0; j < 9; j++) {
      boolean[] stat = new boolean[9 + 1];
      for (int i = 0; i < 9; i++) {
        if (grid[i][j] == '.') {
          continue;
        }
        int val = grid[i][j] - '0';
        if (stat[val] == true) {
          return false;
        }
        stat[val] = true;
      }
    }
    return true;
  }

  private boolean validSubBoxes(char[][] grid) {
    for (int offsetI = 0; offsetI < 9; offsetI += 3) {
      for (int offsetJ = 0; offsetJ < 9; offsetJ += 3) {
        boolean[] stat = new boolean[9 + 1];
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            if (grid[offsetI + i][offsetJ + j] == '.') {
              continue;
            }
            int val = grid[offsetI + i][offsetJ + j] - '0';
            if (stat[val] == true) {
              return false;
            }
            stat[val] = true;
          }
        }
      }
    }
    return true;
  }
}
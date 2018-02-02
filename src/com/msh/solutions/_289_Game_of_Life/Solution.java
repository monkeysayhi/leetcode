package com.msh.solutions._289_Game_of_Life;

/**
 * Created by monkeysayhi on 2018/2/2.
 */
public class Solution {
  // basic: 暴力，复制一个矩阵 copy，根据 copy 修改原矩阵 origin，空间O(m * n)
  // 改进: 只复制前两行，以后复用这两行，空间O(n)
  // 如果打算常量时间完成的话，就必须利用矩阵记录状态，即“在修改mat[i][j]后，使mat[i][j]同时记录当代是否存活与后代是否存活”
  // follow up: 继续使用1号bit位记录当代是否存活，用2号bit位记录后代是否存活；第一遍扫描补充后代是否存活的状态，第二遍扫描将将后代状态更新到矩阵
  public void gameOfLife(int[][] board) {
    int[][] mat = board;
    if (mat == null || mat.length == 0
        || mat[0] == null || mat[0].length == 0) {
      return;
    }
    // add next states
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        int curState = mat[i][j];
        int nextState = calNextState(mat, i, j);
        mat[i][j] = curState | nextState << 1;
      }
    }
    // update states
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        int nextState = mat[i][j] >> 1;
        mat[i][j] = nextState;
      }
    }
  }

  private int calNextState(int[][] mat, int centerX, int centerY) {
    final int STATE_LIVE = 1;
    final int STATE_DEAD = 0;
    int liveNeighborCnt = 0;
    int[][] DIRECS = new int[][]{
        {0, 1}, {0, -1}, {1, 0}, {-1, 0},
        {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
    };
    for (int[] direc : DIRECS) {
      int x = centerX + direc[0];
      int y = centerY + direc[1];
      if (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) {
        int curState = mat[x][y] & 0b1;
        if (curState == STATE_LIVE) {
          liveNeighborCnt++;
        }
      }
    }
    int centerCurState = mat[centerX][centerY]; // & 0b1
    // rule 1: Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    if (centerCurState == STATE_LIVE && liveNeighborCnt < 2){
      return STATE_DEAD;
    }
    // rule 2: Any live cell with two or three live neighbors lives on to the next generation.
    if (centerCurState == STATE_LIVE && (liveNeighborCnt == 2 || liveNeighborCnt == 3)){
      return STATE_LIVE;
    }
    // rule 3: Any live cell with more than three live neighbors dies, as if by over-population.
    if (centerCurState == STATE_LIVE && liveNeighborCnt > 3){
      return STATE_DEAD;
    }
    // rule 4: Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    if (centerCurState == STATE_DEAD && liveNeighborCnt == 3){
      return STATE_LIVE;
    }
    return centerCurState;
  }
}
package com.msh.solutions._773_Sliding_Puzzle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by monkeysayhi on 2018/9/26.
 */
public class Solution {
  private static final int[][] DIRECS = new int[][]{
      {0, 1}, {1, 0}, {0, -1}, {-1, 0}
  };

  // 以 dst 为根，层序bfs，遍历到 src
  // 因为 2^6 种摆放方案，暴力枚举即可
  public int slidingPuzzle(int[][] board) {
    // assume valid
    int[][] src = board;
    int[][] dst = new int[][]{{1, 2, 3}, {4, 5, 0}};

    int[][] root = dst;
    int[][] target = src;
    if (hash(root) == hash(target)) {
      return 0;
    }

    int hashTarget = hash(target);
    // TODO: opt: replace all mat with hash
    Queue<int[][]> q = new LinkedList<>();
    q.offer(root);
    Set<Integer> vis = new HashSet<>();
    vis.add(hash(root));
    int step = 0;
    while (q.size() > 0) {
      step++;
      int curLevelSize = q.size();
      for (int i = 0; i < curLevelSize; i++) {
        int[][] u = q.poll();
        int[] pair = findZero(u);
        for (int[] direc : DIRECS) {
          int x = pair[0] + direc[0];
          int y = pair[1] + direc[1];
          if (x >= 0 && x < 2 && y >= 0 && y < 3) {
            int[][] v = swap(u, pair[0], pair[1], x, y);
            int hashV = hash(v);
            if (!vis.contains(hashV)) {
              if (hashV == hashTarget) {
                return step;
              }
              q.offer(v);
              vis.add(hashV);
            }
          }
        }
      }
    }
    return -1;
  }

  private int[][] swap(int[][] u, int zeroX, int zeroY, int swapingX, int swapingY) {
    int[][] v = new int[2][3];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        v[i][j] = u[i][j];
      }
    }
    v[zeroX][zeroY] = u[swapingX][swapingY];
    v[swapingX][swapingY] = u[zeroX][zeroY];
    return v;
  }

  private int[] findZero(int[][] mat) {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        if (mat[i][j] == 0) {
          return new int[]{i, j};
        }
      }
    }
    assert true;
    return null;
  }

  private int hash(int[][] mat) {
    int hash = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        hash = hash * 10 + mat[i][j];
      }
    }
    return hash;
  }
}
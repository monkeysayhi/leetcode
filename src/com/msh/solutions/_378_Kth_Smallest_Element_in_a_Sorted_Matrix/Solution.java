package com.msh.solutions._378_Kth_Smallest_Element_in_a_Sorted_Matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by monkeysayhi on 2018/1/31.
 */
public class Solution {
  private static class Info {
    private int row;
    private int col;

    private Info(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  // 暴力扫描全矩阵，maxHeap维护最小的k个元素，O(n^2 * logk)
  // 暴力法忽略了矩阵的有序性
  // solution 1: 每行都是升序序列，在n个升序序列中找第k小的数，用minHeap维护前n小的数（更严格），假设 k > n，则O(klogn)
  // solution 1 忽略了每行每列同时满足升序的条件，还能继续优化
  // solution 2: 以右上角为中心，<左，角，下>连成一条升序序列，参考 https://leetcode.com/problems/median-of-two-sorted-arrays/description/ 在n个序列中二分推进，共n^2个元素，时间O(nlogn)（= O(n * log(n^2))）。但实现起来很麻烦，次选
  // solution 3: 升序序列的部分同上，参考 https://leetcode.com/problems/find-the-duplicate-number/description/ 在整个矩阵中二分缩小范围，每次统计数量的时间参考 https://leetcode.com/problems/search-a-2d-matrix-ii/description/ 为O(n + n)，假设元素范围max - min = m，则总时间O(nlogm)。写起来思路上比solution 2麻烦，代码量略增大，次选但需要掌握

  // solution 1
  public int kthSmallest(int[][] matrix, int k) {
    final int[][] mat = matrix;
    int n = mat.length;
    // assume valid
    PriorityQueue<Info> minHeap = new PriorityQueue<>(n, new Comparator<Info>() {
      public int compare(Info info1, Info info2) {
        return mat[info1.row][info1.col] - mat[info2.row][info2.col];
      }
    });
    for (int i = 0; i < n; i++) {
      minHeap.offer(new Info(i, 0));
    }
    int[] buf = new int[k];
    for (int i = 0; i < k ; i++) {
      Info min = minHeap.poll();
      buf[i] = mat[min.row][min.col];
      if (min.col + 1 < n) {
        minHeap.offer(new Info(min.row, min.col + 1));
      }
    }
    return buf[k - 1];
  }

//     // solution 3
//     // 估计主要是测试用例的问题（元素范围m小于k），solution 3的实际执行时间比solution 1快得多。
//     public int kthSmallest(int[][] matrix, int k) {
//         int[][] mat = matrix;
//         int n = mat.length;
//         // assume valid
//         int min = mat[0][0];
//         int max = mat[n - 1][n - 1];
//         while (min < max) {
//             int mid = min +  (max - min) / 2;
//             int leCnt = countLe(mat, mid);
//             if (leCnt >= k) {
//                 max = mid;
//             } else {
//                 min = mid + 1;
//             }
//         }
//         return max;
//     }

//     private int countLe(int[][] mat, int v) {
//         int n = mat.length;
//         int i = 0;
//         int j = n - 1;
//         int cnt = 0;
//         while (i < n && j >= 0) {
//             if (mat[i][j] <= v) {
//                 cnt += j + 1;
//                 i++;
//             } else {
//                 j--;
//             }
//         }
//         return cnt;
//     }
}
package com.msh.solutions._84_Largest_Rectangle_in_Histogram;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/4/12.
 */
public class Solution {
  // solution 1: 暴力，枚举每个bar，将当前bar作为最低bar，向两头扩展，记录最大长度，O(n^2)
  // solution -2: 最优化，求最大，设 dp[i] 为 “以当前bar为最低bar，左侧的最大面积” —— 并不能递推
  // 既然动态规划搞不定，就比较可能在遍历过程中维护神奇的状态来搞定
  // solution 2: 看 [题解](https://blog.csdn.net/doc_sgl/article/details/11805519)，dicuss里大多数是这种方法，过于trick。核心是维护单调 递增的栈（包括相等）. O(n)
  public int largestRectangleArea(int[] heights) {
    // assume valid
    if (heights.length == 0) {
      return 0;
    }
    if (heights.length == 1) {
      return heights[0];
    }

    // 最后插入一个高度为0的bar，以简化代码
    int n = heights.length;
    int[] newHs = Arrays.copyOf(heights, n + 1);
    newHs[n] = 0;
    n++;

    Stack<Integer> incHIdxs = new Stack<>();
    incHIdxs.push(0);
    int maxArea = newHs[0];
    for (int i = 1; i < n; i++) {
      while (!incHIdxs.empty() && newHs[i] < newHs[incHIdxs.peek()]) {
        int topHIdx = incHIdxs.pop();
        if (incHIdxs.empty()) {
          maxArea = Math.max(maxArea, newHs[topHIdx] * i);
          break;
        }
        int top2HIdx = incHIdxs.peek();
        maxArea = Math.max(maxArea, newHs[topHIdx] * (i - top2HIdx - 1));
      }
      incHIdxs.push(i);
    }
    return maxArea;
  }
}
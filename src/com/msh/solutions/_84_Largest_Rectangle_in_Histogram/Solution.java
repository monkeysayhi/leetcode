package com.msh.solutions._84_Largest_Rectangle_in_Histogram;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/4/12.
 */
public class Solution {
  // solution 1: 暴力，枚举bar作为最高bar，向两侧延伸得到当前最大矩形. O(n^2)
  // solution -2: 最优化，求最大，设 left[i] 为“从位置i向左延伸的最大长度”，则 left[i] = if heights[i] <= heights[i - 1]: left[i - 1] + 1 else 1，right[i]同理，最大面积 max = max{(left[i] + right[i] - 1) * heights[i]}. O(n)。。。但是这样是不对的，badcase: `heights[i] <= heights[i - 1]`时，可能`heights[i - 2] < heights[i - 1] && heights[i - 2] >= heights[i]`
  // solution 2: 看 [题解](https://blog.csdn.net/doc_sgl/article/details/11805519)，dicuss里大多数是这种方法，过于trick。核心是维护单调 递增的栈（包括相等）. O(n)
  public int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }

    int n = heights.length;
    int[] newHeights = Arrays.copyOf(heights, n + 1);
    // 最后插入一个高度为0的bar，以简化代码
    newHeights[n] = 0;
    n++;
    heights = newHeights;

    Stack<Integer> incHIdxs = new Stack<>();
    incHIdxs.push(0);
    int maxArea = heights[0];
    for (int i = 1; i < n; i++) {
      int topHIdx = incHIdxs.peek();
      while (heights[i] < heights[topHIdx]) {
        incHIdxs.pop();
        if (incHIdxs.isEmpty()) {
          // 栈顶元素一定是截止到位置topHIdx的最小元素
          int len = i;
          maxArea = Math.max(maxArea, len * heights[topHIdx]);
          break;
        }
        // 相等元素无影响
        int top2HIdx = incHIdxs.peek();
        int len = i - top2HIdx - 1;
        maxArea = Math.max(maxArea, len * heights[topHIdx]);
        topHIdx = top2HIdx;
      }
      incHIdxs.push(i);
    }

    return maxArea;
  }
}
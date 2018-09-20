package com.msh.solutions._42_Trapping_Rain_Water;

/**
 * Created by monkeysayhi on 2018/4/3.
 */
public class Solution {
  // 截止到每个木板的最大容量，只取决于“该木板左侧的第一个不低于的模板”和“该木板左侧的低于的最高模板”，注意优先顺序
  public int trap(int[] height) {
    if (height == null || height.length <= 1) {
      return 0;
    }

    int[] hs = height;
    int n = hs.length;
    int[] vs = new int[n];
    for (int i = 1; i < n; i++) {
      int higher = -1;
      int lowerHighest = -1;
      for (int j = i - 1; j >= 0; j--) {
        if (hs[j] >= hs[i]) {
          higher = j;
          break;
        } else if (lowerHighest == -1 || hs[j] > hs[lowerHighest]) {
          lowerHighest = j;
        }
      }

      int left = higher >= 0 ? higher : lowerHighest;
      int h = Math.min(hs[left], hs[i]);
      for (int j = i - 1; j > left; j--) {
        vs[j] = h - hs[j];
      }
      // 也可以定义 dp[i] 为“截止到位置i的最大容量和”，则 dp[i] = dp[left] + v(left, i)，答案 dp[n - 1]
    }

    int sumV = 0;
    for (int i = 0; i < n; i++) {
      sumV += vs[i];
    }

    return sumV;
  }
}
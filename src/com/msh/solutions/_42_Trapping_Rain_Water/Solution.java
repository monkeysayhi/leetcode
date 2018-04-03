package com.msh.solutions._42_Trapping_Rain_Water;

/**
 * Created by monkeysayhi on 2018/4/3.
 */
public class Solution {
  // 截止到每个目标的盛水量仅与“该木板左侧的最高木板”（O(1)）或“该木板左侧不低于该木板的最近一个木板”（O(n)）相关 + 短板原理，总时间O(n^2)
  public int trap(int[] height) {
    if (height == null || height.length <= 1) {
      return 0;
    }

    int n = height.length;
    int[] volumes = new int[n];
    volumes[0] = 0;
    int heighestIdx = 0;
    for (int i = 1; i < n; i++) {
      int lastHigherIdx = -1;
      for (int j = i - 1; j >= 0; j--) {
        if (height[j] >= height[i]) {
          lastHigherIdx = j;
          break;
        }
      }
      int lastIdx = Math.max(lastHigherIdx, heighestIdx);
      volumes[i] = volumes[lastIdx] + (i - lastIdx - 1) * Math.min(height[lastIdx], height[i]);
      for (int j = lastIdx + 1; j < i; j++) {
        volumes[i] -= height[j];
      }
      if (height[i] > height[heighestIdx]) {
        heighestIdx = i;
      }
    }
    return volumes[n - 1];
  }
}
package com.msh.solutions._11_Container_With_Most_Water;

/**
 * Created by monkeysayhi on 2018/1/25.
 */
public class Solution {
  // 求max{(j - i) * min{height[i], height[j]}}
  // 暴力，n(n^2)，奔着O(n)或O(nlogn)的方向想
  // 使其中一个因子保持递减或递增，保证另一个因子都扫描过即可，很自然想尝试双指针两头扫描
  // 关注短板:
  // 1. 如果形如[l...ansL...ansR...r]，则必然满足 min{l, r} < min{ansL, ansR}，则一定不取min{l, r}，假设最小是l，则l++
  // 2. 如果形如[ansL...l...r...ansR]，则之前一定发生过形如[l...ansL...ansR...r]的情况，必然已经扫描得到过最大值
  // 注意，如果height[l] == height[r]，则移动两边均可
  public int maxArea(int[] height) {
    int n = height.length;
    assert n >= 2;
    if (n == 2) {
      return Math.min(height[0], height[1]);
    }

    int maxArea = 0;
    int l = 0;
    int r = n - 1;
    while (l < r) {
      maxArea = Math.max(
          maxArea, (r - l) * Math.min(height[l], height[r]));
      if (height[l] < height[r]) {
        l++;
      } else {
        r--;
      }
    }
    return maxArea;
  }
}
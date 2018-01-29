package com.msh.solutions._315_Count_of_Smaller_Numbers_After_Self;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/1/29.
 */
public class Solution {
  private static class Info {
    private int index;
    private int val;

    private Info(int index, int val) {
      this.index = index;
      this.val = val;
    }
  }

  // 暴力，每个元素都扫描一遍数组，O(n^2)
  // 找低于O(n^2)的方法
  // 本质上在计算，对于每一个nums[i]，统计j的数量，满足 j > i && nums[j] < nums[i]，逆序对问题，O(nlogn)
  // 不过不是计算总数了，而是要记录对每个元素nums[i]的逆序对数量，封装一下元素和原始位置即可
  public List<Integer> countSmaller(int[] nums) {
    if (nums == null) {
      return null;
    }
    if (nums.length == 0) {
      return new ArrayList<>();
    }

    Info[] infos = new Info[nums.length];
    for (int i = 0; i < nums.length; i++) {
      infos[i] = new Info(i, nums[i]);
    }

    int[] counts = new int[nums.length];
    countReversePair(infos, 0, infos.length, counts);

    List<Integer> rs = new ArrayList<>();
    for (int cnt : counts) {
      rs.add(cnt);
    }
    return rs;
  }

  private void countReversePair(Info[] infos, int l, int r,
                                int[] counts) {
    if (l + 1 == r || l == r) {
      return;
    }

    int m = l + (r - l) / 2;
    countReversePair(infos, l, m, counts);
    countReversePair(infos, m, r, counts);
    merge(infos, l, m, r, counts);
  }

  private void merge(Info[] infos, int l, int m, int r,
                     int[] counts) {
    Info[] buf = new Info[r - l];
    int i = l;
    int j = m;
    int k = 0;
    // 逆序归并，将counts的修改摊分到每次判断上
    while (i < m && j < r) {
      if (infos[i].val > infos[j].val) {
        counts[infos[i].index] += r - j;
        buf[k++] = infos[i++];
      } else {
        buf[k++] = infos[j++];
      }
    }
    while (i < m) {
      buf[k++] = infos[i++];
    }
    while (j < r) {
      buf[k++] = infos[j++];
    }
    System.arraycopy(buf, 0, infos, l, buf.length);
  }
}
package com.msh.solutions._347_Top_K_Frequent_Elements;

import java.util.*;

/**
 * Created by monkeysayhi on 2018/1/23.
 */
public class Solution {
  private static class Info {
    private int val;
    private int cnt;

    Info(int val, int cnt) {
      this.val = val;
      this.cnt = cnt;
    }
  }

  // hashmap + heap
  public List<Integer> topKFrequent(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return new ArrayList<>();
    }

    Map<Integer, Info> stat = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (!stat.containsKey(nums[i])) {
        stat.put(nums[i], new Info(nums[i], 0));
      }
      stat.get(nums[i]).cnt++;
    }

    PriorityQueue<Info> minHeap = new PriorityQueue<>(k + 1, new Comparator<Info>() {
      public int compare(Info info1, Info info2) {
        return info1.cnt - info2.cnt;
      }
    });
    for (Info info : stat.values()) {
      minHeap.offer(info);
      if (minHeap.size() == k + 1) {
        minHeap.poll();
      }
    }

    Integer[] rs = new Integer[minHeap.size()];
    for (int i = minHeap.size() - 1; i >= 0; i--) {
      rs[i] = minHeap.poll().val;
    }
    return Arrays.asList(rs);
  }
}
package com.msh.solutions._621_Task_Scheduler;

import java.util.Arrays;

/**
 * Created by monkeysayhi on 2018/1/28.
 */
public class Solution {
  // solution 1: 画一画就懂了，变成模拟题，but没写出来
  // solution 2: 方向想偏了，参考题解https://leetcode.com/problems/task-scheduler/solution/的方法3
  // 不过这思路还是挺，，，放弃贪心法，这没法总结
  public int leastInterval(char[] tasks, int n) {
    if (tasks == null || tasks.length == 0) {
      return 0;
    }
    if (tasks.length == 1) {
      return 1;
    }
    if (n == 0) {
      return tasks.length;
    }

    int[] stat = new int[26];
    for (int task : tasks) {
      stat[task - 'A']++;
    }
    Arrays.sort(stat);

    int maxTaskCnt = stat[25];
    int slotCnt = (maxTaskCnt - 1) * n;
    for (int i = 24; i >= 0 && stat[i] > 0; i--) {
      if (stat[i] >= maxTaskCnt - 1) { // assert stat[i] <= maxTaskCnt
        slotCnt -= maxTaskCnt - 1;
      } else {
        slotCnt -= stat[i];
      }
    }
    if (slotCnt <= 0) {
      return tasks.length;
    }
    return tasks.length + slotCnt;
  }
}
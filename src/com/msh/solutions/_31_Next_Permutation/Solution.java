package com.msh.solutions._31_Next_Permutation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by monkeysayhi on 2017/12/11.
 */
public class Solution {

  private enum State {
    FOUND_NEXT, FOUND_CUR, NOT_FOUND
  }

  public void nextPermutation(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return;
    }

    int[] curPmt = Arrays.copyOf(nums, nums.length);
    Arrays.sort(nums);
    boolean reverse = true;
    for (int i = 1; i < curPmt.length; i++) {
      if (curPmt[i] > curPmt[i - 1]) {
        reverse = false;
      }
    }
    if (reverse) {
      return;
    }

    Map<Integer, Integer> vis = new HashMap<>();
    Map<Integer, Integer> stat = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (!stat.containsKey(nums[i])) {
        stat.put(nums[i], 0);
        vis.put(nums[i], 0);
      }
      stat.put(nums[i], 1 + stat.get(nums[i]));
    }
    int cur = 0;
    int[] pmt = new int[nums.length];
    backtrack(nums, curPmt, stat, vis, cur, pmt, State.NOT_FOUND);
    System.arraycopy(pmt, 0, nums, 0, nums.length);
  }

  private State backtrack(int[] nums, int[] curPmt,
                          Map<Integer, Integer> stat, Map<Integer, Integer> vis,
                          int cur,
                          int[] pmt, State state) {
    if (cur == nums.length) {
      if (state == State.FOUND_CUR) {
        return State.FOUND_NEXT;
      }
      return State.FOUND_CUR;
    }
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      if (vis.get(nums[i]) == stat.get(nums[i])) {
        continue;
      }
      if (state == State.NOT_FOUND && nums[i] < curPmt[cur]) {
        continue;
      }

      int chosenCnt = vis.get(nums[i]);
      pmt[cur] = nums[i];
      vis.put(nums[i], chosenCnt + 1);
      state = backtrack(nums, curPmt, stat, vis, cur + 1, pmt, state);
      if (state == State.FOUND_NEXT) {
        return State.FOUND_NEXT;
      }
      vis.put(nums[i], chosenCnt);
    }
    return state;
  }
}

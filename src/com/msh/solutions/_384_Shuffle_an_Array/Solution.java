package com.msh.solutions._384_Shuffle_an_Array;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by monkeysayhi on 2018/1/30.
 */
// 重点是shuffle的实现，详见注释，看如何通过swap代替remove，将时间从O(n^2)降到O(n)
public class Solution {
  private int[] origin;

  private Random random = new Random();

  public Solution(int[] nums) {
    this.origin = Arrays.copyOf(nums, nums.length);
  }

  /**
   * Resets the array to its original configuration and return it.
   */
  public int[] reset() {
    return Arrays.copyOf(origin, origin.length);
  }

  /**
   * Returns a random shuffling of the array.
   */
  // 使用O(1)的swap代替remove，时间降到O(n)
  public int[] shuffle() {
    int[] shuffle = Arrays.copyOf(origin, origin.length);
    for (int i = 0; i < shuffle.length; i++) {
      int randomIndex = i + random.nextInt(shuffle.length - i);
      swap(shuffle, i, randomIndex);
    }
    return shuffle;
  }

  private static void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  // // List#remove()是O(n)的，因此总时间O(n^2)
  // public int[] shuffle() {
  //     List<Integer> buf = new ArrayList<>(origin.length);
  //     for (int num : origin) {
  //         buf.add(num);
  //     }
  //     int[] shuffle = new int[origin.length];
  //     for (int i = 0; i < shuffle.length; i++) {
  //         // 此处randomIndex必须是int型，防止后面List#remove()的坑
  //         int randomIndex = (int) (Math.random() * buf.size());
  //         shuffle[i] = buf.get(randomIndex);
  //         buf.remove(randomIndex);
  //     }
  //     return shuffle;
  // }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
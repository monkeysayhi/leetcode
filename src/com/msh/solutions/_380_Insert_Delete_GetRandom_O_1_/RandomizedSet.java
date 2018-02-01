package com.msh.solutions._380_Insert_Delete_GetRandom_O_1_;

import java.util.*;

/**
 * Created by monkeysayhi on 2018/2/1.
 */
// 参考[384. Shuffle an Array](https://leetcode.com/problems/shuffle-an-array/description/)，通过swap达到O(1)时间随机删除
// 这题不好，看了discuss，都是用jdk的容器实现的，，，我以为要自己整，还想用布隆过滤器
public class RandomizedSet {
  private List<Integer> vals = new ArrayList<>();
  private Map<Integer, Integer> locs = new HashMap<>();
  private int size = 0;

  private Random random = new Random();

  /** Initialize your data structure here. */
  public RandomizedSet() {
    // TODO opt, lazy init
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (locs.containsKey(val)) {
      return false;
    }
    vals.add(val);
    locs.put(val, size);
    size++;
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (!locs.containsKey(val)) {
      return false;
    }

    int tailVal = vals.get(size - 1);
    int loc = locs.get(val);
    vals.set(loc, tailVal);
    locs.put(tailVal, loc);

    locs.remove(val);
    vals.remove(size - 1);
    size--;
    return true;
  }

  private void swap(int i, int j) {
    int tmp = vals.get(i);
    vals.set(i, vals.get(j));
    vals.set(j, tmp);
  }

  /** Get a random element from the set. */
  public int getRandom() {
    int loc = random.nextInt(size);
    return vals.get(loc);
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
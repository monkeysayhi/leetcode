package com.msh.solutions._155_Min_Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/2/5.
 */
// O(1)时间push、pop都简单
// 时间不够，空间来凑：O(1)时间min利用了stack单头进出的特性，转成动态规划，设dp[i]为截止到位置i的最小值，则dp[i] = min{dp[i - 1], nums[i]}
public class MinStack {
  private List<Integer> values;
  private List<Integer> mins;
  private int size;

  /**
   * initialize your data structure here.
   */
  public MinStack() {
    // TODO opt
    values = new ArrayList<>();
    mins = new ArrayList<>();
    size = 0;
  }

  public void push(int x) {
    // assume valid
    values.add(x);
    if (size == 0) {
      mins.add(x);
    } else {
      mins.add(Math.min(mins.get(size - 1), x));
    }
    size++;
  }

  public void pop() {
    // assume valid
    values.remove(size - 1);
    mins.remove(size - 1);
    size--;
  }

  public int top() {
    // assume valid
    return values.get(size - 1);
  }

  public int getMin() {
    // assume valid
    return mins.get(size - 1);
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
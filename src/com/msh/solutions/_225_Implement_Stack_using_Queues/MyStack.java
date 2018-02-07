package com.msh.solutions._225_Implement_Stack_using_Queues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by monkeysayhi on 2018/2/7.
 */
// 两个队列来回倒，倒的时候剩一个
public class MyStack {
  Queue<Integer> pushQueue;
  Queue<Integer> popQueue;

  /**
   * Initialize your data structure here.
   */
  public MyStack() {
    // TODO opt
    pushQueue = new LinkedList<>();
    popQueue = new LinkedList<>();
  }

  /**
   * Push element x onto stack.
   */
  public void push(int x) {
    pushQueue.offer(x);
  }

  /**
   * Removes the element on top of the stack and returns that element.
   */
  public int pop() {
    // TODO opt
    transferRemaining(pushQueue, popQueue, 1);
    int x = pushQueue.poll();
    transferRemaining(popQueue, pushQueue, 0);
    return x;
  }

  /**
   * Get the top element.
   */
  public int top() {
    // TODO opt
    transferRemaining(pushQueue, popQueue, 1);
    int x = pushQueue.peek();
    transferRemaining(pushQueue, popQueue, 0);
    transferRemaining(popQueue, pushQueue, 0);
    return x;
  }

  private static void transferRemaining(Queue<Integer> q1, Queue<Integer> q2, int remainingCnt) {
    remainingCnt = Math.max(0, remainingCnt);
    while (q1.size() > remainingCnt) {
      q2.offer(q1.poll());
    }
  }

  /**
   * Returns whether the stack is empty.
   */
  public boolean empty() {
    return pushQueue.isEmpty();
  }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
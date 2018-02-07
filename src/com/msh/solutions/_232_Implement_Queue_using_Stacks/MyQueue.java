package com.msh.solutions._232_Implement_Queue_using_Stacks;

import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/2/7.
 */
// 两个栈来回倒
public class MyQueue {
  private Stack<Integer> pushStack;
  private Stack<Integer> popStack;

  /**
   * Initialize your data structure here.
   */
  public MyQueue() {
    // TODO opt
    pushStack = new Stack<>();
    popStack = new Stack<>();
  }

  /**
   * Push element x to the back of queue.
   */
  public void push(int x) {
    pushStack.push(x);
  }

  /**
   * Removes the element from in front of queue and returns that element.
   */
  public int pop() {
    // TODO opt
    transfer(pushStack, popStack);
    int x = popStack.pop();
    transfer(popStack, pushStack);
    return x;
  }

  /**
   * Get the front element.
   */
  public int peek() {
    // TODO opt
    transfer(pushStack, popStack);
    int x = popStack.peek();
    transfer(popStack, pushStack);
    return x;
  }

  private static void transfer(Stack<Integer> s1, Stack<Integer> s2) {
    while (!s1.isEmpty()) {
      s2.push(s1.pop());
    }
  }

  /**
   * Returns whether the queue is empty.
   */
  public boolean empty() {
    return pushStack.isEmpty();
  }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
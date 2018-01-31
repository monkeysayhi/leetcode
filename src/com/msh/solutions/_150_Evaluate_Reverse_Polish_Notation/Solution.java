package com.msh.solutions._150_Evaluate_Reverse_Polish_Notation;

import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/1/31.
 */
public class Solution {
  // 需要知道反转波兰表达式的概念，，but我不知道。看维基后就简单了
  // 栈
  public int evalRPN(String[] tokens) {
    // assume valid
    Stack<Integer> numStack = new Stack<>();
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].equals("+") || tokens[i].equals("-")
          || tokens[i].equals("*") || tokens[i].equals("/")) {
        int v2 = numStack.pop();
        int v1 = numStack.pop();
        int v = evalOp(v1, v2, tokens[i]);
        numStack.push(v);
        continue;
      }
      numStack.push(Integer.valueOf(tokens[i]));
    }
    assert numStack.size() == 1;
    return numStack.pop();
  }

  private int evalOp(int v1, int v2, String op) {
    if (op.equals("+")) {
      return v1 + v2;
    }
    if (op.equals("-")) {
      return v1 - v2;
    }
    if (op.equals("*")) {
      return v1 * v2;
    }
    if (op.equals("/")) {
      return v1 / v2;
    }
    assert true;
    return -1;
  }
}
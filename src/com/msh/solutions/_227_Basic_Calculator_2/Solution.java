package com.msh.solutions._227_Basic_Calculator_2;

import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/1/30.
 */
public class Solution {
  // solution 1: 直接构建啥玩意树来着，加减号在第一层，乘除号在第二层，下面是操作数
  // 不过只有两层，用树有点杀鸡用牛刀了。可以仿照编译器一样用栈搞定
  // solution 2: 栈存储操作数，注意在读入加减号后，要检查下一个数后是否紧跟着乘除号
  public int calculate(String s) {
    char[] chars = s.replace(" ", "").toCharArray();
    Stack<Integer> numStack = new Stack<>();
    Stack<Character> opStack = new Stack<>();
    int i = 0;
    while (i < chars.length) {
      if (numStack.size() == 0) {
        i += readVal(chars, i, numStack);
        continue;
      }

      i += readOp(chars, i, opStack);
      char op = opStack.peek();
      i += readVal(chars, i, numStack);
      if (op == '+' || op == '-') {
        while (i < chars.length) {
          i += readOp(chars, i, opStack);
          char nextOp = opStack.peek();
          if (nextOp == '+' || nextOp == '-') {
            i -= recoverOp(opStack);
            break;
          }
          i += readVal(chars, i, numStack);
          cal(numStack, opStack);
        }
      }
      cal(numStack, opStack);
    }
    assert numStack.size() == 1 && opStack.size() == 0;
    return numStack.pop();
  }

  private int readVal(char[] chars, int offset, Stack<Integer> numStack) {
    int nextIntLen = nextIntLen(chars, offset);
    Integer nextInt = Integer.valueOf(new String(chars, offset, nextIntLen));
    numStack.push(nextInt);
    return nextIntLen;
  }

  private int readOp(char[] chars, int offset, Stack<Character> opStack) {
    opStack.push(chars[offset]);
    return 1;
  }

  private int recoverOp(Stack<Character> opStack) {
    opStack.pop();
    return 1;
  }

  private void cal(Stack<Integer> numStack, Stack<Character> opStack) {
    int v2 = numStack.pop();
    int v1 = numStack.pop();
    char op = opStack.pop();
    int v = evalOp(v1, v2, op);
    numStack.push(v);
  }

  private int nextIntLen(char[] chars, int offset) {
    int i = offset;
    for (; i < chars.length; i++) {
      if (!Character.isDigit(chars[i])) {
        break;
      }
    }
    return i - offset;
  }

  private int evalOp(int v1, int v2, char op) {
    if (op == '*') {
      return v1 * v2;
    }
    if (op == '/') {
      return v1 / v2;
    }
    if (op == '+') {
      return v1 + v2;
    }
    if (op == '-') {
      return v1 - v2;
    }
    assert true;
    return -1;
  }
}
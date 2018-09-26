package com.msh.solutions._227_Basic_Calculator_2;

import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/1/30.
 */
class Solution {
  // solution 1: 直接构建啥玩意树来着，加减号在第一层，乘除号在第二层，下面是操作数
  // 不过只有两层，用树有点杀鸡用牛刀了。可以仿照编译器一样用栈搞定
  // solution 2: 栈存储操作数，注意在读入加减号后，要检查下一个数后是否紧跟着乘除号
  public int calculate(String s) {
    s = s.replace(" ", "");
    if (s.startsWith("+") || s.startsWith("-")) {
      s = "0" + s;
    }

    String[] numStrs = s.split("\\+|-|\\*|/");
    if (numStrs.length == 1) {
      return Integer.valueOf(numStrs[0]);
    }

    int n = numStrs.length;
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.valueOf(numStrs[i]);
    }
    char[] ops = new char[n];
    for (int i = 1, j = 0; i < n; i++, j++) {
      while (s.charAt(j) != '+' && s.charAt(j) != '-' && s.charAt(j) != '*' && s.charAt(j) != '/') {
        j++;
      }
      ops[i] = s.charAt(j);
    }

    Stack<Integer> numStack = new Stack<>();
    numStack.push(nums[0]);
    int i = 1;
    while (i < n) {
      int num1 = numStack.pop();
      int num2 = nums[i];
      char op = ops[i];
      int ans = 0;
      if (op == '+' || op == '-') {
        if (i + 1 < n && (ops[i + 1] == '*' || ops[i + 1] == '/')) {
          char opBak = op;
          numStack.push(num1);
          numStack.push(nums[i]);
          while (i + 1 < n && (ops[i + 1] == '*' || ops[i + 1] == '/')) {
            i++;
            num1 = numStack.pop();
            num2 = nums[i];
            op = ops[i];
            ans = eval(num1, num2, op);
            numStack.push(ans);
          }
          num2 = numStack.pop();
          num1 = numStack.pop();
          op = opBak;
        }
      }
      ans = eval(num1, num2, op);
      numStack.push(ans);
      i++;
    }
    return numStack.pop();
  }

  private int eval(int num1, int num2, char op) {
    if (op == '+') {
      return num1 + num2;
    } else if (op == '-') {
      return num1 - num2;
    } else if (op == '*') {
      return num1 * num2;
    } else { // op == '/'
      return num1 / num2;
    }
  }
}
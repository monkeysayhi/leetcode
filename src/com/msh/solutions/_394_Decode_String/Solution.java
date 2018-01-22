package com.msh.solutions._394_Decode_String;

import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/1/22.
 */
public class Solution {
  // 栈，O(n^2)
  public String decodeString(String s) {
    if (s == null) {
      return null;
    }

    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      stack.push(s.charAt(i));
      if (stack.peek() == ']') {
        String group = popOneGroup(stack);
        String decodedStr = decodeOneGroup(group);
        pushDecodedStr(stack, decodedStr);
      }
    }

    StringBuilder sb = new StringBuilder();
    for (Character ch : stack) {
      sb.append(ch);
    }
    return sb.toString();
  }

  private String popOneGroup(Stack<Character> stack) {
    StringBuilder reversedSb = new StringBuilder();
    while (!stack.isEmpty() && !isDigit(stack.peek())) {
      reversedSb.append(stack.pop());
    }
    while (!stack.isEmpty() && isDigit(stack.peek())) {
      reversedSb.append(stack.pop());
    }
    return reversedSb.reverse().toString();
  }

  private String decodeOneGroup(String group) {
    StringBuilder cntSb = new StringBuilder();
    for (int i = 0; i < group.length() && isDigit
        (group.charAt(i)); i++) {
      cntSb.append(group.charAt(i));
    }
    int cnt = Integer.valueOf(cntSb.toString());
    String subStr = group.substring(cntSb.length() + 1, group.length() - 1);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < cnt; i++) {
      sb.append(subStr);
    }
    return sb.toString();
  }

  private void pushDecodedStr(Stack<Character> stack, String str) {
    for (int i = 0; i < str.length(); i++) {
      stack.push(str.charAt(i));
    }
  }

  private boolean isDigit(Character ch) {
    return ch >= '0' && ch <= '9';
  }
}
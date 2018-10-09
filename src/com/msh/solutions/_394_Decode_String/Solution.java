package com.msh.solutions._394_Decode_String;

import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/1/22.
 */
public class Solution {
  // solution 1: 栈，一个栈存待decode的半组或一组，每次pop一组，decode，再push，O(n^2)
  // solution 2: 栈，一个栈存倍数，一个栈存编码串，O(n)

  // solution 2
  public String decodeString(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }
    char[] chars = s.toCharArray();
    Stack<Integer> numStack = new Stack<>();
    Stack<String> strStack = new Stack<>();
    StringBuilder strBuf = new StringBuilder();
    StringBuilder intBuf = null;
    for (int i = 0; i < chars.length; i++) {
      char ch = chars[i];
      if (ch == '[') {
        assert intBuf != null;
        numStack.push(Integer.valueOf(intBuf.toString()));
        intBuf = null;
        continue;
      }
      if (ch == ']') {
        // decode
        assert intBuf == null;
        assert strBuf.length() > 0;
        assert numStack.size() > 0;
        int num = numStack.pop();
        StringBuilder decodedSb = new StringBuilder();
        if (numStack.size() > 0) {
          assert strStack.size() > 0;
          decodedSb.append(strStack.pop());
        }
        for (int j = 0; j < num; j++) {
          decodedSb.append(strBuf);
        }
        strBuf = decodedSb;
        continue;
      }
      if (ch >= '0' && ch <= '9') {
        if (intBuf == null) {
          intBuf = new StringBuilder();
        }
        intBuf.append(ch);
        if (intBuf.length() == 1) {
          strStack.push(strBuf.toString());
          // 将 strBuf 置为空串，以简化case
          strBuf = new StringBuilder();
        }
        continue;
      }
      strBuf.append(ch);
    }
    StringBuilder rs = new StringBuilder();
    for (String str : strStack) {
      rs.append(str);
    }
    rs.append(strBuf.toString());
    return rs.toString();
  }

//   // solution 1
//   public String decodeString(String s) {
//     if (s == null) {
//       return null;
//     }
//
//     Stack<Character> stack = new Stack<>();
//     for (int i = 0; i < s.length(); i++) {
//       stack.push(s.charAt(i));
//       if (stack.peek() == ']') {
//         String group = popOneGroup(stack);
//         String decodedStr = decodeOneGroup(group);
//         pushDecodedStr(stack, decodedStr);
//       }
//     }
//
//     StringBuilder sb = new StringBuilder();
//     for (Character ch : stack) {
//       sb.append(ch);
//     }
//     return sb.toString();
//   }
//
//   private String popOneGroup(Stack<Character> stack) {
//     StringBuilder reversedSb = new StringBuilder();
//     while (!stack.isEmpty() && !isDigit(stack.peek())) {
//       reversedSb.append(stack.pop());
//     }
//     while (!stack.isEmpty() && isDigit(stack.peek())) {
//       reversedSb.append(stack.pop());
//     }
//     return reversedSb.reverse().toString();
//   }
//
//   private String decodeOneGroup(String group) {
//     StringBuilder cntSb = new StringBuilder();
//     for (int i = 0; i < group.length() && isDigit
//         (group.charAt(i)); i++) {
//       cntSb.append(group.charAt(i));
//     }
//     int cnt = Integer.valueOf(cntSb.toString());
//     String subStr = group.substring(cntSb.length() + 1, group.length() - 1);
//
//     StringBuilder sb = new StringBuilder();
//     for (int i = 0; i < cnt; i++) {
//       sb.append(subStr);
//     }
//     return sb.toString();
//   }
//
//   private void pushDecodedStr(Stack<Character> stack, String str) {
//     for (int i = 0; i < str.length(); i++) {
//       stack.push(str.charAt(i));
//     }
//   }
//
//   private boolean isDigit(Character ch) {
//     return ch >= '0' && ch <= '9';
//   }
}
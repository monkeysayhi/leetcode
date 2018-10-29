package com.msh.solutions._394_Decode_String;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/1/22.
 */
class Solution {
  // solution 1: 栈，一个栈存待decode的半组或一组，遇到']'就解析，解析后塞回去，O(n)
  // solution 2: 栈，一个栈存倍数，一个栈存编码串，O(n)，但系数更小

  // solution 1
  public String decodeString(String s) {
    // assume valid
    if (s.length() == 0) {
      return s;
    }
    Stack<Character> stk = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == ']') {
        List<Character> unit = popUnit(stk);
        assert stk.pop() == '[';
        int times = popTimes(stk);
        for (int j = 0; j < times; j++) {
          for (char uC : unit) {
            stk.push(uC);
          }
        }
      } else {
        stk.push(c);
      }
    }

    StringBuilder rs = new StringBuilder();
    for (char c : stk) {
      rs.append(c);
    }
    return rs.toString();
  }

  private List<Character> popUnit(Stack<Character> s) {
    Stack<Character> reversedRs = new Stack<>();
    while (true) {
      char c = s.pop();
      if (c == '[') {
        s.push(c);
        break;
      }
      reversedRs.push(c);
    }
    List<Character> rs = new ArrayList<>(reversedRs.size());
    int cnt = reversedRs.size();
    for (int i = 0; i < cnt; i++) {
      rs.add(reversedRs.pop());
    }
    return rs;
  }

  private int popTimes(Stack<Character> s) {
    Stack<Character> reversedRs = new Stack<>();
    while (s.size() > 0) {
      char c = s.pop();
      if (!(c >= '0' && c <= '9')) {
        s.push(c);
        break;
      }
      reversedRs.push(c);
    }
    StringBuilder rs = new StringBuilder();
    int cnt = reversedRs.size();
    for (int i = 0; i < cnt; i++) {
      rs.append(reversedRs.pop());
    }
    return Integer.valueOf(rs.toString());
  }

  // // solution 2
  // public String decodeString(String s) {
  //     if (s == null || s.length() == 0) {
  //         return s;
  //     }
  //     char[] chars = s.toCharArray();
  //     Stack<Integer> numStack = new Stack<>();
  //     Stack<String> strStack = new Stack<>();
  //     StringBuilder strBuf = new StringBuilder();
  //     StringBuilder intBuf = null;
  //     for (int i = 0; i < chars.length; i++) {
  //         char ch = chars[i];
  //         if (ch == '[') {
  //             assert intBuf != null;
  //             numStack.push(Integer.valueOf(intBuf.toString()));
  //             intBuf = null;
  //             continue;
  //         }
  //         if (ch == ']') {
  //             // decode
  //             assert intBuf == null;
  //             assert strBuf.length() > 0;
  //             assert numStack.size() > 0;
  //             int num = numStack.pop();
  //             StringBuilder decodedSb = new StringBuilder();
  //             if (numStack.size() > 0) {
  //                 assert strStack.size() > 0;
  //                 decodedSb.append(strStack.pop());
  //             }
  //             for (int j = 0; j < num; j++) {
  //                 decodedSb.append(strBuf);
  //             }
  //             strBuf = decodedSb;
  //             continue;
  //         }
  //         if (ch >= '0' && ch <= '9') {
  //             if (intBuf == null) {
  //                 intBuf = new StringBuilder();
  //             }
  //             intBuf.append(ch);
  //             if (intBuf.length() == 1) {
  //                 strStack.push(strBuf.toString());
  //                 // 将 strBuf 置为空串，以简化case
  //                 strBuf = new StringBuilder();
  //             }
  //             continue;
  //         }
  //         strBuf.append(ch);
  //     }
  //     StringBuilder rs = new StringBuilder();
  //     for (String str : strStack) {
  //         rs.append(str);
  //     }
  //     rs.append(strBuf.toString());
  //     return rs.toString();
  // }
}
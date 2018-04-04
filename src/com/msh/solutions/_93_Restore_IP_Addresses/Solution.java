package com.msh.solutions._93_Restore_IP_Addresses;

import java.util.*;

/**
 * Created by monkeysayhi on 2018/4/4.
 */
public class Solution {
  // 回溯：枚举划分点 + 各种剪枝
  public List<String> restoreIpAddresses(String s) {
    if (s == null || s.length() < 4 || s.length() > 12) {
      return new ArrayList<>();
    }
    char[] chars = s.toCharArray();
    Stack<Integer> buf = new Stack<>();
    List<String> rs = new LinkedList<>();
    backtrack(chars, 1, buf, rs);
    return rs;
  }

  private void backtrack(char[] chars, int split,
                         Stack<Integer> buf, List<String> rs) {

    if (buf.size() == 3) {
      StringBuilder sb = new StringBuilder();
      buf.push(chars.length);
      int lastSplit = 0;
      boolean legal = true;
      for (int i = 0; i < buf.size(); i++) {
        int nextSplit = buf.get(i);
        String str = new String(chars, lastSplit, nextSplit - lastSplit);
        Integer num = Integer.valueOf(str);
        if (num < 0 || num > 255 || !str.equals(num.toString())) {
          legal = false;
          break;
        }
        sb.append(str);
        if (i < buf.size() - 1) {
          sb.append(".");
        }
        lastSplit = nextSplit;
      }
      buf.pop();
      if (legal) {
        rs.add(sb.toString());
      }
      return;
    }
    if (split == chars.length) {
      return;
    }
    if (chars.length - split + 1 < (4 - buf.size())
        || chars.length - split + 1 > 3 * (4 - buf.size())) {
      return;
    }

    buf.push(split);
    backtrack(chars, split + 1, buf, rs);
    buf.pop();
    backtrack(chars, split + 1, buf, rs);
  }
}
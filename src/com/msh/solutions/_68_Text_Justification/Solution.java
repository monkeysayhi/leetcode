package com.msh.solutions._68_Text_Justification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/9/23.
 */
public class Solution {
  // 模拟
  public List<String> fullJustify(String[] words, int maxWidth) {
    // assume valid
    List<String> rs = new ArrayList<>();
    int n = words.length;
    for (int i = 0; i < n; i++) {

      int len = words[i].length();
      int cnt = 1;
      while (i + cnt < n && len + words[i + cnt].length() + cnt <= maxWidth) {
        len += words[i + cnt].length();
        cnt++;
      }

      StringBuilder sb = new StringBuilder();
      sb.append(words[i]);
      if (i + cnt == n) {
        for (int j = 1; j < cnt; j++) {
          sb.append(' ');
          sb.append(words[i + j]);
        }
      } else {
        int leftSpaceCnt = maxWidth - len;
        for (int j = 1; j < cnt; j++) {
          int spaceCnt = leftSpaceCnt / (cnt - j);
          if (leftSpaceCnt % (cnt - j) != 0) {
            spaceCnt++;
          }
          leftSpaceCnt -= spaceCnt;
          while (spaceCnt-- > 0) {
            sb.append(' ');
          }
          sb.append(words[i + j]);
        }
      }
      if (sb.length() < maxWidth) {
        int spaceCnt = maxWidth - sb.length();
        while (spaceCnt-- > 0) {
          sb.append(' ');
        }
      }

      rs.add(sb.toString());
      i += cnt - 1;
    }
    return rs;
  }
}
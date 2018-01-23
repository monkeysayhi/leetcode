package com.msh.solutions._22_Generate_Parentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/1/23.
 */
public class Solution {
  // 枚举小括号表达式：利用了都是小括号的特点，回溯时只要先放左括号，再放右括号，且左括号的总数量与右括号相等，表达式就一定是匹配的
  public List<String> generateParenthesis(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }

    List<String> rs = new ArrayList<>();
    char[] buf = new char[n * 2];
    backtrack(n, 0, 0, 0, buf, rs);
    return rs;
  }

  private void backtrack(int pairCnt, int leftP, int rightP,
                         int cur, char[] buf,
                         List<String> rs) {
    if (leftP == pairCnt && rightP == pairCnt) {
      assert cur == pairCnt * 2;
      rs.add(new StringBuilder().append(buf).toString());
    }

    if (leftP < pairCnt) {
      buf[cur] = '(';
      backtrack(pairCnt, leftP + 1, rightP, cur + 1, buf, rs);
    }
    if (rightP < pairCnt && leftP > rightP) {
      buf[cur] = ')';
      backtrack(pairCnt, leftP, rightP + 1, cur + 1, buf, rs);
    }
  }
}
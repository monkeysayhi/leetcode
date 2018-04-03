package com.msh.solutions._60_Permutation_Sequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/4/3.
 */
public class Solution {
  // solution 1: 回溯枚举出前k个，O(n!)
  // solution 2: 实际上每一位都在“循环”，用3的例子试一下就能看出来，从而直接构造出第k个
  public String getPermutation(int n, int k) {
    if (n == 1) {
      return "1";
    }

    List<Integer> left = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      left.add(i);
    }
    StringBuilder rs = new StringBuilder();
    while (left.size() > 0) {
      int pCnt = jiecheng(left.size() - 1);
      int toRemovedNo = (k - 1) / pCnt;
      k = k - (toRemovedNo * pCnt);
      assert k >= 1;
      rs.append(left.get(toRemovedNo));
      left.remove(toRemovedNo);
    }
    return rs.toString();
  }

  private int jiecheng(int n) {
    int pro = 1;
    while (n > 1) {
      pro *= n--;
    }
    return pro;
  }
}
package com.msh.solutions._89_Gray_Code;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/4/4.
 */
public class Solution {
  // 公式：第n个格雷码 = n ^ (n >> 1)
  public List<Integer> grayCode(int n) {
    int cnt = 1 << n;
    List<Integer> rs = new ArrayList<>(cnt);
    for (int i = 0; i < cnt; i++) {
      rs.add(i ^ (i >> 1));
    }
    return rs;
  }
}
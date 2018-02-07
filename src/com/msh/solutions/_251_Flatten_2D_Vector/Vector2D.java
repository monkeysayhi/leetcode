package com.msh.solutions._251_Flatten_2D_Vector;

import java.util.Iterator;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/2/7.
 */
// 分别记录listIndex、valIndex
public class Vector2D implements Iterator<Integer> {
  private List<List<Integer>> lists;
  private int listIndex;
  private int valIndex;

  private int cur;
  private final int size;

  public Vector2D(List<List<Integer>> vec2d) {
    lists = vec2d;
    listIndex = 0;
    valIndex = 0;
    cur = 0;
    // TODO opt
    size = cntSize();
  }

  private int cntSize() {
    int cnt = 0;
    for (List<Integer> list : lists) {
      cnt += list.size();
    }
    return cnt;
  }

  @Override
  public Integer next() {
    // assume valid
    while (valIndex >= lists.get(listIndex).size()) {
      listIndex++;
      valIndex = 0;
    }
    int x = lists.get(listIndex).get(valIndex);
    valIndex++;
    cur++;
    return x;
  }

  @Override
  public boolean hasNext() {
    return cur < size;
  }

  @Override
  public void remove() {
  }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
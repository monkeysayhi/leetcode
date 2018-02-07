package com.msh.solutions._281_Zigzag_Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/2/7.
 */
// 分别记录listIndex、valIndex
public class ZigzagIterator {
  private List<List<Integer>> lists;
  private int listIndex;
  private int valIndex;

  private int cur;
  private final int size;

  /*
  * @param v1: A 1d vector
  * @param v2: A 1d vector
  */
  public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
    // TODO opt
    lists = new ArrayList<>(2);
    lists.add(v1);
    lists.add(v2);
    listIndex = 0;
    cur = 0;
    size = cntSize();
  }

  private int cntSize() {
    int cnt = 0;
    for (List<Integer> list : lists) {
      cnt += list.size();
    }
    return cnt;
  }

  /*
   * @return: An integer
   */
  public int next() {
    // assume valid
    while (valIndex >= lists.get(listIndex).size()) {
      nextIndex();
    }
    int x = lists.get(listIndex).get(valIndex);
    nextIndex();
    cur++;
    return x;
  }

  private void nextIndex() {
    listIndex++;
    if (listIndex == lists.size()) {
      listIndex = 0;
      valIndex++;
    }
  }

  /*
   * @return: True if has next
   */
  public boolean hasNext() {
    return cur < size;
  }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator solution = new ZigzagIterator(v1, v2);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */
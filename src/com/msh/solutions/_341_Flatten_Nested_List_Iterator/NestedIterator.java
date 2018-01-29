package com.msh.solutions._341_Flatten_Nested_List_Iterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/1/30.
 */

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {
  private List<NestedInteger> nesteds;

  private int cur = 0;
  private final int size;
  private Stack<Iterator<NestedInteger>> stack;

  public NestedIterator(List<NestedInteger> nestedList) {
    this.nesteds = nestedList;
    // TODO opt
    size = size(this.nesteds);
    stack = new Stack<>();
    stack.push(this.nesteds.iterator());
  }

  @Override
  public Integer next() {
    if (!hasNext()) {
      throw new RuntimeException("NoSuchElementException");
    }

    NestedInteger nested;
    Integer val = null;
    while (!stack.isEmpty()) {
      Iterator<NestedInteger> it = stack.peek();
      if (it.hasNext()) {
        nested = it.next();
        if (nested.isInteger()) {
          val = nested.getInteger();
          cur++;
          break;
        }
        stack.push(nested.getList().iterator());
        continue;
      }
      stack.pop();
    }
    return val;
  }

  @Override
  public void remove() {
    throw new NotImplementedException();
  }

  @Override
  public boolean hasNext() {
    return cur < size;
  }

  private static int size(List<NestedInteger> nestedList) {
    if (nestedList.size() == 0) {
      return 0;
    }
    int size = 0;
    for (NestedInteger nested : nestedList) {
      if (nested.isInteger()) {
        size++;
        continue;
      }
      size += size(nested.getList());
    }
    return size;
  }

  private static boolean isEmpty(List<NestedInteger> nestedList) {
    if (nestedList.size() == 0) {
      return true;
    }
    for (NestedInteger nested : nestedList) {
      if (!isEmpty(nested)) {
        return false;
      }
    }
    return true;
  }

  private static boolean isEmpty(NestedInteger nested) {
    if (nested.isInteger()) {
      return false;
    }
    return isEmpty(nested.getList());
  }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
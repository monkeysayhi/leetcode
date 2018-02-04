package com.msh.solutions._284_Peeking_Iterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

/**
 * Created by monkeysayhi on 2018/2/4.
 */
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
// 看题解才明白：提前next，使用peek记录该次next的结果
public class PeekingIterator implements Iterator<Integer> {
  private Iterator<Integer> it;
  private Integer peek = null;

  public PeekingIterator(Iterator<Integer> iterator) {
    this.it = iterator;
    next();
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    return peek;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    Integer curPeek = peek;
    if (it.hasNext()) {
      peek = it.next();
    } else {
      peek = null;
    }
    return curPeek;
  }

  @Override
  public void remove() {
    throw new NotImplementedException();
  }

  @Override
  public boolean hasNext() {
    return it.hasNext() || peek != null;
  }
}
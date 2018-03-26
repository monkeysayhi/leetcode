package com.msh.solutions._61_Rotate_List;

/**
 * Created by monkeysayhi on 2018/3/26.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  // 没办法快慢指针，以为不确定k的值是否大于len，要先遍历一遍算出len，`k %= len`后再算
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || k == 0 || head.next == null) {
      return head;
    }
    int len = length(head);
    k = k % len;
    int newHeadNo = (len - k) % len; // from 0
    ListNode newHeadPrev = null;
    ListNode newHead = head;
    for (int i = 0; i < newHeadNo; i++) {
      newHeadPrev = newHead;
      newHead = newHead.next;
    }
    if (newHead == head) {
      return head;
    }
    ListNode tail = newHead;
    while (tail.next != null) {
      tail = tail.next;
    }
    newHeadPrev.next = null;
    tail.next = head;
    return newHead;
  }

  private int length(ListNode head) {
    int len = 0;
    while (head != null) {
      head = head.next;
      len++;
    }
    return len;
  }
}
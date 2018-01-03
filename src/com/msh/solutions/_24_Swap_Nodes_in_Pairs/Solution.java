package com.msh.solutions._24_Swap_Nodes_in_Pairs;

/**
 * Created by monkeysayhi on 2018/1/2.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  // 反转 + dummy
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode lastTail = dummy;
    while (lastTail.next != null) {
      ListNode[] subList = reverse(lastTail, 2);
      lastTail = subList[1];
    }
    return dummy.next;
  }

  private ListNode[] reverse(ListNode headPrev, int len) {
    assert headPrev != null && headPrev.next != null;
    ListNode head = headPrev.next;

    ListNode subHeadPrev = headPrev;
    ListNode subTail = head;
    ListNode prev = null;
    ListNode cur = head;
    for (int i = 0; i < len && cur != null; i++) {
      ListNode next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }
    ListNode subHead = prev;
    ListNode subTailNext = cur;
    subHeadPrev.next = subHead;
    subTail.next = subTailNext;
    return new ListNode[]{subHead, subTail};
  }
}

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
  // 模拟 + dummy
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pair = dummy;
    while (pair.next != null && pair.next.next != null) {
      ListNode subHeadPrev = pair;
      ListNode subTail = pair.next;
      ListNode prev = null;
      ListNode cur = pair.next;
      for (int i = 0; i < 2; i++) {
        ListNode next = cur.next;
        cur.next = prev;
        prev = cur;
        cur = next;
      }
      ListNode subHead = prev;
      ListNode subTailNext = cur;
      subHeadPrev.next = subHead;
      subTail.next = subTailNext;
      pair = subTail;
    }
    return dummy.next;
  }
}

package com.msh.solutions._206_Reverse_Linked_List;

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
  // 简单模拟
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode prev = null;
    ListNode cur = head;
    while (cur != null) {
      ListNode next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }
    return prev;
  }
}

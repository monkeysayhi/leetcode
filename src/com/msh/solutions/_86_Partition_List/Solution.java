package com.msh.solutions._86_Partition_List;

/**
 * Created by monkeysayhi on 2018/4/4.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  // 链表partition
  public ListNode partition(ListNode head, int x) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode lt = dummy;
    ListNode prev = dummy;
    while (prev.next != null) {
      ListNode cur = prev.next;
      if (cur.val >= x) {
        prev = prev.next;
        continue;
      }
      if (lt == prev) {
        lt = lt.next;
        prev = prev.next;
      } else {
        prev.next = cur.next;
        cur.next = lt.next;
        lt.next = cur;
        lt = lt.next;
      }
    }
    return dummy.next;
  }
}
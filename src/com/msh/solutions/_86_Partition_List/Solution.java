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
  // 链表上的 partition。注意保持稳定
  public ListNode partition(ListNode head, int x) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode lt = dummy;
    ListNode ge = dummy;
    ListNode cur = head;
    while (cur != null) {
      int val = cur.val;
      if (val < x) {
        if (lt == ge) {
          lt = lt.next;
          ge = ge.next;
        } else {
          ge.next = cur.next;
          cur.next = lt.next;
          lt.next = cur;
          lt = lt.next;
        }
      } else {
        ge = ge.next;
      }
      cur = ge.next;
    }
    return dummy.next;
  }
}
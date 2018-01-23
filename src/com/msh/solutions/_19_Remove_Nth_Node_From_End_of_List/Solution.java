package com.msh.solutions._19_Remove_Nth_Node_From_End_of_List;

/**
 * Created by monkeysayhi on 2018/1/23.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  // 快慢指针：起始位置相差n
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || head.next == null) {
      return null;
    }
    if (n <= 0) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode fast = dummy;
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }
    ListNode slow = dummy;
    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }

    slow.next = slow.next.next;
    return dummy.next;
  }
}
package com.msh.solutions._203_Remove_Linked_List_Elements;

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
  public ListNode removeElements(ListNode head, int val) {
    if (head == null) {
      return null;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    while (prev.next != null) {
      ListNode node = prev.next;
      if (node.val == val) {
        prev.next = node.next;
        continue;
      }
      prev = prev.next;
    }
    return dummy.next;
  }
}
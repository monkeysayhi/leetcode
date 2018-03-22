package com.msh.solutions._82_Remove_Duplicates_from_Sorted_List_2;

/**
 * Created by monkeysayhi on 2018/3/22.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  // 双指针，遍历
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    boolean skip = false;
    for (ListNode next = dummy.next.next; next != null; next = next.next) {
      if (prev.next.val == next.val) {
        skip = true;
        continue;
      }
      if (skip) {
        prev.next = next;
        skip = false;
      } else {
        prev = prev.next;
      }
    }
    if (skip) {
      prev.next = null;
    }
    return dummy.next;
  }
}
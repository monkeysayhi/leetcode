package com.msh.solutions._141_Linked_List_Cycle;

/**
 * Created by monkeysayhi on 2018/1/3.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class Solution {
  // solution1：set记录访问过的节点，存在重复访问的节点证明有环
  // solution2：快慢指针，相遇即有环
  public boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    ListNode slow = head;
    // 与求中点不同，fast要从head开始
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      // 移动一次后再判断。因为刚进入链表时也是相等的
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }
}
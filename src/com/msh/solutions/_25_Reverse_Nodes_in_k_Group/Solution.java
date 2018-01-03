package com.msh.solutions._25_Reverse_Nodes_in_k_Group;

/**
 * Created by monkeysayhi on 2018/1/3.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  // 反转 + dummy node
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || head.next == null || k <= 1) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode lastGroupTail = dummy;
    while (checkLenGe(lastGroupTail.next, k)) {
      lastGroupTail = reverse(lastGroupTail, k)[1];
    }
    return dummy.next;
  }

  private boolean checkLenGe(ListNode head, int maxLen) {
    int len = 0;
    while (len < maxLen && head != null) {
      len++;
      head = head.next;
    }
    if (len == maxLen) {
      return true;
    }
    return false;
  }

  private ListNode[] reverse(ListNode headPrev, int len) {
    assert headPrev != null && headPrev.next != null;
    ListNode head = headPrev.next;
    if (len <= 1) {
      return new ListNode[]{head, head};
    }

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

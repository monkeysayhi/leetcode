package com.msh.solutions._148_Sort_List;

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
  // 时间O(nlogn)，空间O(1)，常用归并排序、堆排等
  public ListNode sortList(ListNode head) {
    return mergeSort(head);
  }

  private ListNode mergeSort(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode mid = findMiddle(head);
    ListNode right = mergeSort(mid.next);
    mid.next = null;
    ListNode left = mergeSort(head);
    return merge2SortedList(left, right);
  }

  private ListNode findMiddle(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  private ListNode merge2SortedList(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        tail.next = l1;
        l1 = l1.next;
      } else {
        tail.next = l2;
        l2 = l2.next;
      }
      tail = tail.next;
    }
    if (l1 != null) {
      tail.next = l1;
    }
    if (l2 != null) {
      tail.next = l2;
    }
    return dummy.next;
  }
}

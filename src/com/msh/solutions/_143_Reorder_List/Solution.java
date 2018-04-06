package com.msh.solutions._143_Reorder_List;

/**
 * Created by monkeysayhi on 2018/4/6.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  // solution 1: 看题解了，其实很简单。将后半部分逆序，然后交叉合并前后两部分
  public void reorderList(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return;
    }
    ListNode mid = findMid(head);
    ListNode midNext = mid.next;
    mid.next = null;
    ListNode reversedHead2 = reverse(midNext);
    crossingMerge(head, reversedHead2);
  }

  private ListNode findMid(ListNode head) {
    // TODO: make sure the accurate pos of mid
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  private ListNode reverse(ListNode head) {
    if (head.next == null) {
      return head;
    }
    ListNode prev = null;
    ListNode cur = head;
    while (cur != null) {
      // backup
      ListNode next = cur.next;
      // reverse
      cur.next = prev;
      // iter
      prev = cur;
      cur = next;
    }
    return prev;
  }

  private void crossingMerge(ListNode head1, ListNode head2) {
    while (head1 != null && head2 != null) {
      // backup
      ListNode head1Next = head1.next;
      ListNode head2Next = head2.next;
      // crossing merge
      head2.next = head1.next;
      head1.next = head2;
      // iter
      head1 = head1Next;
      head2 = head2Next;
    }
  }

//     private ListNode curL = null;
//     // solution -1: 利用递归栈构造逆序，同时利用成员变量构造顺序
//     public void reorderList(ListNode head) {
//         if (head == null || head.next == null || head.next.next == null) {
//             return;
//         }
//         this.curL = head;
//         reorderListInt(head.next);
//     }

//     // 完成构造后，返回true，否则返回false
//     private boolean reorderListInt(ListNode prevR) {
//         if (prevR.next.next == null) {
//             // reorder
//             ListNode curR = prevR.next;
//             curR.next = this.curL.next;
//             this.curL.next = curR;
//             // iter
//             this.curL = this.curL.next.next;
//             prevR.next = null;
//             return false;
//         }
//         if (reorderListInt(prevR.next)) {
//             return true;
//         }
//         if (this.curL == prevR) {
//             return true;
//         }
//         if (this.curL.next == prevR) {
//             // reorder
//             ListNode curR = prevR.next;
//             curR.next = this.curL.next;
//             this.curL.next = curR;
//             // iter
//             this.curL = this.curL.next.next;
//             prevR.next = null;
//             return true;
//         }
//         // reorder
//         ListNode curR = prevR.next;
//         curR.next = this.curL.next;
//         this.curL.next = curR;
//         // iter
//         this.curL = this.curL.next.next;
//         prevR.next = null;
//         return false;
//     }
}
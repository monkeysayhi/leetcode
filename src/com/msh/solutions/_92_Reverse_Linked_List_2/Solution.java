package com.msh.solutions._92_Reverse_Linked_List_2;

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

// 清爽、不易错的写法
public class Solution {
  // 反转 + 模块化 + dummy
  public ListNode reverseBetween(ListNode head, int m, int n) {
    // 1 ≤ m ≤ n ≤ length of list
    if (m == n) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode cur = head;
    int i = 1;
    for (; i < m && cur != null; i++) {
      prev = cur;
      cur = cur.next;
    }
    if (cur == null || cur.next == null) {
      return head;
    }

    reverse(prev, n - m + 1);

    return dummy.next;
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

// // 丑陋、易错的写法
// class Solution {
//     // 模拟
//     public ListNode reverseBetween(ListNode head, int m, int n) {
//         // 1 ≤ m ≤ n ≤ length of list
//         if (m == n) {
//             return head;
//         }

//         int i = 1;
//         ListNode prev = null;
//         ListNode cur = head;
//         ListNode subHeadPrev = new ListNode(0);
//         ListNode subTail = null;
//         while (cur != null) {
//             if (i < m) {
//                 subHeadPrev = cur;
//                 cur = cur.next;
//             } else if (i > n) {
//                 subTail.next = cur;
//                 break;
//             } else {
//                 ListNode next = cur.next;
//                 cur.next = prev;
//                 subHeadPrev.next = cur;
//                 if (subTail == null && prev != null) {
//                     subTail = prev;
//                     subTail.next = null;
//                 }
//                 prev = cur;
//                 cur = next;
//             }
//             i++;
//         }

//         if (m == 1) {
//             if (cur == null) {
//                 head = prev;
//             } else {
//                 head = subHeadPrev.next;
//             }
//         }
//         return head;
//     }
// }
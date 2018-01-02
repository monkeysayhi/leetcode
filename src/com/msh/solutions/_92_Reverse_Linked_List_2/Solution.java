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
  // 模拟，注意模块化思想
  public ListNode reverseBetween(ListNode head, int m, int n) {
    // 1 ≤ m ≤ n ≤ length of list
    if (m == n) {
      return head;
    }

    ListNode prev = null;
    ListNode cur = head;
    int i = 1;
    for (; i < m && cur != null; i++) {
      prev = cur;
      cur = cur.next;
    }
    if (cur == null || cur.next == null) {
      return head;
    }

    ListNode subHeadPrev = prev;
    ListNode subTail = cur;
    for (; i <= n && cur != null; i++) {
      ListNode next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }
    ListNode subHead = prev;
    ListNode subTailNext = cur;

    if (subHeadPrev != null) {
      subHeadPrev.next = subHead;
    } else {
      head = subHead;
    }
    subTail.next = subTailNext;
    return head;
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
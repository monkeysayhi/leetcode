package com.msh.solutions._23_Merge_k_Sorted_Lists;

import java.util.Comparator;
import java.util.PriorityQueue;

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
  // solution 2：最小堆维护k个链表的头节点
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    if (lists.length == 1) {
      return lists[0];
    }

    PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
        lists.length,
        new Comparator<ListNode>() {
          public int compare(ListNode l1, ListNode l2) {
            return l1.val - l2.val;
          }
        }
    );
    for (ListNode list : lists) {
      if (list != null) {
        minHeap.offer(list);
      }
    }
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    while (minHeap.size() >= 2) {
      ListNode minNode = minHeap.poll();
      tail.next = minNode;
      if (minNode.next != null) {
        minHeap.offer(minNode.next);
      }
      tail = tail.next;
    }
    tail.next = minHeap.poll();
    return dummy.next;
  }
//     // solution 1：归并每两个相邻列表
//     public ListNode mergeKLists(ListNode[] lists) {
//         if (lists == null || lists.length == 0) {
//             return null;
//         }
//         if (lists.length == 1) {
//             return lists[0];
//         }

//         Queue<ListNode> que = new LinkedList<>();
//         for (ListNode list : lists) {
//             if (list != null) {
//                 que.offer(list);
//             }
//         }
//         while (que.size() >= 2) {
//             ListNode l1 = que.poll();
//             ListNode l2 = que.poll();
//             ListNode l = merge2SortedList(l1, l2);
//             que.offer(l);
//         }
//         return que.poll();
//     }

//     private ListNode merge2SortedList(ListNode l1, ListNode l2) {
//         ListNode dummy = new ListNode(0);
//         ListNode tail = dummy;
//         while (l1 != null && l2 != null) {
//             if (l1.val < l2.val) {
//                 tail.next = l1;
//                 l1 = l1.next;
//             } else {
//                 tail.next = l2;
//                 l2 = l2.next;
//             }
//             tail = tail.next;
//         }
//         if (l1 != null) {
//             tail.next = l1;
//         }
//         if (l2 != null) {
//             tail.next = l2;
//         }
//         return dummy.next;
//     }
}

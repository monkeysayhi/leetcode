package com.msh.solutions._160_Intersection_of_Two_Linked_Lists;

/**
 * Created by monkeysayhi on 2018/9/23.
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
  // 先计算长度，假设长度差为 d，长的先走d步，然后两个同时走
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    int len1 = 0;
    for (ListNode p = headA; p != null; p = p.next) {
      len1++;
    }
    int len2 = 0;
    for (ListNode p = headB; p != null; p = p.next) {
      len2++;
    }

    ListNode p1 = headA;
    ListNode p2 = headB;
    if (len1 < len2) {
      for (int i = 0; i < len2 - len1; i++) {
        p2 = p2.next;
      }
    } else if (len1 > len2) {
      for (int i = 0; i < len1 - len2; i++) {
        p1 = p1.next;
      }
    }
    while (p1 != null && p2 != null && p1 != p2) {
      p1 = p1.next;
      p2 = p2.next;
    }
    if (p1 == p2) {
      return p1;
    }
    return null;
  }
}
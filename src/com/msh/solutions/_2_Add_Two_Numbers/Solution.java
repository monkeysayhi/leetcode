package com.msh.solutions._2_Add_Two_Numbers;

/**
 * Created by monkeysayhi on 2018/1/22.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);

    ListNode tail = dummy;
    int jinwei = 0;
    while (true) {
      if (l1 == null && l2 == null) {
        if (jinwei == 1) {
          tail.next = new ListNode(jinwei);
        }
        break;
      }
      int curSum = -1;
      if (l1 != null && l2 == null) {
        curSum = l1.val + jinwei;
        l1 = l1.next;
      } else if (l1 == null && l2 != null) {
        curSum = l2.val + jinwei;
        l2 = l2.next;
      } else {
        curSum = l1.val + l2.val + jinwei;
        l1 = l1.next;
        l2 = l2.next;
      }
      jinwei = curSum / 10;
      tail.next = new ListNode(curSum % 10);
      tail = tail.next;
    }

    return dummy.next;
  }
}

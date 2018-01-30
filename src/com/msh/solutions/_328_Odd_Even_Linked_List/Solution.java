package com.msh.solutions._328_Odd_Even_Linked_List;

/**
 * Created by monkeysayhi on 2018/1/30.
 */
class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  // dummy node简化
  public ListNode oddEvenList(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode oddDummy = new ListNode(0);
    ListNode evenDummy = new ListNode(0);
    ListNode oddTail = oddDummy;
    ListNode evenTail = evenDummy;
    ListNode oddCur = head;
    for (int i = 0; true; i++) {
      ListNode evenCur = oddCur.next;
      oddTail.next = oddCur;
      evenTail.next = evenCur;
      oddTail = oddTail.next;
      evenTail = evenTail.next;
      if (evenCur == null || evenCur.next == null) {
        break;
      }
      oddCur = evenCur.next;
    }
    oddTail.next = evenDummy.next;
    return oddDummy.next;
  }
}

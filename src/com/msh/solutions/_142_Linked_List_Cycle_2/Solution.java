package com.msh.solutions._142_Linked_List_Cycle_2;

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
  // solution1：set记录访问过的节点，存在重复访问的节点证明有环；重复节点即为环开始的节点
  // solution2：快慢指针，相遇即有环；后续证明见https://monkeysayhi.github.io/2017/08/19/【刷题】Linked-List-Cycle-II/
  public ListNode detectCycle(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode slow = head;
    // 与求中点不同，fast要从head开始
    ListNode fast = head;
    boolean hasCycle = false;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      // 移动一次后再判断。因为刚进入链表时也是相等的
      if (slow == fast) {
        hasCycle = true;
        break;
      }
    }
    if (!hasCycle) {
      return null;
    }

    ListNode p = head;
    while (p != slow) {
      p = p.next;
      slow = slow.next;
    }
    return p;
  }
}
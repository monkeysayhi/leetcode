package com.msh.solutions._109_Convert_Sorted_List_to_Binary_Search_Tree;

/**
 * Created by monkeysayhi on 2018/4/5.
 */

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}


class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class Solution {
  // 简单的分治：取中点作为根节点，分别构造两侧为左、右子树
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode midPrev = findMidPrev(dummy);
    ListNode mid = midPrev.next;
    TreeNode root = new TreeNode(mid.val);
    root.right = sortedListToBST(mid.next);
    midPrev.next = null;
    // 链表长度小于等于2时，mid等于head，此时左子树为null
    if (head != mid) {
      root.left = sortedListToBST(head);
    }
    return root;
  }

  private ListNode findMidPrev(ListNode prevHead) {
    assert prevHead != null;
    ListNode head = prevHead.next;
    ListNode slowPrev = prevHead;
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slowPrev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    return slowPrev;
  }
}
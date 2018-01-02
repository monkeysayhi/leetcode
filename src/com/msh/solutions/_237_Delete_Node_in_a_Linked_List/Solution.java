package com.msh.solutions._237_Delete_Node_in_a_Linked_List;

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

public class Solution {
  public void deleteNode(ListNode node) {
    if (node == null) {
      return;
    }
    assert node.next != null;
    node.val = node.next.val;
    node.next = node.next.next;
  }
}

package com.msh.solutions._114_Flatten_Binary_Tree_to_Linked_List;

/**
 * Created by monkeysayhi on 2017/12/15.
 */
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class Solution {

  private class TNList {
    private TreeNode head;
    private TreeNode tail;

    TNList(TreeNode head, TreeNode tail) {
      this.head = head;
      this.tail = tail;
    }
  }

  // 分治，TNList(head, tail)
  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    flattenInternal(root);
  }

  private TNList flattenInternal(TreeNode root) {
    if (root == null) {
      return null;
    }

    TNList leftList = flattenInternal(root.left);
    TNList rightList = flattenInternal(root.right);

    root.left = null;
    TreeNode head = root;
    TreeNode tail = root;
    if (leftList != null) {
      tail.right = leftList.head;
      tail = leftList.tail;
    }
    if (rightList != null) {
      tail.right = rightList.head;
      tail = rightList.tail;
    }

    return new TNList(head, tail);
  }
}

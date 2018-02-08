package com.msh.solutions._117_Populating_Next_Right_Pointers_in_Each_Node_2;

/**
 * Created by monkeysayhi on 2018/2/8.
 */


class TreeLinkNode {
  int val;
  TreeLinkNode left, right, next;

  TreeLinkNode(int x) {
    val = x;
  }
}

public class Solution {
  // 注意：不相邻的堂兄弟的也要相连
  // 根右左的dfs，保证左连右的时候，右面的叔伯节点已经串起来
  public void connect(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      return;
    }

    if (root.left != null && root.right != null) {
      root.left.next = root.right;
    }

    TreeLinkNode nextRoot = root.next;
    boolean existNextChild = false;
    while (nextRoot != null) {
      if (nextRoot.left != null || nextRoot.right != null) {
        existNextChild = true;
        break;
      }
      nextRoot = nextRoot.next;
    }
    if (existNextChild) {
      TreeLinkNode child = root.right;
      if (root.right == null) {
        child = root.left;
      }
      TreeLinkNode nextChild = nextRoot.left;
      if (nextRoot.left == null) {
        nextChild = nextRoot.right;
      }
      child.next = nextChild;
    }

    // 注意此处的顺序！！！
    connect(root.right);
    connect(root.left);
  }
}
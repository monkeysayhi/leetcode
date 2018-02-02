package com.msh.solutions._116_Populating_Next_Right_Pointers_in_Each_Node;

/**
 * Created by monkeysayhi on 2018/2/2.
 */

class TreeLinkNode {
  int val;
  TreeLinkNode left, right, next;

  TreeLinkNode(int x) {
    val = x;
  }
}

public class Solution {
  // 如果不限制常量空间，则常规层序BFS
  // 记录p、l、r，假设p一层已经指向好，则 l.next = r, r.next = p.next.l
  // 完全二叉树简化了边界的判断
  public void connect(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      return;
    }
    assert root.left != null && root.right != null;
    root.left.next = root.right;
    if (root.next != null) {
      root.right.next = root.next.left;
    }
    connect(root.left);
    connect(root.right);
  }
}
package com.msh.solutions._129_Sum_Root_to_Leaf_Numbers;

/**
 * Created by monkeysayhi on 2018/4/5.
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
  private int sum;

  // 回溯，递归终止在叶子节点上，buf当前数字，成员变量sum记录和
  public int sumNumbers(TreeNode root) {
    // 规定
    if (root == null) {
      return 0;
    }
    int buf = 0;
    this.sum = 0;
    backtrack(root, buf);
    return this.sum;
  }

  private void backtrack(TreeNode root, int buf) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      buf = buf * 10 + root.val;
      sum += buf;
      return;
    }
    buf = buf * 10 + root.val;
    backtrack(root.left, buf);
    backtrack(root.right, buf);
  }
}
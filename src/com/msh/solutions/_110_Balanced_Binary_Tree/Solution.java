package com.msh.solutions._110_Balanced_Binary_Tree;

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

  private class Result {
    private boolean isBalanced;
    private int maxD;

    Result(boolean isBalanced, int maxD) {
      this.isBalanced = isBalanced;
      this.maxD = maxD;
    }
  }

  // 分治
  // Result(isBalanced, depth)
  public boolean isBalanced(TreeNode root) {
    return isBalancedInternal(root).isBalanced;
  }

  private Result isBalancedInternal(TreeNode root) {
    if (root == null) {
      return new Result(true, 0);
    }

    Result leftRs = isBalancedInternal(root.left);
    Result rightRs = isBalancedInternal(root.right);
    if (leftRs.isBalanced && rightRs.isBalanced
        && Math.abs(leftRs.maxD - rightRs.maxD) <= 1) {
      return new Result(true, 1 + Math.max(leftRs.maxD, rightRs.maxD));
    }
    return new Result(false, -1);
  }
}

package com.msh.solutions._124_Binary_Tree_Maximum_Path_Sum;


/**
 * Created by monkeysayhi on 2018/1/28.
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
  static class Result {
    private int max;
    private int cur;
    private Result(int max, int cur) {
      this.max = max;
      this.cur = cur;
    }
  }

  // 分治，Result(max, cur)，注意负数和0
  public int maxPathSum(TreeNode root) {
    return maxPathSumInt(root).max;
  }

  private Result maxPathSumInt(TreeNode root) {
    if (root == null) {
      return new Result(Integer.MIN_VALUE, 0);
    }
    Result lRs = maxPathSumInt(root.left);
    Result rRs = maxPathSumInt(root.right);

    int cur = root.val;
    int max = root.val;
    cur = Math.max(cur, lRs.cur + root.val);
    cur = Math.max(cur, root.val + rRs.cur);
    max = Math.max(max, lRs.max);
    max = Math.max(max, rRs.max);
    max = Math.max(max, cur);
    max = Math.max(max, lRs.cur + root.val + rRs.cur);
    return new Result(max, cur);
  }
}
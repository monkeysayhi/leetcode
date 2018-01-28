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
  private static class Result {
    private int max;
    private int cur;

    Result(int max, int cur) {
      this.max = max;
      this.cur = cur;
    }
  }

  // 分治，Result(max, cur)，要考虑负数和0，因此，面临的决策会更多
  public int maxPathSum(TreeNode root) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    return dc(root).max;
  }

  private Result dc(TreeNode root) {
    if (root == null) {
      return null;
    }

    Result leftRs = dc(root.left);
    Result rightRs = dc(root.right);

    int max = root.val;
    int cur = root.val;
    if (leftRs != null && rightRs != null) {
      max = Math.max(max, leftRs.cur + root.val + rightRs.cur);
      max = Math.max(max, root.val + leftRs.cur);
      max = Math.max(max, root.val + rightRs.cur);
      max = Math.max(max, leftRs.max);
      max = Math.max(max, rightRs.max);
      cur = Math.max(cur, root.val + leftRs.cur);
      cur = Math.max(cur, root.val + rightRs.cur);
    } else if (leftRs != null) {
      max = Math.max(max, root.val + leftRs.cur);
      max = Math.max(max, leftRs.max);
      cur = Math.max(cur, root.val + leftRs.cur);
    } else if (rightRs != null) {
      max = Math.max(max, root.val + rightRs.cur);
      max = Math.max(max, rightRs.max);
      cur = Math.max(cur, root.val + rightRs.cur);
    }
    return new Result(max, cur);
  }
}
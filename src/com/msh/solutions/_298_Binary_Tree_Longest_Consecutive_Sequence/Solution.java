package com.msh.solutions._298_Binary_Tree_Longest_Consecutive_Sequence;


/**
 * Created by monkeysayhi on 2017/12/16.
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
    private int maxLen = 0;
    private int curLen = 0;

    private Result(int maxLen, int curLen) {
      this.maxLen = maxLen;
      this.curLen = curLen;
    }
  }

  // 分治，Result(maxLen, curLen)
  public int longestConsecutive(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return longestConsecutiveInternal(root).maxLen;
  }

  private Result longestConsecutiveInternal(TreeNode root) {
    if (root.left == null && root.right == null) {
      return new Result(1, 1);
    }

    int maxLenL = 1;
    int curLenL = 1;
    if (root.left != null) {
      Result leftRs = longestConsecutiveInternal(root.left);
      if (root.val + 1 == root.left.val) {
        curLenL = 1 + leftRs.curLen;
      }
      maxLenL = Math.max(curLenL, leftRs.maxLen);
    }

    int maxLenR = 1;
    int curLenR = 1;
    if (root.right != null) {
      Result rightRs = longestConsecutiveInternal(root.right);
      if (root.val + 1 == root.right.val) {
        curLenR = 1 + rightRs.curLen;
      }
      maxLenR = Math.max(curLenR, rightRs.maxLen);
    }

    int maxLen = Math.max(maxLenL, maxLenR);
    int curLen = Math.max(curLenL, curLenR);
    return new Result(maxLen, curLen);
  }
}

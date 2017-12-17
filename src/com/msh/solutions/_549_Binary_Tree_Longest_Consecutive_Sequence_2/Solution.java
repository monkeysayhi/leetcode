package com.msh.solutions._549_Binary_Tree_Longest_Consecutive_Sequence_2;

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
    private int curUpLen = 0;
    private int curDownLen = 0;

    public Result(int maxLen, int curUpLen, int curDownLen) {
      this.maxLen = maxLen;
      this.curUpLen = curUpLen;
      this.curDownLen = curDownLen;
    }
  }

  // 分治，Result(maxLen, curUpLen, curDownLen)
  // 1. 在分治子树的时候，并不知道“子树->根节点”是上升还是下降的。同时记录curUpLen与curDownLen可解决。
  // 2. 除了最大路径出现在单棵子树的情况，还可能由“左子树<->根节点<->右子树”的路径联合构成最长路径，因此，conquer时需特别处理该情况
  public int longestConsecutive2(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return longestConsecutive2Internal(root).maxLen;
  }

  private Result longestConsecutive2Internal(TreeNode root) {
    if (root == null) {
      return null;
    }
    if (root.left == null && root.right == null) {
      return new Result(1, 1, 1);
    }

    int maxLenL = 1;
    int curUpLenL = 1;
    int curDownLenL = 1;
    Result leftRs = longestConsecutive2Internal(root.left);
    if (leftRs != null) {
      if (root.val - 1 == root.left.val) {
        curUpLenL = 1 + leftRs.curUpLen;
      } else if (root.val + 1 == root.left.val) {
        curDownLenL = 1 + leftRs.curDownLen;
      }
      maxLenL = Math.max(Math.max(curUpLenL, curDownLenL),
          leftRs.maxLen);
    }

    int maxLenR = 1;
    int curUpLenR = 1;
    int curDownLenR = 1;
    Result rightRs = longestConsecutive2Internal(root.right);
    if (rightRs != null) {
      if (root.val - 1 == root.right.val) {
        curUpLenR = 1 + rightRs.curUpLen;
      } else if (root.val + 1 == root.right.val) {
        curDownLenR = 1 + rightRs.curDownLen;
      }
      maxLenR = Math.max(Math.max(curUpLenR, curDownLenR),
          rightRs.maxLen);
    }

    int maxCombineLen = 1;
    int curCombineLen = 1;
    if (leftRs != null && rightRs != null) {
      if (root.val + 1 == root.left.val
          && root.val - 1 == root.right.val) {
        curCombineLen = 1 + leftRs.curDownLen + rightRs.curUpLen;
      } else if (root.val - 1 == root.left.val
          && root.val + 1 == root.right.val) {
        curCombineLen = 1 + leftRs.curUpLen + rightRs.curDownLen;
      }
      maxCombineLen = curCombineLen;
    }

    int maxLen = Math.max(Math.max(maxLenL, maxLenR),
        maxCombineLen);
    int curUpLen = Math.max(curUpLenL, curUpLenR);
    int curDownLen = Math.max(curDownLenL, curDownLenR);

    return new Result(maxLen, curUpLen, curDownLen);
  }
}

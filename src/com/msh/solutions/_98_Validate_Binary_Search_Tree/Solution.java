package com.msh.solutions._98_Validate_Binary_Search_Tree;

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
  private boolean initidMax = false;
  private int max;

  // Solutuon 1: 分治，Result(isBST, minV, maxV)
  // Solutuon 2: 中序DFS，判断是否保持升序
  // 本题采用了Solution 2，面试时面试官提过，练练手。
  // 但以后面试时还是建议使用Solution 1。因为Solutuon 2为了需要记录遍历的上一个节点，很麻烦，初始化的时候很丑陋
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (root.left == null && root.right == null && root.val == Integer.MIN_VALUE) {
      return true;
    }

    // assert 如果是BST，则一定存在一个节点大于 Interger.MIN_VALUE
    return dfsInorder(root);
  }

  private boolean dfsInorder(TreeNode root) {
    if (root.left == null && root.right == null) {
      if (!initidMax) {
        max = root.val;
        initidMax = true;
        return true;
      }
      if (root.val <= max) {
        return false;
      }
      max = root.val;
      return true;
    }

    if (root.left != null && !dfsInorder(root.left)) {
      return false;
    }

    if (!initidMax) {
      max = root.val;
      initidMax = true;
    } else {
      if (root.val <= max) {
        return false;
      }
      max = root.val;
    }

    if (root.right != null && !dfsInorder(root.right)) {
      return false;
    }
    return true;
  }
}

package com.msh.solutions._101_Symmetric_Tree;

/**
 * Created by monkeysayhi on 2018/3/25.
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
  // solution 1: 循环，层序BFS，对每一层，假定curLevel对称，判断nextLevel是否对称
  // solution 2: 递归，先序DFS，对每一个子树，判断两棵子树是否关于中点对称
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isMirror(root.left, root.right);
  }

  private boolean isMirror(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) {
      return true;
    }
    if (root1 != null && root2 != null) {
      return root1.val == root2.val
          && isMirror(root1.left, root2.right)
          && isMirror(root1.right, root2.left);
    }
    return false;
  }
}
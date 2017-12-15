package com.msh.solutions._236_Lowest_Common_Ancestor_of_a_Binary_Tree;

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

// 假设两节点在树中
public class Solution {
  // 这题比较坑，直接递归遍历记录path，会爆栈
  // 看题解才明白。思路见代码注释
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    // p and q must exist in tree, so that lca of p and q must exist
    return findMostPossibleLCA(root, p, q);
  }

  private TreeNode findMostPossibleLCA(TreeNode root, TreeNode node1, TreeNode node2) {
    if (root == null) {
      return null;
    }
    if (root == node1 || root == node2) {
      return root;
    }

    TreeNode leftPLCA = findMostPossibleLCA(root.left, node1, node2);
    TreeNode rightPLCA = lowestCommonAncestor(root.right, node1, node2);
    if (leftPLCA == null && rightPLCA == null) {
      // must not exist LCA
      return null;
    }
    if (leftPLCA != null && rightPLCA != null) {
      // must be LCA
      return root;
    }
    // most possible be LCA
    return leftPLCA != null ? leftPLCA : rightPLCA;
  }
}

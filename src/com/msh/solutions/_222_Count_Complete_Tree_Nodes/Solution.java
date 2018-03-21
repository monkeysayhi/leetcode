package com.msh.solutions._222_Count_Complete_Tree_Nodes;

/**
 * Created by monkeysayhi on 2018/3/21.
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
  // solution 1: 暴力遍历，O(n)
  // solution 2: 看题解才想到。分治 + 剪枝（探测完全二叉树的左右深度，以判断子树是否是满二叉树）
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftD = getLeftDepth(root);
    int rightD = getRightDepth(root);
    if (leftD == rightD) {
      return (1 << (leftD + 1)) - 1;
    }
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  private int getLeftDepth(TreeNode root) {
    int d = 0;
    for (TreeNode p = root.left; p != null; p = p.left) {
      d++;
    }
    return d;
  }

  private int getRightDepth(TreeNode root) {
    int d = 0;
    for (TreeNode p = root.right; p != null; p = p.right) {
      d++;
    }
    return d;
  }
}
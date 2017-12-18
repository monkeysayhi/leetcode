package com.msh.solutions._104_Maximum_Depth_of_Binary_Tree;

/**
 * Created by monkeysayhi on 2017/12/18.
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
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftMaxD = maxDepth(root.left);
    int rightMaxD = maxDepth(root.right);
    return 1 + (leftMaxD > rightMaxD ? leftMaxD : rightMaxD);
  }
}

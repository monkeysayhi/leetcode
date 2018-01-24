package com.msh.solutions._105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;

/**
 * Created by monkeysayhi on 2018/1/24.
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
  // 思路同https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
  // /description/
  // 但代码更漂亮
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    return buildTreeInternal(preorder, 0, preorder.length, inorder, 0, inorder.length);
  }

  private TreeNode buildTreeInternal(int[] pre, int preL, int preR,
                                     int[] in, int inL, int inR) {
    assert preR - preL == inR - inL;
    if (pre == null || in == null || preL == preR) {
      return null;
    }
    if (preR - preL == 1) {
      return new TreeNode(pre[preL]);
    }

    int rootVal = pre[preL];
    int leftNodesCnt = 0;
    while (inL + leftNodesCnt < inR && in[inL + leftNodesCnt] != rootVal) {
      leftNodesCnt++;
    }

    TreeNode root = new TreeNode(rootVal);
    root.left = buildTreeInternal(pre, preL + 1, preL + leftNodesCnt + 1, in, inL, inL +
        leftNodesCnt);
    root.right = buildTreeInternal(pre, preL + leftNodesCnt + 1, preR, in, inL + leftNodesCnt +
        1, inR);
    return root;
  }
}
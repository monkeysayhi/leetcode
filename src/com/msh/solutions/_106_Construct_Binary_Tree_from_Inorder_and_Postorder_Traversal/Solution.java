package com.msh.solutions._106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal;

/**
 * Created by monkeysayhi on 2017/12/12.
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
  // 后序序列的最后一个节点是root，中序序列中root两侧的子序列为左、右子树
  // 1. 后序序列的最后一个节点是根节点，不妨设为 root
  // 2. 在中序序列中找到 root，root 左侧的子序列 left 是左子树的中序序列，右侧同理
  // 3. 设 left 中节点的数量为 n，则后序序列的前n节点构造左子树的后序序列；如果 n == 0，则左子树为 null
  // 4. 递归重复 1-3
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if (inorder == null || postorder == null) {
      return null;
    }
    assert inorder.length == postorder.length;
    if (inorder.length == 0) {
      return null;
    }

    return buildTreeInternal(inorder, 0, postorder, 0, inorder.length);
  }

  private TreeNode buildTreeInternal(int[] inod, int inL,
                                     int[] postod, int postL,
                                     int len) {
    if (len == 0) {
      return null;
    }

    int rootVal = postod[postL + len - 1];
    TreeNode root = new TreeNode(rootVal);
    int leftLen = 0;
    while (leftLen < len && inod[inL + leftLen] != rootVal) {
      leftLen++;
    }
    root.left = buildTreeInternal(inod, inL, postod, postL, leftLen);
    root.right = buildTreeInternal(inod, inL + leftLen + 1, postod, postL + leftLen, len -
        leftLen - 1);
    return root;
  }
}

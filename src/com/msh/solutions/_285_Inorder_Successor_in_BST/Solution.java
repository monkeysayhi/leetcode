package com.msh.solutions._285_Inorder_Successor_in_BST;

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
  // 如何验证BST？分治验证子树，或者中序遍历看是否是上升序列
  // 因此，中序遍历BST能得到升序序列
  // 求BST中目标v的**后继节点**，等价于在BST中二分查找求上界
  // 同时，由于给定节点p必在树中，因此，不需要考虑p的值小于最小节点的情况
  // PS：BST中没有重复元素，所以不用担心找到相同值不同节点p'的上界
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    return bsearchU(root, p);
  }

  private TreeNode bsearchU(TreeNode root, TreeNode v) {
    TreeNode m = root;
    TreeNode succ = null;
    while (m != null) {
      if (m.val > v.val) {
        succ = m;
        m = m.left;
      } else {
        m = m.right;
      }
    }
    return succ;
  }
}

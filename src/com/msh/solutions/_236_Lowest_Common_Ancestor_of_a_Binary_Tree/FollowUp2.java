package com.msh.solutions._236_Lowest_Common_Ancestor_of_a_Binary_Tree;

import java.util.Stack;

/**
 * Created by monkeysayhi on 2017/12/15.
 */

// 两节点可能不在树中，节点也没有 parent 域
public class FollowUp2 {
  // 1. DFS得到从root到两节点的路径
  // 2. 再比较路径取lca
  // 时间O(n)，空间O(lgn)
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode node1 = p;
    TreeNode node2 = q;
    if (root == null || node1 == null || node2 == null) {
      return null;
    }

    Stack<TreeNode> path1 = new Stack<>();
    if (!dfsPreorder(root, node1, path1)) {
      return null;
    }
    Stack<TreeNode> path2 = new Stack<>();
    if (!dfsPreorder(root, node2, path2)) {
      return null;
    }

    TreeNode lca = null;
    for (int i = 0; i < path1.size() && i < path2.size(); i++) {
      if (path1.get(i) != path2.get(i)) {
        break;
      }
      lca = path1.get(i);
    }

    return lca;
  }

  private boolean dfsPreorder(TreeNode root, TreeNode node, Stack<TreeNode> path) {
    path.push(root);
    if (root == node) {
      return true;
    }
    if (root.left != null && dfsPreorder(root.left, node, path)) {
      return true;
    }
    if (root.right != null && dfsPreorder(root.right, node, path)) {
      return true;
    }
    path.pop();
    return false;
  }
}

package com.msh.solutions._144_Binary_Tree_Preorder_Traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfsPreorder(root, result);
    return result;
  }

  private void dfsPreorder(TreeNode root, List<Integer> result) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while (cur != null || !stack.isEmpty()) {
      while (cur != null) {
        result.add(cur.val);
        stack.push(cur);
        cur = cur.left;
      }
      cur = stack.pop();
      cur = cur.right;
    }
  }
}

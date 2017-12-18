package com.msh.solutions._145_Binary_Tree_Postorder_Traversal;

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
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfsPostorder(root, result);
    return result;
  }

  private void dfsPostorder(TreeNode root, List<Integer> result) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    TreeNode last = null;
    while (cur != null || !stack.isEmpty()) {
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }
      cur = stack.peek();
      if (cur.right == null || cur.right == last) {
        result.add(cur.val);
        stack.pop();
        last = cur;
        cur = null;
      } else {
        cur = cur.right;
      }
    }
  }
}

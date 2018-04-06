package com.msh.solutions._173_Binary_Search_Tree_Iterator;

import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/4/6.
 */

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}


// 拆分循环版中序dfs
public class BSTIterator {
  // OPT: lazy init
  private Stack<TreeNode> stack = new Stack<>();

  public BSTIterator(TreeNode root) {
    increStack(root);
  }

  /**
   * @return whether we have a next smallest number
   */
  public boolean hasNext() {
    return stack.size() > 0;
  }

  /**
   * @return the next smallest number
   */
  public int next() {
    // TODO: throw exception if no elem left
    TreeNode node = stack.pop();
    increStack(node.right);
    return node.val;
  }

  private void increStack(TreeNode root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
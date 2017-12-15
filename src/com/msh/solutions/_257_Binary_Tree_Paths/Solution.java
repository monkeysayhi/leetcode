package com.msh.solutions._257_Binary_Tree_Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

public class Solution {
  private static final String SEP = "->";

  // 先序dfs，stack做buf保存遍历的中间结果
  public List<String> binaryTreePaths(TreeNode root) {
    if (root == null) {
      return new ArrayList<String>();
    }

    List<String> results = new ArrayList<>();
    Stack<String> buf = new Stack<>();
    buf.push(String.valueOf(root.val));
    dfsPreorder(root, buf, results);
    buf.pop();
    return results;
  }

  private void dfsPreorder(TreeNode root, Stack<String> buf, List<String> results) {
    if (root.left == null && root.right == null) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < buf.size(); i++) {
        sb.append(buf.get(i));
        if (i < buf.size() - 1) {
          sb.append(SEP);
        }
      }
      results.add(sb.toString());
      return;
    }

    if (root.left != null) {
      buf.push(String.valueOf(root.left.val));
      dfsPreorder(root.left, buf, results);
      buf.pop();
    }
    if (root.right != null) {
      buf.push(String.valueOf(root.right.val));
      dfsPreorder(root.right, buf, results);
      buf.pop();
    }
  }
}
package com.msh.solutions._107_Binary_Tree_Level_Order_Traversal_2;

import java.util.*;

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
  // 层序BFS + stack
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    if (root == null) {
      return results;
    }

    Stack<List<Integer>> resultStack = new Stack<>();
    bfsLevelorder(root, resultStack);
    while (!resultStack.isEmpty()) {
      results.add(resultStack.pop());
    }
    return results;
  }

  private void bfsLevelorder(TreeNode root, Stack<List<Integer>> resultStack) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int curLevelSize = queue.size();
      List<Integer> curLevelResult = new ArrayList<>();
      for (int i = 0; i < curLevelSize; i++) {
        TreeNode node = queue.poll();
        curLevelResult.add(node.val);
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      resultStack.push(curLevelResult);
    }
  }
}

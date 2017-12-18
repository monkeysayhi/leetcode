package com.msh.solutions._102_Binary_Tree_Level_Order_Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
  // 层序BFS
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    if (root == null) {
      return results;
    }
    bfsLevelorder(root, results);
    return results;
  }

  private void bfsLevelorder(TreeNode root,
                             List<List<Integer>> results) {
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
      results.add(curLevelResult);
    }
  }
}
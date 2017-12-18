package com.msh.solutions._103_Binary_Tree_Zigzag_Level_Order_Traversal;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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

class Solution {
  // 层序BFS + deque，一层从左向右，一层从右向左
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    if (root == null) {
      return results;
    }

    bfsZigzagLevelorder(root, results);
    return results;
  }

  private void bfsZigzagLevelorder(TreeNode root,
                                   List<List<Integer>> results) {
    Deque<TreeNode> deq = new LinkedList<>();
    deq.offer(root);
    boolean left2right = true;
    while (!deq.isEmpty()) {
      int curLevelSize = deq.size();
      List<Integer> curLevelResult = new ArrayList<>();
      for (int i = 0; i < curLevelSize; i++) {
        if (left2right) {
          TreeNode node = deq.pollFirst();
          curLevelResult.add(node.val);
          if (node.left != null) {
            deq.offerLast(node.left);
          }
          if (node.right != null) {
            deq.offerLast(node.right);
          }
        } else {
          TreeNode node = deq.pollLast();
          curLevelResult.add(node.val);
          if (node.right != null) {
            deq.offerFirst(node.right);
          }
          if (node.left != null) {
            deq.offerFirst(node.left);
          }
        }
      }
      left2right = !left2right;
      results.add(curLevelResult);
    }
  }
}
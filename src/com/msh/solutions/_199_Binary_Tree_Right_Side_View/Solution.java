package com.msh.solutions._199_Binary_Tree_Right_Side_View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

public class Solution {
  // slution 1: 根、右、左的dfs，用maxD维护层，每层都只记录第一个遍历到的节点
  // 相当于：回溯，rs保存右侧的视图，终止在叶子节点，回溯时，每当深度大于rs.sise时就向rs添加一个元素
  // solution 2: 右、左的层序bfs，每层都只记录第一个节点

  // solution 2
  public List<Integer> rightSideView(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<Integer> rs = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (queue.size() > 0) {
      int curLevelSize = queue.size();
      for (int i = 0; i < curLevelSize; i++) {
        TreeNode node = queue.poll();
        if (i == 0) {
          rs.add(node.val);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
        if (node.left != null) {
          queue.offer(node.left);
        }
      }
    }
    return rs;
  }

//     // solution 1
//     public List<Integer> rightSideView(TreeNode root) {
//         if (root == null) {
//             return new LinkedList<>();
//         }
//         List<Integer> rs = new LinkedList<>();
//         backtrack(root, 0, rs);
//         return rs;
//     }

//     private void backtrack(TreeNode root, int depth, List<Integer> rs) {
//         if (root == null) {
//             return;
//         }
//         depth++;
//         if (depth > rs.size()) {
//             rs.add(root.val);
//         }
//         // 右侧视图，所以要按照“根右左”的顺序
//         backtrack(root.right, depth, rs);
//         backtrack(root.left, depth, rs);
//     }
}
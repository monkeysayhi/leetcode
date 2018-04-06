package com.msh.solutions._199_Binary_Tree_Right_Side_View;

import java.util.LinkedList;
import java.util.List;

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
  // 回溯，rs保存右侧的视图，终止在叶子节点，回溯时，每当深度大于rs.sise时就向rs添加一个元素
  public List<Integer> rightSideView(TreeNode root) {
    if (root == null) {
      return new LinkedList<>();
    }
    List<Integer> rs = new LinkedList<>();
    backtrack(root, 0, rs);
    return rs;
  }

  private void backtrack(TreeNode root, int depth, List<Integer> rs) {
    if (root == null) {
      return;
    }
    depth++;
    if (depth > rs.size()) {
      rs.add(root.val);
    }
    // 右侧视图，所以要按照“根右左”的顺序
    backtrack(root.right, depth, rs);
    backtrack(root.left, depth, rs);
  }
}
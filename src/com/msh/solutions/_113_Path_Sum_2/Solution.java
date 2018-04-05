package com.msh.solutions._113_Path_Sum_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/4/5.
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
  // 回溯，递归终止在叶子节点上，buf当前路径
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    if (root == null) {
      return new LinkedList<>();
    }
    Stack<Integer> buf = new Stack<>();
    List<List<Integer>> rs = new LinkedList<>();
    backtrack(root, sum, buf, rs);
    return rs;
  }

  private void backtrack(TreeNode root, int sum,
                         Stack<Integer> buf, List<List<Integer>> rs) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      if (root.val == sum) {
        buf.push(root.val);
        rs.add(new ArrayList<>(buf));
        buf.pop();
      }
      return;
    }
    buf.push(root.val);
    backtrack(root.left, sum - root.val, buf, rs);
    backtrack(root.right, sum - root.val, buf, rs);
    buf.pop();
  }
}
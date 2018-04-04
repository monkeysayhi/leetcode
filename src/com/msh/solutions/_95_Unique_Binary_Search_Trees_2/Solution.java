package com.msh.solutions._95_Unique_Binary_Search_Trees_2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/4/4.
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
  // 动规枚举出所有树结构，然后在每棵树上中序dfs填数字
  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      // 规定。返回null更好。
      return new LinkedList<>();
    }

    List<TreeNode> BSTs = getAllBSTs(n);
    for (TreeNode BST : BSTs) {
      assert fillBST(BST, 0) == n;
    }
    return BSTs;
  }

  // 设 dp[i] 为“i个节点组成的所有BST”，则 dp[i] = sum{dp[j] X dp[i - j - 1] with root | 0 <= j < i}
  private List<TreeNode> getAllBSTs(int n) {
    List<TreeNode>[] dp = new List[n + 1];
    dp[0] = new LinkedList<>();
    dp[0].add(null);
    dp[1] = new LinkedList<>();
    dp[1].add(new TreeNode(0));
    for (int i = 2; i <= n; i++) {
      dp[i] = new LinkedList<>();
      for (int j = 0; j < i; j++) {
        dp[i].addAll(multiplyWithRoot(dp[j], dp[i - j - 1]));
      }
    }
    return dp[n];
  }

  private List<TreeNode> multiplyWithRoot(List<TreeNode> lTrees, List<TreeNode> rTrees) {
    List<TreeNode> rs = new LinkedList<>();
    for (TreeNode lTree : lTrees) {
      for (TreeNode rTree : rTrees) {
        TreeNode root = new TreeNode(0);
        root.left = copy(lTree);
        root.right = copy(rTree);
        rs.add(root);
      }
    }
    return rs;
  }

  private TreeNode copy(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode copyRoot = new TreeNode(root.val);
    copyRoot.left = copy(root.left);
    copyRoot.right = copy(root.right);
    return copyRoot;
  }

  // 中序填数字
  private int fillBST(TreeNode root, int last) {
    assert root != null;
    if (root.left != null) {
      last = fillBST(root.left, last);
    }
    last++;
    root.val = last;
    if (root.right != null) {
      last = fillBST(root.right, last);
    }
    return last;
  }
}
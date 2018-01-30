package com.msh.solutions._230_Kth_Smallest_Element_in_a_BST;

/**
 * Created by monkeysayhi on 2018/1/30.
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
  private int cur;
  private TreeNode kthNode;

  // basic problem: 中序遍历，O(n)
  // follow up:
  // 算法上可以仿照ConcurrentHashMap#size在jdk1.7中的实现，分段加和，即记录左右子树的节点数，则以后增删查都可以二分O(logn)完成（画图，举例）
  // 除此之外，工程上还可以考虑是否读写的分布，看是否适合维护一段缓存，缓存中存储已知的前k个元素（具体画图解释）
  public int kthSmallest(TreeNode root, int k) {
    cur = 0;
    assert dfsInorder(root, k);
    return kthNode.val;
  }

  private boolean dfsInorder(TreeNode root, int k) {
    if (root == null) {
      return false;
    }

    if (dfsInorder(root.left, k)) {
      return true;
    }
    cur++;
    if (cur == k) {
      kthNode = root;
      return true;
    }
    if (dfsInorder(root.right, k)) {
      return true;
    }
    return false;
  }
}
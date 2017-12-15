package com.msh.solutions._236_Lowest_Common_Ancestor_of_a_Binary_Tree;

/**
 * Created by monkeysayhi on 2017/12/15.
 */
class ParentTreeNode {
  public ParentTreeNode parent, left, right;
}

// 假设两节点在树中，假设节点有 parent 域
public class FollowUp1 {
  // 1. 先分别向上遍历到root，得到两个深度d1，d2
  // 2. 回到节点位置，更深的先向上走abs(d1-d2)步
  // 3. 然后二者一起走min(d1,d2)步，过程中一定会有根节点
  // 时间O(lgn)，空间O(1)
  public ParentTreeNode lowestCommonAncestor(ParentTreeNode root,
                                             ParentTreeNode p,
                                             ParentTreeNode q) {
    ParentTreeNode node1 = p;
    ParentTreeNode node2 = q;
    if (root == null || node1 == null || node2 == null) {
      return null;
    }

    int depth1 = getDepth(root, node1);
    int depth2 = getDepth(root, node2);
    if (depth1 == -1 || depth2 == -1) {
      return null;
    }

    ParentTreeNode startNode1 = node1;
    ParentTreeNode startNode2 = node2;
    int depth = depth1;
    if (depth1 > depth2) {
      for (int i = 0; i < depth1 - depth2; i++) {
        startNode1 = startNode1.parent;
      }
      depth = depth2;
    } else if (depth1 < depth2) {
      for (int i = 0; i < depth2 - depth1; i++) {
        startNode2 = startNode2.parent;
      }
      depth = depth1;
    }

    for (int i = 0; i < depth; i++) {
      if (startNode1 == startNode2) {
        return startNode1;
      }
      startNode1 = startNode1.parent;
      startNode2 = startNode2.parent;
    }

    throw new RuntimeException("UnknownError");
  }

  private int getDepth(ParentTreeNode root, ParentTreeNode target) {
    int depth = 1;
    ParentTreeNode node = target;
    for (; node.parent != null; node = node.parent) {
      if (node == root) {
        break;
      }
      depth++;
    }

    if (node == root) {
      return depth;
    }
    return -1;
  }
}

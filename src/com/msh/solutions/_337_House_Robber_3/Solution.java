package com.msh.solutions._337_House_Robber_3;

/**
 * Created by monkeysayhi on 2018/1/26.
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

  private static class Result {
    private int maxRob;
    private int maxNRob;

    private Result(int maxRob, int maxNRob) {
      this.maxRob = maxRob;
      this.maxNRob = maxNRob;
    }
  }

  // solution 2: 注意到，动规的时候，当前节点的状态仅与孩子节点相关，则可以进一步简化为分治
  // Result(maxRob, maxNRob)，Result.max = max{maxRob, maxNRob}
  // 设dpRob[root]为抢劫节点root后的最大钱数，dpNRob[root]为不抢劫节点root后的最大钱数
  // dpRob[root] = 两个孩子都不抢
  // dpNRob[root] = max{dpRob[root.left], dpNRob[root.left]} + max{dpRob[root.right], dpNRob[root.right]}
  public int rob(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Result rs = dc(root);
    return Math.max(rs.maxRob, rs.maxNRob);
  }

  private Result dc(TreeNode root) {
    if (root == null) {
      return new Result(0, 0);
    }

    Result leftRs = dc(root.left);
    Result rightRs = dc(root.right);

    Result rs = new Result(0, 0);
    rs.maxRob = root.val + leftRs.maxNRob + rightRs.maxNRob;
    rs.maxNRob = Math.max(leftRs.maxRob, leftRs.maxNRob)
        + Math.max(rightRs.maxRob, rightRs.maxNRob);
    return rs;
  }

//     // solution 1: 最优化，求最大，多状态动规
//     // 设dpRob[root]为抢劫节点root后的最大钱数，dpNRob[root]为不抢劫节点root后的最大钱数
//     // dpRob[root] = 两个孩子都不抢
//     // dpNRob[root] = max{dpRob[root.left], dpNRob[root.left]} + max{dpRob[root.right], dpNRob[root.right]}
//     // 记忆化搜索更好写
//     public int rob(TreeNode root) {
//         if (root == null) {
//             return 0;
//         }
//         Map<TreeNode, Integer> dpRob = new HashMap<>();
//         Map<TreeNode, Integer> dpNRob = new HashMap<>();
//         robInTree(dpRob, dpNRob, root);
//         return Math.max(dpRob.get(root), dpNRob.get(root));
//     }

//     private void robInTree(Map<TreeNode, Integer> dpRob,
//                            Map<TreeNode, Integer> dpNRob,
//                            TreeNode root) {
//         if (root == null) {
//             return;
//         }

//         if (dpRob.containsKey(root)) {
//             assert dpNRob.containsKey(root);
//             return;
//         }

//         robInTree(dpRob, dpNRob, root.left);
//         robInTree(dpRob, dpNRob, root.right);

//         int maxRob = root.val;
//         maxRob += root.left == null ? 0 : dpNRob.get(root.left);
//         maxRob += root.right == null ? 0 : dpNRob.get(root.right);
//         dpRob.put(root, maxRob);

//         int maxNRob = 0;
//         maxNRob += root.left == null ? 0 : Math.max(dpRob.get(root.left), dpNRob.get(root.left));
//         maxNRob += root.right == null ? 0 : Math.max(dpRob.get(root.right), dpNRob.get(root.right));
//         dpNRob.put(root, maxNRob);
//     }
}
}
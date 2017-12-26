package com.msh.solutions._133_Clone_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by monkeysayhi on 2017/12/26.
 */
class UndirectedGraphNode {
  int label;
  List<UndirectedGraphNode> neighbors;

  UndirectedGraphNode(int x) {
    label = x;
    neighbors = new ArrayList<UndirectedGraphNode>();
  }
};

public class Solution {
  // 智力题：
  // solution 1, readable, recommended：先拷贝节点，再拷贝边
  // solution 2，clean but not readable：递归拷贝边，map缓存节点

  // solution 1
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) {
      return null;
    }

    Map<UndirectedGraphNode, UndirectedGraphNode> newNodes = new HashMap<>();
    cloneNodes(node, newNodes);
    cloneEdges(newNodes);
    return newNodes.get(node);
  }

  // 先序dfs 或 后序dfs+visiting状态（单纯后序dfs会死循环）
  private void cloneNodes(UndirectedGraphNode oldNode,
                          Map<UndirectedGraphNode, UndirectedGraphNode> newNodes) {
    if (newNodes.containsKey(oldNode)) {
      return;
    }
    newNodes.put(oldNode, new UndirectedGraphNode(oldNode.label));
    for (UndirectedGraphNode node : oldNode.neighbors) {
      cloneNodes(node, newNodes);
    }
  }

  private void cloneEdges(Map<UndirectedGraphNode, UndirectedGraphNode> newNodes) {
    for (UndirectedGraphNode oldNode : newNodes.keySet()) {
      UndirectedGraphNode newNode = newNodes.get(oldNode);
      for (UndirectedGraphNode oldNeighbor : oldNode.neighbors) {
        newNode.neighbors.add(newNodes.get(oldNeighbor));
      }
    }
  }

//     // solution 2
//     public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
//         if (node == null) {
//             return null;
//         }

//         Map<UndirectedGraphNode, UndirectedGraphNode> newNodes = new HashMap<>();
//         cloneGraph(node, newNodes);
//         return newNodes.get(node);
//     }

//     private UndirectedGraphNode cloneGraph(UndirectedGraphNode oldNode,
//                                            Map<UndirectedGraphNode, UndirectedGraphNode>
// newNodes) {
//         if (newNodes.containsKey(oldNode)) {
//             return newNodes.get(oldNode);
//         }
//         UndirectedGraphNode newNode = new UndirectedGraphNode(oldNode.label);
//         // 触发函数入口处的递归终止条件
//         newNodes.put(oldNode, newNode);
//         for (UndirectedGraphNode oldNeighbor : oldNode.neighbors) {
//             UndirectedGraphNode newNeighbor = cloneGraph(oldNeighbor, newNodes);
//             newNode.neighbors.add(newNeighbor);
//         }
//         return newNode;
//     }
}

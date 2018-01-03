package com.msh.solutions._138_Copy_List_with_Random_Pointer;

/**
 * Created by monkeysayhi on 2018/1/3.
 */

class RandomListNode {
  int label;
  RandomListNode next, random;

  RandomListNode(int x) {
    this.label = x;
  }
}

public class Solution {
  // solution2: 奇技淫巧，使用next保存新节点；时间O(n)，空间O(1)
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
      return null;
    }
    copyNodes(head);
    copyRandom(head);
    return separateNewNodes(head);
  }

  private void copyNodes(RandomListNode head) {
    while (head != null) {
      RandomListNode next = head.next;
      RandomListNode headCopy = new RandomListNode(head.label);
      head.next = headCopy;
      headCopy.next = next;
      head = next;
    }
  }

  private void copyRandom(RandomListNode head) {
    while (head != null) {
      RandomListNode headCopy = head.next;
      if (head.random != null) {
        headCopy.random = head.random.next;
      }
      head = headCopy.next;
    }
  }

  private RandomListNode separateNewNodes(RandomListNode head) {
    RandomListNode newHead = head.next;
    while (head != null) {
      RandomListNode headCopy = head.next;
      head.next = head.next.next;
      if (headCopy.next != null) {
        headCopy.next = headCopy.next.next;
      }
      head = head.next;
    }
    return newHead;
  }

//     // solution1: map存映射，暴力复制；时间O(n)，空间O(n)
//     public RandomListNode copyRandomList(RandomListNode head) {
//         if (head == null) {
//             return null;
//         }
//         Map<RandomListNode, RandomListNode> newNodes = copyNodes(head);
//         copyEdges(head, newNodes);
//         return newNodes.get(head);
//     }

//     private Map<RandomListNode, RandomListNode> copyNodes(RandomListNode head) {
//         Map<RandomListNode, RandomListNode> newNodes = new HashMap<>();
//         while (head != null) {
//             newNodes.put(head, new RandomListNode(head.label));
//             head = head.next;
//         }
//         return newNodes;
//     }

//     private void copyEdges(RandomListNode head, Map<RandomListNode, RandomListNode> newNodes) {
//         while (head != null) {
//             RandomListNode newNode = newNodes.get(head);
//             newNode.next = newNodes.get(head.next);
//             if (head.random != null) {
//                 newNode.random = newNodes.get(head.random);
//             }
//             head = head.next;
//         }
//     }
}
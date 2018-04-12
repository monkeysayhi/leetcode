package com.msh.solutions._382_Linked_List_Random_Node;

import java.util.Random;

/**
 * Created by monkeysayhi on 2018/4/12.
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// // basic solution 1: 计算长度，随机数，然后取
// public class Solution {
//     private int size;
//     private ListNode head;
//     private Random random = new Random();

//     /** @param head The linked list's head.
//         Note that the head is guaranteed to be not null, so it contains at least one node. */
//     public Solution(ListNode head) {
//         this.head = head;
//         for (ListNode p = head; p != null; p = p.next) {
//             size++;
//         }
//     }

//     /** Returns a random node's value. */
//     public int getRandom() {
//         int randomPos = random.nextInt(size);
//         return get(randomPos).val;
//     }

//     private ListNode get(int pos) {
//         assert pos < size;
//         ListNode node = null;
//         for (ListNode p = head; p != null; p = p.next) {
//             if (pos == 0) {
//                 node = p;
//                 break;
//             }
//             pos--;
//         }
//         return node;
//     }
// }

// // basic solution 2: 计算长度，保存数组，随机数，然后取
// public class Solution {
//     private int[] nodes;
//     private Random random = new Random();

//     /** @param head The linked list's head.
//         Note that the head is guaranteed to be not null, so it contains at least one node. */
//     public Solution(ListNode head) {
//         int size = 0;
//         for (ListNode p = head; p != null; p = p.next) {
//             size++;
//         }
//         nodes = new int[size];
//         for (int i = 0; i < size; i++) {
//             nodes[i] = head.val;
//             head = head.next;
//         }
//     }

//     /** Returns a random node's value. */
//     public int getRandom() {
//         assert nodes != null && nodes.length > 0;
//         int randomPos = random.nextInt(nodes.length);
//         return nodes[randomPos];
//     }
// }

// follow up: 看 [题解](http://bookshadow.com/weblog/2016/08/10/leetcode-linked-list-random-node/)，蓄水池抽样
public class Solution {
  private ListNode head;
  private Random random = new Random();

  /** @param head The linked list's head.
  Note that the head is guaranteed to be not null, so it contains at least one node. */
  public Solution(ListNode head) {
    this.head = head;
  }

  /** Returns a random node's value. */
  public int getRandom() {
    assert head != null;
    int cnt = 1;
    ListNode chosen = null;
    for (ListNode p = head; p != null; p = p.next) {
      if (random.nextInt(cnt) == 0) {
        chosen = p;
      }
      cnt++;
    }
    assert chosen != null;
    return chosen.val;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
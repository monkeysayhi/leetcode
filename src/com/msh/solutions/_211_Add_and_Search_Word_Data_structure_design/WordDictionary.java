package com.msh.solutions._211_Add_and_Search_Word_Data_structure_design;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by monkeysayhi on 2018/2/5.
 */
// 比trie树多一点点搜索
public class WordDictionary {
  private Node root;

  private static class Node {
    private char ch;
    private boolean isWord;
    private Map<Character, Node> children;

    private Node(char ch) {
      this.ch = ch;
      this.isWord = false;
      // TODO opt
      this.children = new HashMap<>();
    }

    private static Node createRootNode() {
      return new Node('_');
    }

    private int childrenCnt() {
      return children.size();
    }

    private Collection<Node> getChildren() {
      return children.values();
    }

    private boolean hasChild(char ch) {
      return children.containsKey(ch);
    }

    private Node getChild(char ch) {
      return children.get(ch);
    }

    private void addChild(char ch) {
      if (!hasChild(ch)) {
        children.put(ch, new Node(ch));
      }
    }

    private void forceIsWord() {
      isWord = true;
    }

    private boolean isWord() {
      return isWord;
    }
  }

  /**
   * Initialize your data structure here.
   */
  public WordDictionary() {
    // TODO opt
    root = Node.createRootNode();
  }

  /**
   * Adds a word into the data structure.
   */
  public void addWord(String word) {
    // assume valid
    Node cur = root;
    char[] chars = word.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (!cur.hasChild(chars[i])) {
        cur.addChild(chars[i]);
      }
      cur = cur.getChild(chars[i]);
      if (i == chars.length - 1) {
        cur.forceIsWord();
      }
    }
  }

  /**
   * Returns if the word is in the data structure. A word could contain the dot character '.' to
   * represent any one letter.
   */
  public boolean search(String word) {
    // assume valid
    return searchInt(word.toCharArray(), 0, root);
  }

  private boolean searchInt(char[] chars, int offset, Node parent) {
    char ch = chars[offset];
    if (ch != '.') {
      if (!parent.hasChild(ch)) {
        return false;
      }
      Node child = parent.getChild(ch);
      if (offset == chars.length - 1) {
        return child.isWord();
      }
      return searchInt(chars, offset + 1, child);
    }
    if (parent.childrenCnt() == 0) {
      return false;
    }
    if (offset == chars.length - 1) {
      for (Node child : parent.getChildren()) {
        if (child.isWord()) {
          return true;
        }
      }
      return false;
    }
    for (Node child : parent.getChildren()) {
      if (offset == chars.length - 1 && child.isWord()) {
        return true;
      }
      if (searchInt(chars, offset + 1, child)) {
        return true;
      }
    }
    return false;
  }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
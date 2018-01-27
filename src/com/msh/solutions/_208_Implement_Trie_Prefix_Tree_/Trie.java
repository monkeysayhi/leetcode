package com.msh.solutions._208_Implement_Trie_Prefix_Tree_;

/**
 * Created by monkeysayhi on 2018/1/28.
 */
// v4：Map换数据没多少提升。而且test case不是按照注释给的。优化Trie#insert()中的TODO
public class Trie {
  private Node root;

  /** Initialize your data structure here. */
  public Trie() {
    // TODO op, lazy init
    this.root = new Node(Node.NO_SENSE_CHAR, false);
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    Node parent = this.root;
    char[] chars = word.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      char ch = chars[i];
      if (!parent.hasChild(ch)) {
        parent.addChild(ch);
      }
      if (i == chars.length - 1) {
        parent.getChild(ch).isWord = true;
      }
      parent = parent.getChild(ch);
    }
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    return searchInternal(word, false);
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    return searchInternal(prefix, true);
  }

  private boolean searchInternal(String prefix, boolean ignoreIsWord) {
    Node parent = this.root;
    char[] chars = prefix.toCharArray();
    for (int i = 0; i < chars.length; i++)  {
      if (!parent.hasChild(chars[i])) {
        return false;
      }
      Node child = parent.getChild(chars[i]);
      if (i == chars.length - 1) {
        return ignoreIsWord || child.isWord;
      }
      parent = child;
    }
    // no sense
    return false;
  }

  private static class Node {
    private char ch;
    private boolean isWord;
    private Node[] children;

    private static final char NO_SENSE_CHAR = ' ';

    private Node(char ch, boolean isWord) {
      this.ch = ch;
      this.isWord = isWord;
    }

    private Node(char ch) {
      this(ch, false);
    }

    private boolean hasChild(char ch) {
      return getChild(ch) != null;
    }

    private Node getChild(char ch) {
      if (this.children == null) {
        return null;
      }
      return this.children[ch - 'a'];
    }

    private void addChild(char ch) {
      if (this.children == null) {
        this.children = new Node[26];
      }
      assert this.children[ch - 'a'] == null;
      this.children[ch - 'a'] = new Node(ch);
    }
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
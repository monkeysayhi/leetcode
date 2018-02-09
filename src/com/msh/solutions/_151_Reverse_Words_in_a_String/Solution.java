package com.msh.solutions._151_Reverse_Words_in_a_String;

/**
 * Created by monkeysayhi on 2018/2/9.
 */
public class Solution {
  // solution 2: format + split + join
  public String reverseWords(String s) {
    if (s == null) {
      return null;
    }

    s = s.trim();
    if (s.length() == 0) {
      return s;
    }

    String[] words = s.split(" +");
    StringBuilder sb = new StringBuilder();
    sb.append(words[words.length - 1]);
    for (int i = words.length - 2; i >= 0; i--) {
      sb.append(" ").append(words[i]);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(new Solution().reverseWords("  the   sky is blue  "));
  }

//     // solution 1: 格式化 + reverse整个串 + reverse单个单词
//     public String reverseWords(String s) {
//         if (s == null) {
//             return null;
//         }
//         s = s.trim();
//         if (s.length() == 0) {
//             return s;
//         }
//         s = format(s);
//         char[] chars = s.toCharArray();
//         reverseSentence(chars);
//         reverseEachWord(chars);
//         return new String(chars);
//     }

//     private String format(String s) {
//         StringBuilder sb = new StringBuilder();
//         for (char c : s.toCharArray()) {
//             if (c == ' ' && sb.charAt(sb.length() - 1) == ' ') {
//                 continue;
//             }
//             sb.append(c);
//         }
//         return sb.toString();
//     }

//     private void reverseSentence(char[] chars) {
//         reverseChars(chars, 0, chars.length);
//     }

//     private void reverseEachWord(char[] chars) {
//         int n = chars.length;
//         int i = 0;
//         while (i < n) {
//             int wordLen = 1;
//             while (i + wordLen < n && chars[i + wordLen] != ' ') {
//                 wordLen++;
//             }
//             reverseChars(chars, i, wordLen);
//             i += 1 + wordLen;
//         }
//     }

//     private void reverseChars(char[] chars, int offset, int len) {
//         int i = 0;
//         while (i < len - 1 - i) {
//             swap(chars, offset + i, offset + len - 1 - i);
//             i++;
//         }
//     }

//     private void swap(char[] chars, int i, int j) {
//         char tmp = chars[i];
//         chars[i] = chars[j];
//         chars[j] = tmp;
//     }
}
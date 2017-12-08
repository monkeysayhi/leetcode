package com.msh.solutions._28_Implement_strStr_;

/**
 * Created by monkeysayhi on 2017/12/8.
 */
// [AC] 2. rabin-karp, O (m + n), do not use kmp
public class FollowUp1 {

  /**
   * 假设 m > n
   * 1. 用hash的比较代替字符串的比较。一般假设hash不碰撞，则比较的时间复杂度降为O(1)，需要比较(m-n)次
   * 2. tagSrc是固定的，而srcStr是渐变的，但更新的位固定。更新srcHash的时间复杂度为O(1)
   * 3. 初始化srcHash和tgtHash的时间复杂度为O(m + n)
   * 则，时间复杂度共 O(m + n) + (m-n) * O(1) = O(m + n)
   */

  private final static int MAGIC = 31;
  private final static int MODE = 100007;

  public int strStr(String haystack, String needle) {
    if (haystack == null || needle == null) {
      return -1;
    }
    if (haystack.length() < needle.length()) {
      return -1;
    }
    if (needle.length() == 0) {
      return 0;
    }

    int srcHash = 0;
    int tgtHash = 0;
    int POW = 1;
    for (int j = 0; j < needle.length(); j++) {
      srcHash = ((srcHash * MAGIC) % MODE + haystack.charAt(j)) % MODE;
      tgtHash = ((tgtHash * MAGIC) % MODE + needle.charAt(j)) % MODE;
      if (j > 0) {
        POW = (POW * MAGIC) % MODE;
      }
    }

    for (int i = 0; i + needle.length() <= haystack.length(); i++) {
      // 躲坑：先判断迭代条件 i > 0，再迭代 srcHash
      if (i > 0) {
        int minus = (haystack.charAt(i - 1) * POW) % MODE;
        int plus = haystack.charAt(i - 1 + needle.length());
        srcHash = (((srcHash + MODE - minus) % MODE * MAGIC) % MODE + plus) % MODE;
      }
      if (srcHash == tgtHash) {
        boolean eq = true;
        for (int j = 0; j < needle.length(); j++) {
          if (haystack.charAt(i + j) != needle.charAt(j)) {
            eq = false;
            break;
          }
        }
        if (eq) {
          return i;
        }
      }
    }
    return -1;
  }
}

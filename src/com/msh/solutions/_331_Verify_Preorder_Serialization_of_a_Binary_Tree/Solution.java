package com.msh.solutions._331_Verify_Preorder_Serialization_of_a_Binary_Tree;

/**
 * Created by monkeysayhi on 2018/3/28.
 */
public class Solution {
  private static class Result {
    private boolean isValid;
    private int nodeCnt;

    private static final Result INVALID_RS = new Result(false, 0);

    private Result(boolean isValid, int nodeCnt) {
      this.isValid = isValid;
      this.nodeCnt = nodeCnt;
    }
  }

  // 类似于分治，Result(isValid, nodeCnt)，但是个先序dfs
  public boolean isValidSerialization(String preorder) {
    // assume valid
    String[] strs = preorder.split(",");
    Result rs = isValidInt(strs, 0);
    return rs.isValid && rs.nodeCnt == strs.length;
  }

  private Result isValidInt(String[] strs, int offset) {
    if (offset >= strs.length) {
      return Result.INVALID_RS;
    }
    if (strs[offset].equals("#")) {
      return new Result(true, 1);
    }
    Result lRs = isValidInt(strs, offset + 1);
    if (!lRs.isValid) {
      return Result.INVALID_RS;
    }
    Result rRs = isValidInt(strs, offset + 1 + lRs.nodeCnt);
    if (!rRs.isValid) {
      return Result.INVALID_RS;
    }
    return new Result(true, 1 + lRs.nodeCnt + rRs.nodeCnt);
  }
}
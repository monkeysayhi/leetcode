package com.msh.solutions._241_Different_Ways_to_Add_Parentheses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by monkeysayhi on 2018/4/9.
 */
public class Solution {
  // solution -1: 如果不区分符号的优先级，则可将小括号套到每一步运算上，枚举运算顺序就得到了所有小括号方案，即求运算顺序的全排列. O(n!). badcase: `((2*3)-(4*5)) = -14`，“`(2*3)`”与“`(4*5)`”之间没有先后顺序
  // solution 1: 动规/分治，设dp[i][j]为“第i个数字到第j个数字组成的表达式的结果集”，则dp[i][j] = union{dp[i][k] X dp[k + 1][j] | i <= k < j}
  public List<Integer> diffWaysToCompute(String input) {
    if (input == null || input.length() == 0) {
      return new ArrayList<>();
    }

    if (input.startsWith("-")) {
      input = "0" + input;
    }
    String[] numStrs = input.split("\\+|-|\\*");
    int[] nums = new int[numStrs.length];
    for (int i = 0; i < numStrs.length; i++) {
      nums[i] = Integer.valueOf(numStrs[i]);
    }
    char[] ops = new char[numStrs.length - 1];
    for (int i = 0, j = 0; i < input.length(); i++) {
      char ch = input.charAt(i);
      if (ch == '+' || ch == '-' || ch == '*') {
        ops[j++] = ch;
      }
    }

    List<Integer>[][] dp = new List[nums.length][nums.length];
    return diffWaysToComputeInt(nums, ops, dp, 0, nums.length - 1);
  }

  private List<Integer> diffWaysToComputeInt(int[] nums, char[] ops, List<Integer>[][] dp,
                                             int l, int r) {
    if (dp[l][r] != null) {
      return dp[l][r];
    }
    if (l == r) {
      dp[l][l] = new ArrayList<>(1);
      dp[l][l].add(nums[l]);
      return dp[l][l];
    }
    if (l + 1 == r) {
      dp[l][l + 1] = new ArrayList<>(1);
      dp[l][l + 1].add(eval(nums[l], nums[l + 1], ops[l]));
      return dp[l][l + 1];
    }

    List<Integer> rs = new LinkedList<>();
    for (int m = l; m < r; m++) {
      char op = ops[m];
      List<Integer> rs1 = diffWaysToComputeInt(nums, ops, dp, l, m);
      List<Integer> rs2 = diffWaysToComputeInt(nums, ops, dp, m + 1, r);
      for (int num1 : rs1) {
        for (int num2 : rs2) {
          rs.add(eval(num1, num2, op));
        }
      }
    }
    dp[l][r] = rs;
    return dp[l][r];
  }

  private int eval(int num1, int num2, char op) {
    if (op == '+') {
      return num1 + num2;
    } else if (op == '-') {
      return num1 - num2;
    } else { // op == '*'
      return num1 * num2;
    }
  }
}
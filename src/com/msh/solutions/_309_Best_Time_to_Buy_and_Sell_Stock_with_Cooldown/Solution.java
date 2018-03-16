package com.msh.solutions._309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown;

/**
 * Created by monkeysayhi on 2018/1/22.
 */
public class Solution {
  // 最大利润，判断为最优化问题
  // 第i天是买入状态的最大利润：buy[i] = max{buy[i - 1], cooldown[i - 1] - prices[i]}
  // 第i天是卖出状态的最大利润：sell[i] = buy[i - 1] + prices[i]
  // 第i天是冷却状态的最大利润：cooldown[i] = max{cooldown[i - 1], sell[i - 1]}
  // 可以将数组优化掉
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int n = prices.length;
    int[] buy = new int[n];
    int[] sell = new int[n];
    int[] cooldown = new int[n];
    buy[0] = - prices[0];
    sell[0] = 0;
    cooldown[0] = 0;
    for (int i = 1; i < n; i++) {
      buy[i] = Math.max(buy[i - 1], cooldown[i - 1] - prices[i]);
      // sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
      sell[i] = buy[i - 1] + prices[i];
      cooldown[i] = Math.max(cooldown[i - 1], sell[i - 1]);
    }
    return Math.max(sell[n - 1], cooldown[n - 1]);
  }
}
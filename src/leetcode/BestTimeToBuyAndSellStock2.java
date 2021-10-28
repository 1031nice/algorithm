package leetcode;

public class BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int stock = 0;
        boolean isHolding = false;
        for(int i=0; i<prices.length-1; i++) {
            if(prices[i+1] > prices[i] && !isHolding) {
                isHolding = true;
                stock = prices[i];
            }
            else if(prices[i+1] < prices[i] && isHolding) {
                isHolding = false;
                profit += prices[i] - stock;
            }
        }
        return isHolding ? profit + prices[prices.length-1] - stock : profit;
    }
}

package com.jiuzhang.单调栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class FinalDiscountedPrice {
    // input price list
    // output final price list
    // the price will be subtracted by the price of the first lower or the same price item on the right side of the item
    
    public int[] FinalDiscountedPrice(int[] prices) {
        if (prices == null || prices.length == 0) {
            return prices;
        }
        
        int[] res = Arrays.copyOf(prices, prices.length);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < res.length; i++) {
            int price = prices[i];
            while (!stack.isEmpty() && prices[stack.peekLast()] >= price) {
                int current = stack.pollLast();
                res[current] = prices[current] - price;
            }
            stack.offer(i);
        }
        return res;
    }
}

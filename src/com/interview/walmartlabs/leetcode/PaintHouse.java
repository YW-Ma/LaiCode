package com.interview.walmartlabs.leetcode;

public class PaintHouse {
    public int minCost(int[][] costs) {
        // n houses, thre colors, costs is nx3
        int[] dp = new int[3];
        // base rule:
        for (int i = 0; i < 3; i++) {
            dp[i] = costs[0][i];
        }
        
        // induction rule:
        for (int i = 1; i < costs.length; i++) {
            // for each house, check the price and store
            int first = costs[i][0] + Math.min(dp[1], dp[2]);
            int second = costs[i][1] + Math.min(dp[0], dp[2]);
            int third = costs[i][2] + Math.min(dp[0], dp[1]);
            dp[0] = first;
            dp[1] = second;
            dp[2] = third;
        }
        
        return Math.min(Math.min(dp[0], dp[1]), dp[2]);
    }
}

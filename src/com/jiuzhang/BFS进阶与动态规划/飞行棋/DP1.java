package com.jiuzhang.BFS进阶与动态规划.飞行棋;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DP1 {
    // from left to right
    // DP[i] stands for "the least steps to jump to current position"
    // DP[i] = min{ min(prev 6 status) + 1 , connection"from" + 0 }

    public static void main(String[] args) {
        int[][] connections = new int[][]{{7, 9}, {8, 14}};
        // 1->7(9)
        // 9->15
        int length = 15;
        System.out.println(modernLudo(length, connections));
    }

    // 输入一维飞行棋的length和可以单向瞬间移动的connections
    // 棋盘用int表达，从1开始到length
    public static int modernLudo(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
        int[] dp = new int[length + 1];
        // base rule:
        dp[0] = 0; // dp[0] has no use
        dp[1] = 0;
        // induction rule;
        for (int i = 2; i <= length; i++) {
            dp[i] = Integer.MAX_VALUE;
            // first situation
            int leftLimit = Math.max(i - 6, 1);
            for (int j = i - 1; j >= leftLimit; j--) {
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }

            // second situation
            for (int j : graph.get(i)) {
                dp[i] = Math.min(dp[i], dp[j]);
            }
        }

        return dp[length];
    }

    public static Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        // we will search "from" by "to", so key is "to"
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] connection : connections) {
            graph.get(connection[1]).add(connection[0]);
        }

        return graph;
    }
}

package com.jiuzhang.BFS进阶与动态规划.飞行棋;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DP2 {
    // from right to left filling the dp
    // DP[i] stands for "the least steps of jump from current node to the end node"
    // DP[i] = min{ min(future 6 status) + 1 , connection"to" + 0 }

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
        dp[length] = 0;
        // induction rule;
        for (int i = length - 1; i >= 1; i--) {
            dp[i] = Integer.MAX_VALUE;
            // first situation
            int rightLimit = Math.min(i + 6, length);
            for (int j = i + 1; j <= rightLimit; j++) {
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }

            // second situation
            for (int j : graph.get(i)) {
                dp[i] = Math.min(dp[i], dp[j]);
            }
        }

        return dp[1];
    }

    public static Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        // we will search "to" by "from", so key is "from"
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] connection : connections) {
            graph.get(connection[0]).add(connection[1]);
        }

        return graph;
    }
}

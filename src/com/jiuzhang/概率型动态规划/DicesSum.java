package com.jiuzhang.概率型动态规划;

import java.util.*;

public class DicesSum {
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        double[][] dp = new double[n + 1][6 * n + 1];

        // base rule:
        for (int j = 1; j <= 6; j++) {
            dp[1][j] = 1 / 6.0;
        }

        // induction rule:
        for (int i = 2; i <= n; i++) { // 枚举骰子的个数
            for (int j = 1; j <= 6 * i; j++) { // 枚举当前的sum值
                for (int k = 1; k <= 6; k++) { // 枚举当前骰子的点数
                    if (j < k) { // 试图寻找上一个状态，如果sum值低于点数，说明没有合适的上一个状态
                        continue;
                    }
                    dp[i][j] += dp[i - 1][j - k] / 6; // 这种k的情况发生的概率是 1/6
                }
            }
        }

        List<Map.Entry<Integer, Double>> ans = new ArrayList<>();
        for (int j = n; j <= 6 * n; j++) {
            ans.add(new AbstractMap.SimpleEntry<Integer, Double>(j, dp[n][j]));
        }
        return ans;
    }
}

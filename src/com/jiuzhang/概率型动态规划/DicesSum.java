package com.jiuzhang.概率型动态规划;

import java.util.*;

public class DicesSum {
    public static void main(String[] args) {
        dicesSum(3);
    }

    public static List<Map.Entry<Integer, Double>> dicesSum(int n) {
        double[][] dp = new double[n + 1][6 * n + 1];

        // base rule:
        for (int j = 1; j <= 6; j++) {
            dp[1][j] = 1 / 6.0;
        }

        // induction rule:
        for (int i = 2; i <= n; i++) { // 枚举骰子的个数
            for (int j = 1; j <= 6 * i; j++) { // 枚举当前“可能”的sum值，也就是把当前这一次投掷已经算上了

                // 全概率公式：
                for (int k = 1; k <= 6; k++) { // 枚举当前这次可能投出骰子的点数
                    if (j < k) { // 有那么一些sum值，是当前投掷无法达到的，比如试图sum = 1，但是当前想投掷6
                        continue;
                    }
                    dp[i][j] += dp[i - 1][j - k] / 6; // 这种k的情况发生的概率是 1/6，和最里层for循环没有关系，是用数学退出来的。
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

package com.jiuzhang.概率型动态规划.马在棋盘上的概率;

public class KnightProbability {
    int[] deltaRow = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    int[] deltaCol = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[N][N][K + 1]; // dp[i][j][k]是马在第k步走上(i,j)的概率
        // base rule: 在开始位置时，概率为1
        dp[r][c][0] = 1;

        // induction rule:
        for (int step = 1; step <= K; step++) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    for (int direction = 0; direction < 8; direction++) {
                        int lastRow = row + deltaRow[direction];
                        int lastCol = col + deltaCol[direction];
                        // 从last point 跳到当前点的概率是 1/8
                        if (isValid(N, lastRow, lastCol)) {
                            dp[row][col][step] += dp[lastRow][lastCol][step - 1] / 8;
                        }
                    }
                }
            }
        }

        // build results:
        double res = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                res += dp[row][col][K]; // 第K步即最后一步
            }
        }
        return res;
    }

    private boolean isValid(int N, int lastRow, int lastCol) {
        if (lastCol < 0 || lastRow < 0 || lastRow >= N || lastCol >= N) {
            return false;
        }
        return true;
    }
}

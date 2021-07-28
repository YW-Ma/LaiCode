package com.study.algorithms.class13_DP_3.重听.ConsecutiveOnes;

public class LargestSquareSurroundedByOne {
    public int largestSquareSurroundedByOne(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxEdge = 0;

        int[][] left = new int[rows][cols]; // 存储(i-1, j-1)左侧的最长连续1 左侧的最长边
        int[][] up = new int[rows][cols];

        // induction rule & base rule
        //          注意：要从0开始，不然漏掉只有一行或者一列的情况
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) { // 要负责update maxEdge了
                    left[i][j] = (j == 0) ? matrix[i][0] : left[i][j - 1] + 1; // (base rule) : (induction rule)
                    up[i][j] = (i == 0) ? matrix[0][j] : up[i - 1][j] + 1;

                    int currentEdge = getCurrentEdge(i, j, up, left, matrix);
                    maxEdge = Math.max(currentEdge, maxEdge);
                } else { // 没必要，本来就是0
                    left[i][j] = 0;
                    up[i][j] = 0;
                }
            }
        }
        return maxEdge;
    }

    private int getCurrentEdge(int i, int j, int[][] up, int[][] left, int[][] matrix) {
        for (int minLen = Math.min(up[i][j], left[i][j]); minLen >= 1; minLen--) {
            if (up[i][j - minLen + 1] >= minLen && left[i - minLen + 1][j] >= minLen) {
                return minLen;
            }
        }
        return 0;
    }
}

package com.study.algorithms.class13_DP_3.重听.ConsecutiveOnes;

public class LargestCrossOfOnes {
    public int largest(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        // do largest consecutive 1 for each directions
        // M1 left -> right
        // M2 right -> left
        // M3 top -> down
        // M4 bottom -> up

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] M1 = new int[rows][cols];
        int[][] M2 = new int[rows][cols];
        int[][] M3 = new int[rows][cols];
        int[][] M4 = new int[rows][cols];

        // left -> right
        for (int row = 0; row < rows; row++) {
            int count = 0;
            for (int col = 0; col < cols; col++) {
                count = matrix[row][col] == 1 ? count + 1 : 0;
                M1[row][col] = count;
            }
        }

        // right -> left
        for (int row = 0; row < rows; row++) {
            int count = 0;
            for (int col = cols - 1; col >= 0; col--) {
                count = matrix[row][col] == 1 ? count + 1 : 0;
                M2[row][col] = count;
            }
        }

        // top -> down
        for (int col = 0; col < cols; col++) {
            int count = 0;
            for (int row = 0; row < rows; row++) {
                count = matrix[row][col] == 1 ? count + 1 : 0;
                M3[row][col] = count;
            }
        }

        // bottom -> up
        for (int col = 0; col < cols; col++) {
            int count = 0;
            for (int row = rows - 1; row >= 0; row--) {
                count = matrix[row][col] == 1 ? count + 1 : 0;
                M4[row][col] = count;
            }
        }

        // post processing (merge)
        return merge(M1, M2, M3, M4);
    }

    private int merge(int[][] M1, int[][] M2, int[][] M3, int[][] M4) {
        int MaxOfMin = Integer.MIN_VALUE;
        for (int i = 0; i < M1.length; i++) {
            for (int j = 0; j < M1[0].length; j++) {
                MaxOfMin = Math.max(Math.min(Math.min(Math.min(M1[i][j], M2[i][j]), M3[i][j]), M4[i][j]), MaxOfMin);
            }
        }
        return MaxOfMin;
    }
}

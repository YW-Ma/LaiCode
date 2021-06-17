package com.study.algorithms.class12_DP_2.重听;

public class LargestSquareOfOnes {
    // 以[i][j] 为右下角
    // 检查左上、左、上的值。
    // 最小值 + 1，就是自己   -->     如果能数出来 nxn, 那么左上角一定是至少 (n-1)x(n-1) ，左边一定是至少 (n-1)x(n-1)
    //                              如果左上、左边有冲突，那么代表他们小于 n-1, 但是当前最小边为 n-1, 发生矛盾。 所以不可能有问题。

    //
    //  [     M         ]
    //
    //  N
    //
    //  [     M         ]

    public int largest(int[][] matrix) {
        final int N = matrix.length;
        final int M = matrix[0].length;
        int[][] largest = new int[N][M]; // largest[i][j] represents the side length of the largest square of 1's with right bottom corner at matrix[i][j]
        int globalMax = 0;

        // base rule:
        for (int i = 0; i < N; i++) {
            largest[i][0] = matrix[i][0];
            if (matrix[i][0] == 1) {
                globalMax = 1;
            }
        }
        for (int j = 0; j < M; j++) {
            largest[0][j] = matrix[0][j];
            if (matrix[0][j] == 1) {
                globalMax = 1;
            }
        }

        // induction rule:
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }

                int upper = largest[i - 1][j];
                int upperLeft = largest[i - 1][j - 1];
                int left = largest[i][j - 1];

                int smallest = Math.min(upper, Math.min(upperLeft, left)); // get the smallest square, and expand based on it.
                largest[i][j] = smallest + 1;
                globalMax = Math.max(globalMax, largest[i][j]);
            }
        }
        return globalMax;
    }
}

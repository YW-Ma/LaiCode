package com.interview.Google;

public class MaximumNumberOfPointsWithCost_followup {
    public static void main(String[] args) {
        int[][] points = new int[][]{{0,3,0,4,2},{5,4,2,4,1},{5,0,0,5,1},{2,0,1,0,3}};
        maxPoints(points);
    }

    // 目标：干掉for k 循环，复杂度 O(m*n*n) --> O(m*n)
    public static long maxPoints(int[][] points) {
        // skip corner case ...
        int rows = points.length;
        int cols = points[0].length;
        long[] cur = new long[cols];
        long[] prev = new long[cols];
        // base rule:
        for (int i = 0; i < cols; i++) {
            prev[i] = points[rows - 1][i];
        }
        // induction rule 可以写成
        //   1.  j - k >= 0:     -->    (k定义域是 k <= j)
        //          cur[j] = max{prev[k] - (j-k)} + points[i][j]   -->  之前嵌套在一起，是为了枚举max里面的内容。
        //                 = max{prev[k] + k} + points[i][j]  - j
        //   2.  j - k < 0:     -->    (k定义域是 k > j)
        //          cur[j] = max{prev[k] - (k-j)} + points[i][j]
        //                 = max{prev[k] - k} + points[i][j]  + j
        //   所以实际上j和k的循环是不需要嵌套在一起行成O(n*n)的模块的。
        //   枚举所有j即可，max部分可以通过一个`best`变量来携带。
        for (int i = rows - 2; i >= 0; i--) {
            // j循环，同时跟进对应的max{} 模块
            long curMax = Long.MIN_VALUE;
            // situation 1
            for (int j = 0; j < cols; j++) {
                // k<=j, 正序遍历j的过程中，可以获得k<=j中的max{prev[k] + k}
                curMax = Math.max(curMax, prev[j] + j);
                cur[j] = curMax + points[i][j] - j;
            }
            // situation 2
            curMax = Long.MIN_VALUE;
            for (int j = cols - 1; j >= 0; j--) {
                // k>j, 倒序遍历j的过程中，可以获得k>j中的max{prev[k] - k}
                curMax = Math.max(curMax, prev[j] - j);
                cur[j] = Math.max(cur[j], curMax + points[i][j] + j); // 要和situation 1一较高下，所以需要Math.max()
            }
            long[] tmp = prev;
            prev = cur;
            cur = tmp; // the value in previous row is of no use now.
        }

        long max = 0;
        for (int i = 0; i < cols; i++) {
            max = Math.max(prev[i], max);
        }
        return max;
    }
}

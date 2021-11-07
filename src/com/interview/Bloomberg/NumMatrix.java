package com.interview.bloomberg;

public class NumMatrix {
    // https://leetcode-cn.com/problems/range-sum-query-2d-immutable/solution/er-wei-qu-yu-he-jian-suo-ju-zhen-bu-ke-b-2z5n/
    
    // 给定matrix，支持sumRegion操作，求出给定左上角、右下角区域的sum。
    // 用前缀和： pS[i][j]是以 (i+1,j+1) 即第i,j个row/col 为右下角的矩阵的size
    // 前缀和为了方便使用，一般要把(0,0)也包括进去，这样整个的sum才能是 pS[i][j] - pS[0][0]
    
    // Step 1: 递推求preSum --> 写在构造函数里面
    // ps[i][j] = ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1] + matrix[i-1][j-1]   --> 注意下标含义
    // i>=0, j>=0. 依赖左侧和上方，所以直接正常遍历就行。
    
    // Step 2: 用sumRegion来求size
    // sr(x1,y1,x2,y2) => 把x1,y1,x2,y2 都+1 方便换算成下标 => ps[x2][y2] - ps[x1-1][y2] - ps[x2][y1-1] + ps[x1-1][y1-1]  注意要-1 因为要包括x1,y1在内。
    //
    
    int[][] preSum;
    int[][] matrix;
    int rows;
    int cols;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = matrix;
        this.preSum = new int[rows + 1][cols + 1];
        // compute preSum
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i][j] + matrix[i][j];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // corner case:
        if (rows == 0) {
            return 0;
        }
        // compute sum using preSum.
        return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
    }
}

package com.interview.Google;

public class MaximumNumberOfPointsWithCost {
    public static void main(String[] args) {
        int[][] points = new int[][]{{1,5},{2,3},{4,2}};
        maxPoints(points);
    }
    
    // https://leetcode.com/problems/maximum-number-of-points-with-cost/
    // input: m x n int matrix
    // want to maximize the number of points you can get from the matrix
    // --> to gain points, you must pick on cell in each row
    // --> however, will lose point if pick a cell too far from the cell picked in previous line
    // subtract abs(c1 - c2)
    
    // 直观感受 --> 暴力的方法就是DFS，每行选择一个。
    // 但是可以动态规划，因为很轻松发现sub-problem几乎都是重复的。
    
    // 应该要记录每行各个value开始递推的最大points。开销O(m*n*n)  -->  n^2 是因为每行的元素都要走一行。
    // 刚好的开销，是抛开位置信息，只存储这行可能的最大值。 见第二个解法
    
    // 举例:
    /*   matrix         dp
    *   1   5           7   11   --> output 11
    *   2   3           6   6
    *   4   2           4   2
    * */
    // max(下一行 - cost) + 自己的value
    
    // 实际上和那个房子题一样，我记录完 4 2以后就只用一次。
    // 所以其实两个array就够了
    
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
        // induction rule
        for (int j = rows - 2; j >= 0; j--) {
            
            for (int i = 0; i < cols; i++) {
                long max = 0;
                //             之前这组确实有重复，比如 第1个 和 第len-1个，的内层for k循环其实是一样的
                for (int k = 0; k < cols; k++) {
                    max = Math.max(max, prev[k] - Math.abs(i - k));
                }
                cur[i] = max + points[j][i];
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

package com.study.algorithms.class12_DP_2.重听;

public class EditDistance {
    // 递归：
    //      如何保证问题规模在缩小
    //      问题的大小由长度确定，只要（insert target首字母），（delete 不相同的首字母），（change 首字母为target首字母）
    //      就可以保证子问题更小（串更短了）


    // DP：
    // 发现递归有很多子问题有重复
    // 设置M和M的填写方式：
    // (1) 由于一个问题由两个变量唯一确定（即source和target两个字符串），所以需要一个 2D Array 来存储子问题结果
    // (2) 由于编辑距离每次操作开头，所以子问题其实是靠近尾巴的那个（当一个string为空的时候）。即base case应填写2D Array的最右侧和最底层。

    // 依然是三个options，对应不同的右下方子问题。
    // M[i][j] represents the minimum number of operations to convert the letters from after i of s1, and after j of s2.

    // 不要参考课件的M矩阵定义，它是前i、j个字母。和recursion差别比较大，不方便理解。


    // Solution 1: Recursion
    // Recursive way to solve this problem:
    // (1) each level, operate src, try three methods to let it closer to tar
    // (2) so there are three branches in each level, each lead to a smaller problem.
    // (3) base case is src == tar(return 0), or one of them is empty(return the length of the other one).    empty can include the src == tar situation by don't add # of operations

    //           src    tar
    //          (asdf, sghj)
    //           /       |        \
    //       +s       a->s         -a
    // (asdf, ghj)   (sdf, ghj)    (sdf, sghj)
    //
    //      #1          #2            #3
    //      +1          +1 or +0       +1
    //           \      |         /
    //               return the min

    public int editDistanceRecursion(String src, String tar) {
        return helper(src, tar, 0, 0); // calculate the edit distance of [0,length)
    }

    private int helper(String src, String tar, int i, int j) {
        // base case: remaining part is empty:
        if (src.length() == i) {
            return tar.length() - j;
        }
        if (tar.length() == j) {
            return src.length() - i;
        }

        // recursion rule:
        int add = helper(src, tar, i, j + 1) + 1;
        int replace = helper(src, tar, i + 1, j + 1) + (src.charAt(i) == tar.charAt(j) ? 0 : 1);
        int delete = helper(src, tar, i + 1, j) + 1;

        return Math.min(add, Math.min(replace, delete));
    }


    // Solution 2: DP
    public int editDistance(String src, String tar) {
        // assume src and tar are not null
        int[][] dist = new int[src.length() + 1][tar.length() + 1];
        // base rule:
        for (int i = 0; i <= src.length(); i++) {
            dist[i][tar.length()] = src.length() - i;
        }
        for (int j = 0; j <= tar.length(); j++) {
            dist[src.length()][j] = tar.length() - j;
        }
        // induction rule:
        for (int i = src.length() - 1; i >= 0; i--) {
            for (int j = tar.length() - 1; j >= 0; j--) {
                int add = dist[i][j + 1] + 1;
                int replace = dist[i + 1][j + 1] + (src.charAt(i) == tar.charAt(j) ? 0 : 1);
                int delete = dist[i + 1][j] + 1;
                dist[i][j] = Math.min(add, Math.min(replace, delete));
            }
        }
        // return the solution of original problem
        return dist[0][0];
    }
}

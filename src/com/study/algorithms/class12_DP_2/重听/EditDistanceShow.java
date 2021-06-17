package com.study.algorithms.class12_DP_2.重听;

public class EditDistanceShow {
    public int editDistance1(String src, String tar) {
        return helper(src, tar, 0, 0);
    }

    private int helper(String src, String tar, int i, int j) {
        // current size of problem:
        // src[i, src.length)
        // tar[j, tar.length)

        // base case
        if(src.length() == i) {
            return tar.length() - j;
        }
        if(tar.length() == j) {
            return src.length() - i;
        }

        // recursion rule:expect a child return the editDistance of its subproblem
        int add = helper(src, tar, i, j + 1) + 1;
        int replace = helper(src, tar, i + 1, j + 1) + (src.charAt(i) == tar.charAt(j) ? 0 : 1);
        int delete = helper(src, tar, i + 1, j) + 1;

        return Math.min(add, Math.min(replace, delete));
    }


    public int editDistance2(String src, String tar) {
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
            }}

        // return the solution of original size of problem
        return dist[0][0];

        // Time: O(n*m), n,m is the length of src, tar
        // Space: O(n*m)
    }

}

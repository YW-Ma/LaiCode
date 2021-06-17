package com.study.algorithms.class11_DP_1.重听;

public class MaxProductAgain {

    // 难点：左大段不再是严格的子问题
    // 本体的要求是"at least one cut", 但是左大段却可以不cut。

    public int maxProduct(int length) {
        int[] M = new int[length + 1]; // i-th element stores the solution to rope length = i
        // base rule:
        M[0] = 1;
        M[1] = 1;
        M[2] = 1; // at least one cut.

        // __ | _

        // induction rule:
        for (int i = 1; i <= length; i++) {
            // first i meters
            int max = Integer.MIN_VALUE;
            for (int j = 1; j < i; j++) {
                // cut before j meter (at least one cut, so starts at 1)
                int left = Math.max(M[j], j); // 左大段可以不cut
                int right = i - j;
                max = Math.max(max, left * right);
            }
            M[i] = max;
        }
        return M[length];
    }
    // Time O(n^2)
}

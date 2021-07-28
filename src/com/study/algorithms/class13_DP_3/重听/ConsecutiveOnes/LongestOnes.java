package com.study.algorithms.class13_DP_3.重听.ConsecutiveOnes;

public class LongestOnes {
    public int longest(int[] array) {
        // DP: base rule: count = 0
        // induction rule: if 1 count++, if 0 count=0

        int count = 0;
        int globalMax = 0;
        for (int i : array) {
            if (i == 1) {
                count++;
                globalMax = Math.max(globalMax, count);
            } else {
                count = 0;
            }
        }
        return globalMax;
    }
}

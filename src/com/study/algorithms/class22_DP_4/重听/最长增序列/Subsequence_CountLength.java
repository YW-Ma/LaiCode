package com.study.algorithms.class22_DP_4.重听.最长增序列;

public class Subsequence_CountLength {
    // review: 默认值可以先写。 但是那样要让globalMax也从默认值开始。
    // 一个月后重新做的：感觉写的最好。
    // O(n^2)
    public int longest(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int[] length = new int[array.length]; // ith ele record the LAS ends at i
        length[0] = 1;
        int globalMax = 1;
        for (int i = 1; i < array.length; i++) {
            int curMax = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    curMax = Math.max(curMax, length[j]);
                }
            }
            length[i] = curMax + 1;
            globalMax = Math.max(globalMax, length[i]);
        }

        return globalMax;
    }
}

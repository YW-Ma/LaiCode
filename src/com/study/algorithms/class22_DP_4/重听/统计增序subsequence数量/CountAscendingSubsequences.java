package com.study.algorithms.class22_DP_4.重听.统计增序subsequence数量;

public class CountAscendingSubsequences {
    // DP:
    // base rule:       only one element --> one subsequence
    // induction rule:  after adding a new element, increase the number by sum(DP[j]), where 0<=j<i, and a[j] < a[i] (increasing)
    public int numIncreasingSubsequences(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        
        int[] DP = new int[a.length]; // storing how many ascending subsequence ends at a[i] can be generated.
        DP[0] = 1;
        int result = 1;
        for (int i = 1; i < a.length; i++) {
            int sum = 1;   // 加上只加上a[i]自己的情况
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    sum += DP[j];
                }
            }
            DP[i] = sum;
            result += sum;
        }
        
        // Since DP stores the results ending at a[i], so we need to sum up.
        return result;
    }
}

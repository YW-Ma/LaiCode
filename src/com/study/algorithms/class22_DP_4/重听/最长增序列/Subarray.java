package com.study.algorithms.class22_DP_4.重听.最长增序列;

public class Subarray {
    public int longest(int[] array) {
        // Assumption:
        // an integer array A
        // input {5,2,6,3,4,7,5}
        // output {3,4,7}
        // DP[i] represents the length of LAS of the subarray [0,i]
        // globalMax: record the maximum in DP array
        
        if (array == null || array.length == 0) {
            return 0;
        }
        
        int[] DP = new int[array.length];
        // base rule: DP[0] = 1;
        // induction rule:  if A[i] > A[i-1] --> DP[i] = DP[i-1] + 1
        //                  else DP[i] = 1
        int globalMax = 1;
        DP[0] = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                DP[i] = DP[i - 1] + 1;
                globalMax = Math.max(globalMax, DP[i]);
            } else {
                DP[i] = 1;
            }
        }
        
        return globalMax;
    }
}

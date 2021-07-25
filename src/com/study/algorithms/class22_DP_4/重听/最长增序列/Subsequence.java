package com.study.algorithms.class22_DP_4.重听.最长增序列;

public class Subsequence {
    public int longest_after_review(int[] array) {
        // review的修改：
        // 1. 可以沈略prev，prev的比较逻辑是可以和i的更新逻辑合并的。
        // 2. 默认值可以放在最前面 (DP[i] = 1)
        if (array == null || array.length == 0) {
            return 0;
        }
        
        int[] DP = new int[array.length];
        int globalMax = 1;
        DP[0] = 1;
        
        for (int i = 1; i < array.length; i++) {
            DP[i] = 1;
            for (int j = 0; j < array.length; j++) {
                if (array[j] < array[i] && DP[j] + 1 > DP[i]) {
                    DP[i] = DP[j] + 1;
                    globalMax = Math.max(globalMax, DP[i]);
                }
            }
        }
        return globalMax;
    }
    
    public int longest_before_review(int[] array) {
        // 最基本的算法：look back to previous i-1 elements to find out the one to add after。 所以是O(n) * O(n) * O(1) = O(n^2)
        if (array == null || array.length == 0) {
            return 0;
        }
        
        int[] DP = new int[array.length];
        int globalMax = 1;
        DP[0] = 1;
        
        for (int i = 1; i < array.length; i++) {
            int prev = i;
            for (int j = 0; j < array.length; j++) {
                if (array[j] < array[i] && DP[j] > DP[prev]) {
                    prev = j;
                }
            }
            
            if (prev != i) {
                DP[i] = DP[prev] + 1;
                globalMax = Math.max(globalMax, DP[i]);
            } else {
                DP[i] = 1;
            }
        }
        return globalMax;
    }
    
}

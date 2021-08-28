package com.study.algorithms.class22_DP_4.重听.最长增序列;

import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        Subsequence_GetSubsequence s1 = new Subsequence_GetSubsequence();
        int[] res = s1.longest(new int[]{5, 2, 6, 3, 4, 7, 5});
        System.out.println(Arrays.toString(res));
    }
}

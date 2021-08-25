package com.study.algorithms.class22_DP_4.重听.最长增序列;

import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        LongestAscendingSubSequence_NLogN longest = new LongestAscendingSubSequence_NLogN();
        int res = longest.binarySearch(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, 3);
        System.out.println(res);
        
        int results = longest.longest(new int[]{1,1,1,3,5,2});
        System.out.println(results);
    }
}

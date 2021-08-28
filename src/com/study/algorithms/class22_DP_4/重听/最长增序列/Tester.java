package com.study.algorithms.class22_DP_4.重听.最长增序列;

import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
<<<<<<< HEAD
        Subsequence_GetSubsequence s1 = new Subsequence_GetSubsequence();
        int[] res = s1.longest(new int[]{5, 2, 6, 3, 4, 7, 5});
        System.out.println(Arrays.toString(res));
=======
        LongestAscendingSubSequence_NLogN longest = new LongestAscendingSubSequence_NLogN();
        int res = longest.binarySearch(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, 3);
        System.out.println(res);
        
        int results = longest.longest(new int[]{1,1,1,3,5,2});
        System.out.println(results);
>>>>>>> a0a90c241fbb099d34d5f3dba40fa20e59d67bfe
    }
}

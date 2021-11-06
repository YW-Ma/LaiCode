package com.interview.walmartlabs.leetcode;

public class Tester {
}

//M[i][j] the max sum of the pizza you can get from i to j,
//        i : left side of the pizza
//        j : right side of the pizza
//
//        base case:
//        M[i][i]  = A[i];
//        M[0][1] = Math.max(A[0], A[1]);
//
//        induction rule:
//        if we take left: Case 1 =
//        if A[i + 1] > A[j]  :      M[i + 2][j] + A[i];   +2 因为i被我拿了，i+1被朋友拿了
//        if A[i + 1] <= A[j] :      M[i + 1][j - 1] + A[i];
//        if we take right: Case 2 =
//        if A[i] > A[j - 1]   :      M[i + 1][j - 1] +A[j];
//        if A[i] <= A[j - 1]  :      M[i][j - 2] + A[j];
//        M[j][i] = Math.max(Case1, Case2);
//        填表方向：
//        i始终依赖比自己大的，j始终依赖比自己小的。
//        i是不同行，依赖比自己更大的行。
//        j是不同列，依赖比自己更前的列。
        

package com.study.algorithms.class12_DP_2.重听;

public class LargestSquareOfOnes {
    // 以[i][j] 为右下角
    // 检查左上、左、上的值。
    // 最小值 + 1，就是自己   -->     如果能数出来 nxn, 那么左上角一定是至少 (n-1)x(n-1) ，左边一定是至少 (n-1)x(n-1)
    //                              如果左上、左边有冲突，那么代表他们小于 n-1, 但是当前最小边为 n-1, 发生矛盾。 所以不可能有问题。S
}

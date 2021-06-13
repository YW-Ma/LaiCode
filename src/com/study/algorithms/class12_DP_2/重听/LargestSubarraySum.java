package com.study.algorithms.class12_DP_2.重听;

public class LargestSubarraySum {
    // Subarray必须要是连续的（subsequence则是可以不连续（但是顺序要保证））
    // 一共有多少个subarray：如果有n个元素，那么一共有n+Cn2个可能性，因为相当于是n个元素里选择首尾。 或者以一个元素为起点，看右几个，一共是 (n+1) * n / 2

    // M --> M应当存储的是结果，所以应当存储以当前元素结尾的subarray中，sum最大的那个
    //      M[i] represents the largest sum of subarray ends at A[i]
    // 如何linear scan回头看？ -->  读取一个新的数字，它的max基于回头看：如果是负资产就不继承，是正资产就继承
    //      if previous M store a negative sum, then M[i] = A[i], else M[i] = A[i] + M[i-1] (inherit the sum or not)

    // { 1, 2, 4,-100,-2,10}
    //   0  1  2  3    4  5
    // M 1  3  7  -93 -2  10
    // L 0  0  0  0    4  5

    // Follow Up: 输出范围。 实际上相当于是输出左边界。
    // 记录一个left，记录当前answer的左边界。
    // Left[i] represents the left end of the subarray in the M[i] (whose right end is A[i])
    // 每次记录是"继承" 还是 "另起炉灶"， 前者延续Left[i-1], 后者则等于 i



    // 还可以优化空间：
    // 1. 需要记录globalMax：即{max, left, right} 这三个元素   -->  globalLeft, globalRight are respectively the left border and the right border of the subarray that achieves globalMax.
    // 2. 需要对当前值进行判断，即需要 prevM, prevLeft
    // 所以可以压缩到 O(5)


    // 另外的follow up：切单词题目的具体切法（即每一刀切在哪里），跳房子的题目则打印每一步是怎么做的。
    //

}

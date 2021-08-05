package com.study.algorithms.class07_Heap_and_Graph_Search.重听;

public class KthSmallestNumber {
    // a matrix of size nxm
    // each row are sorted, each column are sorted (ascending)
    // find the kth smallest in it

    // assume: integer, not empty, get the value of kth smallest

    /*
    *[[1,3,5,7],
    * [2,4,6,8],
    * [3',5,7,9]]
    * */

    // queue: [1]
    // 1  [2,3]
    // 2  [3,3',4]
    // 3  [3',4,5]
    // 3' <-- fourth smallest.

    // since there may be duplicate numbers, we need a wrapper class.

    public int kthSmallest(int[][] matrix, int k) {
        return 0;
    }
}


// aaabbbaaaa
// 0001233333
// 7654444321

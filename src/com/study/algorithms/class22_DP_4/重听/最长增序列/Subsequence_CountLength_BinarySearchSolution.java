package com.study.algorithms.class22_DP_4.重听.最长增序列;

public class Subsequence_CountLength_BinarySearchSolution {
    // how can deal with it within O(n logn) ?
    // if we change our mind,
    // let's say we store the end of all kind of lengths of ascending subsequence
    // then, if we get a number and use it to update these lengths.
    // we need find out the right place it can be use --> after the number that just smaller than it.

    // if there is a number in this space: update it
    // if this is an empty space: take it (there must have enough space)
    // how to find the number: binary search
    // how many times:


    // 5 2 6 3 4 7 5
    // len    index
    // 1        2
    // 2        3
    // 3        4
    // 4        5
    // 5

    // O(n logn)
    public int longest(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int[] len = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            len[i] = Integer.MAX_VALUE;
        }

        int globalMax = 0;
        for (int i = 0; i < array.length; i++) {
            int pos = binarySearch(len, array[i]);
            len[pos] = array[i];
            if (globalMax < pos + 1) {
                globalMax = pos + 1;
            }
        }
        return globalMax;
    }

    // find the index of largest value smaller than target
    // 1,2,3,4,5 --> find 2, but return 2 + 1
    // 1,2,4,5 --> find 2, but return 2 + 1
    public int binarySearch(int[] len, int target) {
        // 【review】 important corner case:
        if (len[0] >= target) { // 这样不和后面 +1 的逻辑一起走了。
            return 0;
        }

        int left = 0;
        int right = len.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (len[mid] < target) {
                left = mid; // may be the place we are looking for;
            } else if (len[mid] >= target) {
                right = mid; // cannot be ans
            }
        }
        // left is the index we want, but if the target is too small, remember to not +1 (in corner case)
        return left + 1;
    }
}

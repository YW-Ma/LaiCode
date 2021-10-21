package com.interview.walmartlabs.leetcode;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSort(new int[]{1, 5, 3, 4, 2})));
    }
    
    
    public static int[] mergeSort(int[] array) {
        // corner case check
        if (array == null || array.length <= 1) {
            return array;
        }
        // DFS
        return mergeSort(array, 0, array.length - 1);
    }
    
    // DFS: get middle, recur on each side, then merge
    private static int[] mergeSort(int[] array, int left, int right) {
        // base case
        if (left == right) {
            return new int[]{array[left]};
        }
        // sort each side
        int mid = left + (right - left) / 2;
        int[] leftSorted = mergeSort(array, left, mid);
        int[] rightSorted = mergeSort(array, mid + 1, right);
        // merge
        return merge(leftSorted, rightSorted);
    }
    
    private static int[] merge(int[] leftSorted, int[] rightSorted) {
        int[] res = new int[leftSorted.length + rightSorted.length];
        int w = 0;
        int l = 0;
        int r = 0;
        while (l < leftSorted.length && r < rightSorted.length) {
            if (leftSorted[l] <= rightSorted[r]) { // stable
                res[w++] = leftSorted[l++];
            } else {
                res[w++] = rightSorted[r++];
            }
        }
        
        while (l < leftSorted.length) {
            res[w++] = leftSorted[l++];
        }
        while (r < rightSorted.length) {
            res[w++] = rightSorted[r++];
        }
        return res;
    }
}

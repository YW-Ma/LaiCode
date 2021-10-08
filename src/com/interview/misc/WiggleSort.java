package com.interview.misc;

import java.util.Arrays;

public class WiggleSort {
// 3 5 2 1 6 4 --> input an integer array

// --> output an array that [0] <= [1] >= [2] <= [3] ...
// 3 5 1 6 2 4
// 1 6 2 5 3 4     both is acceptable

// solution 1: O(nlogn) sort and swap each pair (1,2,3,4,5) --> (2,1,4,3,5)
// solution 2: O(n)     one-pass:
    // compare the current element with the next, if the order is incorrect, we swap it.
    // we will never destory the order existed, e.g.  [0] <= [1] <= [2], we will swap 1 and 2, but 2 is still larger than 0.
    
    // using sort
    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums); // ascending
        for (int i = 1; i < nums.length - 1; i += 2) { // change each pair that not follow the rule.
            swap(nums, i, i + 1);
        }
    }
    
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    // using one-pass
    public static void wiggleSort2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) { // <=
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else { // >=
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }
}

package com.interview.walmartlabs.leetcode;

import java.util.Arrays;

public class TwoSumLessThanK {
    // Solution: sort + two pointers --> O(nlogn)
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int maxSum = -1; // since nums is positive int array
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum >= k) {
                j--;
            } else {
                maxSum = Math.max(maxSum, sum);
                i++;
            }
        }
        return maxSum;
    }
    
    // 注意，Arrays除了.sort(), 还拥有一个Arrays.binarySearch(arr, from, to, target)
    public static void main(String[] args) {
    
    }
}

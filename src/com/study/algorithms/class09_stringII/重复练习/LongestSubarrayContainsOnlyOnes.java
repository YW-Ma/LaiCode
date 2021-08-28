package com.study.algorithms.class09_stringII.重复练习;

public class LongestSubarrayContainsOnlyOnes {
    // Given an array of integers that contains only 0s and 1s and a positive integer k
    // you can flip at most k 0s to 1s, return the longest subarray that contains only integer 1 after flipping

    // k >= 0

    //  [1,1,0,0,1,1,1,0,0,0]  k = 2
    //   [
    //   ]
    //       ^ ^               这样有7
    //                 ^ ^     这样只有5
    // 转变题目：finding the longest subarray that contains at most k zeros

    // Time Complexity: O(n^2) --> since we need to find the first zero that has been converted.
    // SC: O(1)

    public int longestConsecutiveOnes(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int left = 0; // left是闭区间
        int right = 0; // right是开区间
        int zeroCount = 0;
        int globalMax = 0;

        while (right < nums.length) {
            if (nums[right] == 1) { // 下一个是1
                right++; // 把它吃进去
                globalMax = Math.max(globalMax, right - left); // 当前的right指向了下一个位置。
            } else {                // 下一个是0
                if (zeroCount < k) {
                    right++;
                    zeroCount++;
                    globalMax = Math.max(globalMax, right - left);
                } else {
                    if (nums[left] == 0) {
                        zeroCount--;
                    }
                    left++;
                }
            }
        }

        return globalMax;
    }
}

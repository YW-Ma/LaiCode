package com.jiuzhang.双指针与隔板;

// 209. Minimum Size Subarray Sum
// https://leetcode.com/problems/minimum-size-subarray-sum/
/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * */
public class MinimumSizeSubarraySum {
    // solution 1: binary search for each left border
    // solution 2: two pointer
    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        MinimumSizeSubarraySum minimumSizeSubarraySum = new MinimumSizeSubarraySum();
        int res = minimumSizeSubarraySum.minSubArrayLen(target, nums);
        System.out.println(res);
    }

    // Naive, using the format. O(n^2)
    public int minSubArrayLenNaive(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        for (int start = 0; start < nums.length; start++) {
            int end = start;
            // j --> right border
            int sum = 0;
            while (end < nums.length && sum < target) {
                sum += nums[end];
                end++;
            }
            if (sum >= target) {
                minLength = Math.min(minLength, end - start + 1);
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }

    // Solution 1: binary search. optimize Naive O(nlogn)
    public int minSubArrayLenBianrySearch(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        // construct prefix sum for binary search:
        // [2,3,1,2,4,3]
        //0 2 5 6 8 12 15
        //  [i,j] sum = ps[j+1] - ps[i]
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        for (int start = 0; start < nums.length; start++) {
            // binary search right border (possible no valid, so need to double-check)
            int end = findPossibleRightBorder(start, target, prefixSum);
            if (prefixSum[end + 1] - prefixSum[start] >= target) {
                if (minLength > end - start + 1) {
                    minLength = end - start + 1;
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }
    private static int findPossibleRightBorder(int start, int target, int[] prefixSum) {
        int l = start; // original start as the minimum possible right border
        int r = prefixSum.length - 2; // original end as maximum possible right border
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            //  [i,j] sum = ps[j+1] - ps[i]
            if (prefixSum[mid + 1] - prefixSum[start] >= target) { // possible to be the answer, so don't exclude
                r = mid;
            } else { // cannot be the answer, safely exclude mid.
                l = mid + 1;
            }
        }
        // post-processing:
        // 1. if an answer is found, return left -> return right
        // 2. if not found, return any is ok, outside will check
        if (prefixSum[l + 1] - prefixSum[start] >= target) {
            return l;
        }
        return r;
    }

    // Solution 2: two pointer
    // when move left, we don't need to loop right from `new left`
    // instead, continue to move right !!
    // therefore, each element will be pointed to at most twice, so O(n)
    public int minSubArrayLen(int target, int[] nums) {
        int right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for (int left = 0; left < nums.length; left++) {
            while (right < nums.length && sum < target) {
                sum += nums[right];
                right++;
            }
            if (sum >= target) {
                minLength = Math.min(minLength, right - left);
            }
            sum -= nums[left];
        }
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }
}
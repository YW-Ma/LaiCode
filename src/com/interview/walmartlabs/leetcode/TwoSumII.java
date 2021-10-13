package com.interview.walmartlabs.leetcode;

public class TwoSumII {
    // now input array is sorted
    // 那么直接用两个指针对象而行就行了， 开销是O(N)
    public int[] twoSum(int[] nums, int target) {
        // sorted non-decreasing order
        // may have duplicate?
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            long sum = nums[left] + nums[right]; // 注意，这一行有溢出风险。最好用long。
            boolean withinBoundary = sum > Integer.MIN_VALUE && sum < Integer.MAX_VALUE;
                if (withinBoundary && sum == target) {
                    return new int[]{left + 1, right + 1};
                }
                if (withinBoundary && sum < target || sum < Integer.MIN_VALUE) {
                    // smaller, move left border
                    left++;
                    continue;
                }
                if (withinBoundary && sum > target || sum > Integer.MAX_VALUE) {
                    // larger, move right border
                    right--;
                }
            }
        return null;
    }
}

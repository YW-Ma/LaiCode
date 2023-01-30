package com.jiuzhang;



public class BinarySearchTemplate {
    /**
     * Find any position of a target number in a sorted array. Return -1 if target does not exist.
     *
     * Example 1:
     * Input: nums = [1,2,2,4,5,5], target = 2
     * Output: 1 or 2
     *
     * Example 2:
     * Input: nums = [1,2,2,4,5,5], target = 6
     * Output: -1
     * https://www.jiuzhang.com/solution/classical-binary-search/
     * */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        // check if left or right is the target
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1; // cannot find
    }

    /**
     * 套用九章模版 -- 可无脑解出所有binary search类问题而不需担心死循环
     * https://www.jiuzhang.com/solution/classical-binary-search/
     * 1. 循环条件为 left + 1 > right
     * 2. 三个分支，都不越过mid
     * 3. 必然后处理，后处理时先看left还是先看right要根据题目要求来。
     *
     * https://www.jiuzhang.com/problem/find-first-and-last-position-of-element-in-sorted-array/
     * 在排序数组中查找元素的第一个和最后一个位置 · Find First and Last Position of Element in Sorted Array
     *
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * */
    public int[] searchRange(int[] nums, int target) { // target element.
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        // using the formate to do two binary search.
        int lowerBound = findLowerBound(nums, target);
        int upperBound = findUpperBound(nums, target);
        return new int[]{lowerBound, upperBound};
    }

    private int findLowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) { // to avoid infinite loop
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }

    private int findUpperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) { // to avoid infinite loop
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid; // change 1
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // change order
        if (nums[right] == target) {
            return right;
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearchTemplate binarySearchTemplate = new BinarySearchTemplate();
        int[] res = binarySearchTemplate.searchRange(new int[]{5,7,7,8,8,10}, 8);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}


package com.interview.walmartlabs.leetcode;

public class SearchInRotatedArray_Naive {
    // 先O(logn)找到pivot（ 所有val减去第一个val，这样pivot开始是负的，之前是正的），即beginning of second part --> 注意我的算法仅仅对真的发生了rotate的人管用。
        // find_rotate_index 有两个找法： 我这样找 first negative element,  或者不相减，而是找 pivot使得nums[pivot] < nums[pivot + 1] 然后返回pivot + 1
    // 再O(logn)找value --> 这里我做得比较傻，真的用pseudo的int在整个数组扫描了。实际上只用在某一个part里，这样binary search更容易。
    public int search(int[] nums, int target) {
        // corner case:
        if (nums.length == 1) { // to skip all one-element-nums.
            return (nums[0] == target) ? 0 : -1;
        }
        
        
        // 1. find out where is the beginning of the "second" part, but if the origianl one is not rotated. this will give the last element.
        int l = 0;
        int r = nums.length - 1;
        if (nums[0] > nums[nums.length - 1]) {
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] - nums[0] >= 0) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
        }
        // now, l = m = r = beginning of the second part.
        
        
        // 2. do binary search -> but only one element, may have problem, like: find 1 in [1], at the beginning, the right = (-1 - 0 + 1) % 1 = 0 == left. so will skip while loop.
        int left = toNew(l, nums, l);
        int right = toNew(l - 1, nums, l);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[toOld(mid, nums, l)] == target) {
                return toOld(mid, nums, l);
            } else if (nums[toOld(mid, nums, l)] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    private int toNew(int idx, int[] nums, int l) {
        return (idx - l + nums.length) % nums.length;
    }
    private int toOld(int idx, int[] nums, int l) {
        return (idx + l + nums.length) % nums.length;
    }
}

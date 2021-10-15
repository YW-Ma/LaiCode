package com.interview.walmartlabs.leetcode;

public class SearchInRotatedArray_Naive_Updated {
    // 三处更新：
    // 1. find_rotate_index 有两个找法：
    //    上次那样找 first negative element,
    //    或者不相减，而是找 pivot使得nums[pivot] < nums[pivot + 1] 然后返回pivot + 1 （这次的写法）
    // 2. 不需要在整个数组binary search结果了，只需要在可能包括target的那一侧。 （加速了，而且显得聪明一些
    //    -->  但是也要注意，如果实际上是没有rotated过，pivot = 0，此时应该怎么分呢？ --> 可以单独列一个情况
    // 3. 把两个过程拆分成两个子函数，方便复用（找pivot + 普通binary search）
    
    // 发现大坑：如果没有rotate，或者target就在rotate上，可能会有问题？
    
    public int search(int[] nums, int target) {
        // corner cases
        int size = nums.length;
        if (size == 1) {
            return target == nums[0] ? 0 : -1;
        }
        
        // find rotate index
        int pivot = findRotateIndex(nums);
        
        // search in the possible part
        // not rotated
        if (pivot == 0) {
            return binarySearch(nums, target, 0, size - 1);
        }
        // rotated
        if (nums[0] > target) {
            return binarySearch(nums, target, pivot, size - 1);
        } else {
            return binarySearch(nums, target, 0, pivot - 1);
        }
    }
    
    private int findRotateIndex(int[] nums) {
        // if not rotated:
        if (nums[0] < nums[nums.length - 1]) {
            return 0;
        }
        
        // if rotated:
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) { // left-biased mid, so mid + 1 must exists.
                return mid + 1;
            } else if (nums[mid] >= nums[0]) { // in the left size (notice, 0 itself is in left size too)
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0; // 这里返回什么呢？ 如果直接弹出，意味着一开始 left > right. 只有可能size = 0 （让right变-1） 那么返回0比较合理。
    }
    
    private int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // cannot find
    }
}

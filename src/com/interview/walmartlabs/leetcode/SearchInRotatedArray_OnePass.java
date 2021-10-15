package com.interview.walmartlabs.leetcode;

public class SearchInRotatedArray_OnePass {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // 注意：run out all search space.
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[left]) { // 先确定自己是123，还是456 这两组情况  注意，== 也属于情况123里面。
                // 1 or 23
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 45 or 6
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
        }
        return -1;
    }
    // 参考[我的笔记](https://docs.google.com/document/d/1SgzldFSkjcQFC4rrjPVkrgN7bKHC5BN-TU0SJoK5DNk/edit?usp=sharing)
    //      先分割成123、456两组情况，再分别看是1还是23，是45还是6.
}

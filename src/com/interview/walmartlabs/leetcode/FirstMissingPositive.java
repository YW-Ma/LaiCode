package com.interview.walmartlabs.leetcode;

import java.util.*;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        // in-place solution
        // phase 1: corner case:
        boolean hasOne = false;
        for (int num : nums) {
            if (num == 1) {
                hasOne = true;
                break;
            }
        }
        if (!hasOne) {
            return 1;
        }
        
        // phase 2: pre-processing
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) { // only can have 1 to n integers.
                nums[i] = 1;
            }
        }
        
        // phase 3: implement "hashSet" using positive or negative
        for (int num : nums) {
            num = Math.abs(num);
            nums[num - 1] = -Math.abs(nums[num - 1]); // set to negative, to mark as read.
        }
        
        // phase 4: check first missing positive
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] > 0) {
                return i;
            }
        }
        return nums.length + 1;
    }
    
    public int firstMissingPositive_HashSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return nums.length + 1;
    }
}

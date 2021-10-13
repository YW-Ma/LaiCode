package com.interview.walmartlabs.leetcode;

import java.util.HashMap;

public class TwoSum {
    // 记得two sum要assume 不会有overflow。
    
    // two sum: int array, and a target.
    // naive: HashMap, two pass (first build, then use) --> 但是use的时候要保证两个不同，不然tar = 2， 就会找到两个1组成结果
    // better: HashMap, one pass (this one, only compare with the node before it, to make sure no duplicate results)
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> arr = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (arr.containsKey(target - nums[i])) { // check each one
                return new int[]{arr.get(target - nums[i]), i};
            }
            arr.put(nums[i], i);        // put --> 先check，才put
        }
        return null;
    }
    // TC: O(# of elements) * [O(lookup time) + O(put time)] = O(n) * O(1) = O(n)
    
    
    // two pass solution: notice that: early return to avoid duplicate (1,2) & (2,1),  check i to avoid using one element twice (1,1) -> 2
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> arr = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            arr.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (arr.containsKey(target - nums[i]) && arr.get(target - nums[i]) != i) {
                return new int[]{i, arr.get(target - nums[i])};
            }
        }
        return null;
    }
}

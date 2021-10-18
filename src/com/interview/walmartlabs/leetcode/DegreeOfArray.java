package com.interview.walmartlabs.leetcode;

import java.util.*;

public class DegreeOfArray {
    public int findShortestSubArray(int[] nums) {
        // input: nonempty,nonnegative int array --> nums
        // the maximum freq of any one of its elements (we called it degree)
        
        // output: the smallest possible length of a subarray of nums, that has the same degree as nums.
        
        // my solution:
        // 1. count freq, save to <val, freq-start-end array> hashMap, record the val with highest freq
        // 2. return the subarray using start, end
        // 也可以把freq, start, end 放到三个分别的Hashmap里面，反正key都是val。
        
        Map<Integer, Integer> count = new HashMap<>(); // val -> cnt
        Map<Integer, Integer> start = new HashMap<>(); // val -> start
        Map<Integer, Integer> end = new HashMap<>(); // val -> end
        
        
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            count.put(val, count.getOrDefault(val, 0) + 1);
            start.put(val, start.getOrDefault(val, i)); // inherit the previous start, or start a new
            end.put(val, i); // new end
        }
        
        int res = nums.length;
        int degree = Collections.max(count.values());
        for (int val : count.keySet()) {
            if (count.get(val) == degree) { // 可能有多个count相同的，他们对应的start、end中取最小的
                res = Math.min(res, end.get(val) + 1 - start.get(val));
            }
        }
        return res;
    }
}

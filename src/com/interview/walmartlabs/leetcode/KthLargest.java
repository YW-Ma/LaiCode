package com.interview.walmartlabs.leetcode;

import java.util.PriorityQueue;

public class KthLargest {
    public int findKthLargest_minHeap(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap
        // loop through
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        return pq.peek();
        // TC: O(n*logk)
    }
    
    
}

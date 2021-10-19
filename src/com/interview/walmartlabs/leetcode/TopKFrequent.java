package com.interview.walmartlabs.leetcode;

import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequent {
    // our algorithm's time complexity must be better than O(n log n), where n is the array's size.
    // 这个题是套皮 k-th largest
    // 只是需要先count一次frequent罢了。 -->  practice 一下两个方案 O(nlogk) 的heap 和 O(n)的quick select
    
    // count的过程是使用了HashMap
    HashMap<Integer, Integer> count; // val -> freq
    
    // func:
    public int[] topKFrequent(int[] nums, int k) {
        // O(1) corner case
        if (nums.length == k) {
            return nums;
        }
        // O(N) count
        count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        // O(N * logk) minHeap, storing index
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> count.get(a) - count.get(b));
        for (int n : count.keySet()) {
            minHeap.add(n);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // O(k * logk) build output array
        int[] top = new int[k];
        for (int i = 0; i < k; i++) {
            top[i] = minHeap.poll();
        }
        return top;
    }
}

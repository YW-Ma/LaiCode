package com.jiuzhang.单调栈.单调队列;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMaximum {
    // https://leetcode.com/problems/sliding-window-maximum/
    /*
    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [3,3,5,5,6,7]
    * */
    
    /*
     * [3 -1
     * [3 -1 -3
     * [5
     * [5 3
     * [6
     * [7
     * */
    
    // 如果是递增队列 --> 等这个大的被干出去了，次大的却没能记住
    // 所以应该是递减队列
    // 每次拿到队首的就行了
    
    class Cell {
        int id;
        int val;
        
        public Cell(int id, int val) {
            this.id = id;
            this.val = val;
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Cell> q = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        // corner cases
        if (nums == null || nums.length == 0 || k == 0) {
            return null;
        }
        // initial status
        for (int i = 0; i < k; i++) {
            int n = nums[i];
            while (!q.isEmpty() && q.peekLast().val <= n) {
                q.pollLast();
            }
            q.offerLast(new Cell(i, n));
        }
        res.add(q.peekFirst().val);
        
        // iteration
        for (int i = k; i < nums.length; i++) {
            // delete outdated entry
            while (!q.isEmpty() && q.peekFirst().id <= i - k) {
                q.pollFirst();
            }
            // maintain the descending order
            while (!q.isEmpty() && q.peekLast().val <= nums[i]) {
                q.pollLast();
            }
            q.offerLast(new Cell(i, nums[i]));
            res.add(q.peekFirst().val);
        }
        
        int[] results = new int[res.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = res.get(i);
        }
        return results;
    }
}

package com.study.algorithms.class04_queue_and_stack.LargestRectangleArea;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleArea_UsingFormat {
    // 单调栈还有另一个模板
    public void format() {
        int[] nums = new int[10];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peekLast() > nums[i]) {
                stack.pollLast();
            }
            stack.push(nums[i]);
        }
    }
    
    // 使用这个模板来写的话：
    class Entry {
        int val;
        int index;
        public Entry(int val, int index) {
            this.val = val;
            this.index = index;
        }
        public String toString() {
            return "{ " + val + " }";
        }
    }
    public int largestRectangleArea(int[] heights) {
        int globalMax = 0;
        Deque<Entry> stack = new ArrayDeque<>();
        // 三个分支：空、top更大、new更大，其中top更大和空可以合并，new更大在处理完以后也可以走他们的分支。
        
        
        for (int i = 0; i <= heights.length; i++) {
            // prepare the "nums[i]'
            Entry newEntry = null;
            if (i == heights.length) {
                newEntry = new Entry(0, heights.length);
            } else {
                newEntry = new Entry(heights[i], i);
            }
            // 模板   ---   part 1
            while (!stack.isEmpty() && stack.peekLast().val >= newEntry.val) {
                Entry curEntry = stack.pollLast(); // 一般会在part 1模板，即poll的时候做一些处理
                int leftBound = stack.isEmpty() ? -1 : stack.peekLast().index;
                int rightBound = newEntry.index;
                int area = (rightBound - leftBound - 1) * curEntry.val;
                globalMax = Math.max(globalMax, area);
            }
            // 模板   ---   part 2
            stack.offerLast(newEntry);
        }
        return globalMax;
    }
}

package com.study.algorithms.class04_queue_and_stack.LargestRectangleArea;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleArea {
    // stack:
    //   linear scan回头看
    //   可以简化为每次值看第一个， 所以用stack
    // 出栈时机：
    //   新的比栈顶要小，那么出栈。
    //   宽度是新的下标  -  出栈后栈顶下标
    //   注意如果出栈后空了，那么默认是 -1 作为左侧下标 --> 防止NPE，可能需要关注一下
    //  -- 在纸上绘制一下，用虚线描出出栈的bar，可以清晰理解这么操作的意义 （确实新栈顶就是左侧边界）
    // 入栈时机：比栈顶大于等于
    
    // 为了保证能出栈，还要制作一个高度为0的结尾，下标为右边界+1.
    
    // 实际上，这个栈就是"单调栈"， 里面是单调增的。 出现小的就会计算矩形面积
    
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
        
        int i = 0;
        while (i <= heights.length) {   // 不适合用for循环，因为如果走第三个情况的分支的话，为了简约，就直接i不++了。 如果用for循环，则还要套一个while在第三个分支。
            // 构造virtual 的 last element
            Entry newEntry = null;
            if (i == heights.length) {
                newEntry = new Entry(0, heights.length);
            } else {
                newEntry = new Entry(heights[i], i);
            }
            
            // 三个情况：stack为空，stack顶比我小，stack顶比我大
            if (stack.isEmpty()) {
                stack.offerLast(newEntry);
                i++;
            } else if (newEntry.val >= stack.peekLast().val) {
                stack.offerLast(newEntry);
                i++;
            } else {
                Entry topEntry = stack.pollLast();
                int height = topEntry.val;
                int leftBound = stack.isEmpty() ? -1 : stack.peekLast().index;
                int rightBound = newEntry.index;
                
                int area = height * (rightBound - leftBound - 1);
                globalMax = Math.max(globalMax, area);
            }
        }
        return globalMax;
    }
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
    
    
    
}

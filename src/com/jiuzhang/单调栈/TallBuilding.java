package com.jiuzhang.单调栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class TallBuilding {
    public int[] tallBuilding(int[] arr) {
        // 向左看能看到的楼一定是单调减的高度
        // 一个元素要进来的时候，检查一下这个单调减的stack有多少个元素，那就是向左能看到多少个楼
        int[] results = new int[arr.length];
        Arrays.fill(results, 1);
        countBuildings(arr, results, 0, arr.length, 1);
        // 向右看的话是一个镜像的过程，最好写到一个函数里面
        countBuildings(arr, results, arr.length - 1, -1, -1);
        
        return results;
    }
    
    private void countBuildings(int[] arr, int[] results, int start, int end, int delta) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = start; i != end; i += delta) {
            results[i] += stack.size();
            int height = arr[i];
            while (!stack.isEmpty() && stack.peekLast() <= height) {
                stack.pollLast();
            }
            stack.offerLast(height);
        }
    }
}

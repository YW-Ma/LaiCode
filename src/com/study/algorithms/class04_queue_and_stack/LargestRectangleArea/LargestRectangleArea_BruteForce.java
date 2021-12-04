package com.study.algorithms.class04_queue_and_stack.LargestRectangleArea;

public class LargestRectangleArea_BruteForce {
    public int largestRectangleArea(int[] heights) {
        int globalMax = 0;
        for (int i = 0; i < heights.length; i++) {
            // for each height, expand to find the maximum width.
            int height = heights[i];
            int l = i; // l 和 r 最后会指向边界的外侧，即 l+1 和 r-1 正好是边界。
            int r = i;
            while (l >= 0 && heights[l] >= height) { // 为了能让 l+1, r-1指向 0 和 length-1, 就一定要能让 l==0继续进入循环。
                l--;
            }
            while (r <= heights.length - 1 && heights[r] >= height) {
                r++;
            }
            int width = (r - 1) - (l + 1) + 1;  // 注意l和r此时都是边界外
            
            globalMax = Math.max(globalMax, height * width);
        }
        return globalMax;
    }
}

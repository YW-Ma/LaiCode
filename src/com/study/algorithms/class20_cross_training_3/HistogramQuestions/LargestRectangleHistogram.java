package com.study.algorithms.class20_cross_training_3.HistogramQuestions;

import java.util.*;

public class LargestRectangleHistogram {
    // Histogram题目
    // { 2, 1, 3, 3, 4 },
    // the largest rectangle area is 3 * 3 = 9
    // (starting from index 2 and ending at index 4)
    public int largest(int[] array) {
        // Assumption: array holds non-negative elements. not null, not empty
        int result = 0;
        // Note that the stack contains the "index",
        // not the "value" of the array

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= array.length; i++) {
            int cur = i == array.length ? 0 : array[i];
            while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) { // “悬崖” 为前面提供right border
                int height = array[stack.pollFirst()];
                // determine the left border of the largest rectangle with height array[i]
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1; // 左边界还是靠继承。
                // determine the right border of the largest rectangle with height of the popped element.
                int right = i;

                result = Math.max(result, height * (right - left));
            }
            stack.offerFirst(i); // 把悬崖加进去。 和所有比它矮的在一起，等一个更矮的悬崖。
        }
        return result;
    }
}

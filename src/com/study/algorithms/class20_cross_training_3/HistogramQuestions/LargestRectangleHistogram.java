package com.study.algorithms.class20_cross_training_3.HistogramQuestions;

import java.util.*;

public class LargestRectangleHistogram {
    // 参考资料：

    // Histogram题目
    // { 2, 1, 3, 3, 4 },
    // the largest rectangle area is 3 * 3 = 9
    // (starting from index 2 and ending at index 4)
    public int largest2(int[] array) {
        // Assumption: array holds non-negative elements. not null, not empty
        int result = 0;
        // Note that the stack contains the "index",
        // not the "value" of the array

        // stack定义：存储index，他们都还没遇到自己的右悬崖。他们的左悬崖一定是自己前一个元素。（有连续重复高度的情况下不是，但是不影响面积。）
        // stack的使用：每次拉一个新元素，看它能否作为stack元素的右悬崖（是否小于peekFirst），
        //      1. 不能：那么追加到里面
        //      2. 可以：那么把每个大于等于我的都弹出来，然后右边界设置成我，左边界设置成它的前一个元素。 如果发现弹出来的元素是stack顶部，那么left是0 （证明它要么是第一个，要么比第一个还矮一些，所以left是0）
        //     和我们的简单版做法一样，left是inclusive，right是exclusive的。

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= array.length; i++) {
            int cur = i == array.length ? 0 : array[i]; // 假设最右侧还有一个高速为0的 （i == array.length)，这样可以让 [1,2,3,4] 这种情况，获得一个“悬崖”
            while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) { // “悬崖” 为前面提供right border
                int height = array[stack.pollFirst()];
                // determine the left border of the largest rectangle with height array[i]
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1; // stack里面的东西一定是递增的，也就是每个元素的左侧元素，都是比自己低的那个的index。
                // determine the right border of the largest rectangle with height of the popped element.
                int right = i;

                result = Math.max(result, height * (right - left));
            }
            stack.offerFirst(i); // 把悬崖加进去。 和所有比它矮的在一起，等一个更矮的悬崖。
        }
        return result;
    }
}

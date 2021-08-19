package com.study.algorithms.class20_cross_training_3.HistogramQuestions;

public class LargestRectangleHistogramBasic {
    //  0 1 2 3 4
    // [2,1,3,4,1]
    //  | |                 1-0 * 2 = 2
    //    | |               2-1 * 1 = 1
    //      |    |          4-2 * 3 = 6
    //  |          | <-- 极端情况但是发现依然符合 5-0 * 1 = 5  只是注意不能把rb拿来进行下标访问。

    // let left be inclusive
    // let right be exclusive


    public int largest(int[] array) {
        int maximum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {

            // 1 get left border
            int lb = i;
            while (lb >= 0 && array[lb] >= array[i]) {
                lb--;
            }
                    // 走出来的时候，array[lb] < array[i] 即 lb 在规定边界的左侧， 需要+1 才能符合模型中的边界
            lb++;


            // 2 get right border
            int rb = i;
            while (rb <= array.length - 1 && array[rb] >= array[i]) {
                rb++;
            }
                    // 走出来的时候，array[rb] < array[i] 及 rb 是边界的右侧, 符合模型中的边界
                    // 但是，如果是走rb > array.length - 1出来的，即rb == array.length，还符合吗？ （还符合）

            // 3 update the maximum rectangle
            int size = (rb - lb) * array[i];
            maximum = Math.max(maximum, size);
        }
        return maximum;
    }
}

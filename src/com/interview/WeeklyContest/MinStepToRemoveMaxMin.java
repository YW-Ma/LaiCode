package com.interview.WeeklyContest;

public class MinStepToRemoveMaxMin {
    // 给你一个数组 nums ，数组由若干 互不相同 的整数组成。
    // 一次 删除 操作定义为从数组的 前面 移除一个元素或从数组的 后面 移除一个元素。
    // 返回将数组中最小值和最大值 都 移除需要的最小删除次数。

    // 即只能通过删除来处理元素的话，怎么在最短时间干掉最小、最大值。

    // 先找到最小最大
    // 每次从最靠近的那边删除一个离边最近的

    public int minimumDeletions(int[] nums) {
        // find the index
        int minIndex = 0;
        int maxIndex = 0;
        int min = nums[0];
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                maxIndex = i;
                max = nums[i];
            }
            if (nums[i] < min) {
                minIndex = i;
                min = nums[i];
            }
        }
        int i = minIndex;
        int j = maxIndex;
        if (i > j) { // 保证 i < j
            int tmp = i;
            i = j;
            j = tmp;
        }

        // there are total three solutions:
        //  1. j+1
        //  2. len-i
        //  3. i+1  +  len-j
        return Math.min(Math.min(j + 1, nums.length - i), i + 1 + nums.length - j);
    }


}

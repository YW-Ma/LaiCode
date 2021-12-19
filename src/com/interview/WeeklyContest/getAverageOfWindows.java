package com.interview.WeeklyContest;

import java.util.Arrays;

public class getAverageOfWindows {
    // 获得半径为k的子数组的平均值
    // 给定nums和半径k
    // 对下标i为中心，半径为k, [i-k, i+k] 范围内的元素的平均值
    // 如果i前或后不足k个元素，则平均值取-1

    // 输入：nums = [ 7, 4, 3, 9, 1, 8, 5, 2, 6],  k = 3
    // 输出：       [-1,-1,-1, 5, 4, 4,-1,-1,-1]
    //            index       k   end-k-1

    // 如何快速计算一段区域内的sum --> prefix sum
    // 但是sum要向面试官确认，会不会sum溢出, 最好不要用int(32bit) 而是改用 long(64bit)
    public int[] getAverages(int[] nums, int k) {
        // corner case:
        if (nums == null || nums.length == 0) {
            return null;
        }

        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        long[] prefixSum = new long[nums.length + 1]; // i-th element --> sum of first i elements.
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        for (int i = k; i <= nums.length - k - 1; i++) {
            res[i] = (int)((prefixSum[i + 1 + k] - prefixSum[i - k]) / (2 * k + 1)); // 注意(int)后方要套一个括号，否则在除号前就long->int溢出了
        }
        return res;
    }
}

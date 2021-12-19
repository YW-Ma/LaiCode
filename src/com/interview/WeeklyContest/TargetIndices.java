package com.interview.WeeklyContest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TargetIndices {
    // 给定下标从0开始的 int array nums,  以及一个target 目标元素
    // 目标下标是一个满足 nums[i] == target  的下标i
    // 将nums按照 non-descending 排序，返回nums中目标下标组成的列表

    // [1,2,5,2,3]    target=2
    //  0 1 2 3 4
    // 不是这个题：nums[1], nums[3] == 2  --->   return 1 and 3
    // 这个题很简单，只要求返回排序后的nums的下标，即返回 1,2即可（见下文）

    // 思路：
    // 先排序一下：
    //  [1,2,2,3,5]
    //   0 1 3 4 5   -->  不是这个题
    //   发现找到target后，获得对应的原始index即可。

    // 这个题排序，然后返回target范围即可。
    // 可以优化： 二分查找找到第一个出现的、在排序的时候跟踪总数，  这样除了排序只需要O(logn)

    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int firstOccurrence = getFirstOccurrence(nums, target);
        if (firstOccurrence == -1) {
            return res;
        }
        // sort的时候其实可以把count拿到
        for (int i = firstOccurrence; i < nums.length && nums[i] == target; i++) {
            res.add(i);
        }
        return res;
    }

    public int getFirstOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        // [1,2,2,3,5]
        //    l
        //    r
        //    m
        //
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }
}

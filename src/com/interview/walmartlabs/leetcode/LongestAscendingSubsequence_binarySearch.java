package com.interview.walmartlabs.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LongestAscendingSubsequence_binarySearch {
    // 思路建立在另一个for-for-loop上
    // 思路依然是维护一个array，第i个元素是最可能作为第i个元素的值。 第i个元素存在，代表subseq至少会有这么长。 但是第i个元素不一定是答案里面的那个
    // 比如 [3,5,6,10] 遇到了最后一个元素1，会变成 [1,5,6,10] 只是size合法，但是具体的值不对。
    public int lengthOfLIS(int[] nums) {
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (array.isEmpty() || nums[i] > array.get(array.size() - 1)) {
                array.add(nums[i]);
            } else {
                int pos = binarySearch(array, nums[i]); // find the first larger.
                array.set(pos, nums[i]);
            }
        }
        return array.size();
    }
    
    private int binarySearch(List<Integer> array, int target) {
        // [1,3,5,7] find 4 get 5
        //      l
        //        r
        //      m          safely move l over m if m < target,   but can only move right to m if m >= target,  so, when l == r then quit
        int l = 0;
        int r = array.size() - 1;
        // find the first element equal or larger than num
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}

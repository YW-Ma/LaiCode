package com.interview.walmartlabs.leetcode;

import java.util.ArrayList;
import java.util.List;

// ForForLoop:
// 动态维护一个数组seq，里面存储最可能是longest subseq的部分
//  seq[i] 指的是“可以做第i位的元素中最小的那个”，seq[i] 不为0代表当前最长的seq存在第i位
//  对于每个新值：
//      如果比最后一个值还要大，就append，让数组变长
//      如果小于最后一个元素，寻找自己的可能的位置i，替换seq[i]   → 最后结果可能不需要替换，但是替换了不会导致长度信息的错误。
public class LongestAscendingSubsequence_forforloop {
    public int lengthOfLIS(int[] nums) {
        // Longest Increasing Subsequence
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (array.isEmpty() || nums[i] > array.get(array.size() - 1)) {
                array.add(nums[i]);
            } else {  // find the proper position
                int pos = 0;
                while (pos < array.size() && array.get(pos) < nums[i]) {
                    pos++; // skip
                }
                array.set(pos, nums[i]);
            }
        }
        
        return array.size();
    }


//    public int lengthOfLIS_naive(int[] nums) {
//        // Longest Increasing Subsequence
//        // nums [0,1,0,3,2,3]
//        // dp    1 1 1 1 1 1        <- length of the LIS that ends with element i. (init)
//        // dp    1 1 1 1 1 1        <- length of the LIS that ends with element i.
//
//        int[] dp = new int[nums.length];
//        // 初始化为1，因为自己和自己是一个长度为1的序列
//        Arrays.fill(dp, 1);
//
//        // forforloop开销O(n^2)
//        for (int i = 1; i < nums.length; i++) {
//            // 每次撸一遍之前的，继承比我小的值里面，dp值最大的的那个（继承自最有优势的前继）
//            for (int j = 0; j < i; j++) {
//                if (nums[j] < nums[i]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//        }
//
//        // 再撸一遍获得结果
//        int ans = 0;
//        for (int n : dp) {
//            ans = Math.max(ans, n);
//        }
//        return ans;
//    }
}

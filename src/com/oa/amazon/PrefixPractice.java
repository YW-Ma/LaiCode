package com.oa.amazon;

import java.util.*;

// https://leetcode.com/problems/subarray-sum-equals-k/solution/
public class PrefixPractice {
    
//- 如果关心具体的解，那么只能用枚举所有subarray的方法。
//  - 但是因为使用了prefixSum，所以开销是o(n^2) 而不是O(n^3) 我们只需要知道两端就可以知道summary，而不需要把中间加一遍。
//  - 注意，如果去除重复可以用HashSet存储结果（要放入ArrayList）
//
// 滑动窗口初始位置：  prefixSum有个前0元素的和，让l停在那个位置。这样可以获得 包括了第一个元素 的和。
//
//   1 1 2 2 3 4  5     k = 4  <--
// 0 1 2 4 6 9 13 18           <--
// l
//   r

// 滑动窗口扫描方法
//
//   1 1 2 2 3 4  5     k = 4  <--
// 0 1 2 4 6 9 13 18           <--
// l
//   r -->    ...  r    定住l，扫描所有可能的r。 总体开销是等差数列O(n^2)级别
    
    public static void main(String[] args) {
        subarraySum(new int[]{1,2,3,4,5,6,7,8,9}, 4);
        
        // 注意，HashSet是可以去重的（前提是提供的是List对象，而不是int array）
        Set<List<Integer>> res = new HashSet<>();
        res.add(Arrays.asList(1,2,3));
        res.add(Arrays.asList(1,2,3));
        res.add(Arrays.asList(1,2,3));
        System.out.println(res);
    }
    
    public static int subarraySum(int[] nums, int k) { // O(n^2)
        Set<List<Integer>> res = new HashSet<>();
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1]; // nums from 0, sum from 1
        }
        
        for (int start = 0; start <= nums.length; start++) {  // 滑动窗口扫描方法
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k) {
                    count++;
                    res.add(Arrays.asList(start, end));  // 注意API的运用
                }
            }
        }
        
        System.out.println(res);
        return count;
    }
    
    public static int subarraySumHashMap(int[] nums, int k) { // O(n)
        // k == 0 也是可以的，因为nums可正可负。
        Map<Integer, Integer> prevPrefixSum = new HashMap<>(); // map: prefixSum to count of prefixSum value in the previous iterations.
        prevPrefixSum.put(0, 1); // prefix前缀和为0的个数是1，这样后面如果刚好有 value == k 的元素，counter就可以加一
        
        
        int counter = 0;
        int prefix = 0;
        for (int num : nums) {
            prefix += num;  // 先更新counter，再put新记录。 防止 k == 0 时，把自己找出来（搜索的时候应该只在之前的范围里搜索）
            counter += prevPrefixSum.getOrDefault(prefix - k, 0); // 更新counter变量，添加前缀和为 prefix - k 的个数,  k是两个prefix的中间的value，所以应该是prefix - k。
            prevPrefixSum.put(prefix, prevPrefixSum.getOrDefault(prefix, 0) + 1); // 更新前缀和
        }
        return counter;
    }
}

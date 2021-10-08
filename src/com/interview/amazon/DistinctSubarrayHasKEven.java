package com.interview.amazon;

// distinct and non-distinct solution is different:
// 如果需要distinct，我们只能用ArrayList存储结果用Set保证distinct，这个过程需要我们把可能的结果具体地产生出来
// 如果不需要distinct，我们只需要用滑动窗口，里面的subarray的数量是 right - left + 1 这么多个（但是不能知道具体的内容）

public class DistinctSubarrayHasKEven {
    // distinct subarray是，比如说[1, 2, 3, 1, 2], 前面的[1, 2]和后面的[1, 2] 就不算distinct，所以count的时候只能count一遍这样
    // 第二题是给一个int list，和一个int k，要求找到所有最多含有k个odd number的subarray的个数；两道题都是brute force就能pass所有的test
    public int CountSubarray(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int[] preSum = new int[arr.length + 1]; // (count of odd numbers) in the (first i numbers).
        preSum[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + (arr[i - 1] % 2 == 1 ? 1 : 0);
        }
        
        int counter = 0;
        int left = 0; // count of odd nums in (left, right], left, right is the first x numbers in the array.
        int right = 1;
        
        while (right <= arr.length) {
            int diff = preSum[right] - preSum[left];
            if (diff <= k) {
                counter++;
            } else {
            
            }
        }
        return 0;
    }
}

package com.oa.arista;

public class MissingNumber {
//    MissingNumber & Binary Search
//    https://leetcode.com/problems/missing-element-in-sorted-array/
//    1. left, right 是否越过 mid 取决于题意mid是否可能是答案：
    //    找smallest larger
    //    找largest smaller
    //    找first occurance
    //    找last occurance
    //    找target
//    2. while循环用哪个？ while(left < right) or while (left < right - 1)
    //    mid is left biased, since we use mid = left + (right - left) / 2
    //    如果出现 mid = left, mid + 1 = right， 那么 left < right会死循环
    //    出现死循环的情况是：left 不越过mid。 right 无论是否越过mid 都不影响
//    因此见下表：
//    smallest in larger：	mid比target小的时候不可能是答案，left可以越过，	while不变
//    largest in smaller：	mid比target小的时候可能是答案，所以left不能越过，	while要变 +
//    first occurance：	    mid比target小的时候不可能是答案，left超过，		while不变
//    last occurance：	    left分支可能是答案，left不可越过mid，		    while要变 +
//    target：		        越过，								        while不变
//
//    Rule of Thumb: 一旦发现left不能越过了，那么请right也不要越过。不然可能会有奇怪的bug。（但是反过来没事，可以right不越过，left越过）
//    另外：有些题目，比如missing number题，可以通过找largest smaller或smallest larger解决，所以有两种方案。
//    答案在哪里呢：
    //    对于while不变的情况，最后会出现 left = right = mid,
    //    对于while会变的情况，要分left和right考虑，因为他们可能不同。
    
    
    // 使用smallest larger的binary search
    public int missingElement1(int[] nums, int k) {
        // pre-processing
        int[] missing = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            missing[i] = nums[i] - nums[0] - i; // 归0化，然后减去对应的第几个数字。 就可以知道跳跃了几个数字。
        }
        if (k > missing[missing.length - 1]) {
            return k - missing[missing.length - 1] + nums[nums.length - 1];
        }
        //
        // 0 2 3 5      k = 3
        //   l
        //     r
        //   m   --> will find the largest smaller 3
        
        // binary search - largest smaller
        int left = 0;
        int right = missing.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (missing[mid] < k) {
                left = mid;
            } else {
                right = mid; // right不可能是答案所以可以删掉，但是测试的时候会出错。不知道为啥。
            }
        }
        return nums[left] + (k - missing[left]);
    }
    
    // 使用largest smaller的binary search
    public int missingElement2(int[] nums, int k) {
        // pre-processing
        int[] missing = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            missing[i] = nums[i] - nums[0] - i; // 归0化，然后减去对应的第几个数字。 就可以知道跳跃了几个数字。
        }
        if (k > missing[missing.length - 1]) {
            return k - missing[missing.length - 1] + nums[nums.length - 1];
        }
        
        // 0 2 3 5      k = 3
        //     l
        //     r
        //     m
        // find left = right index such that
        // missing(left - 1) < k <= missing(left)
        
        // binary search
        int left = 0;
        int right = missing.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (missing[mid] < k) {
                left = mid + 1;  // 注意，当right = mid， left = mid + 1的时候。不需要修改循环条件为 -1， 因为此时不会出现那种死循环。
            } else {
                right = mid; // right这边可能是ans，所以不动。 似乎在找 first larger.
            }
        }
        return nums[right - 1] + (k - missing[right - 1]); //left == right了其实
    }
}

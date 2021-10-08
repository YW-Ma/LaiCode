package com.interview.misc;

/*
* A subarray is a contiguous portion of an array.
* Given an array of int, determine the number of distinct subarrays that can be formed having
* at most a given number of odd elements.
*
* Two subarrays are distinct if they differ at even one position in their contents.
*
* Example:
* num = [1,2,3,4]
* k = 1      -->    having no more than 1 odd elements (odds are 1,3)
*
* [1] [2] [3] [4] [12] [14] [23] [24] [124] [234]
*
* */

import java.util.*;

public class EvenSubarray {
    public static void main(String[] args) {
        evenSubarray(new int[]{1,2,3,4}, 1);
    }


    public static int evenSubarray(int[] numbers, int k) { // 不需要考虑有没有duplicate nums，HashSet自动去重。
        // pre-process numbers to arraylist
        List<Integer> nums = new ArrayList<Integer>();
        for (int num : numbers) {
            nums.add(num);
        }
        // HashSet
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        List<Integer> cur = new ArrayList<>();

        // preSum:  oddPerSum[i] --> # of odd nums in first (i) elements.
        int[] oddPreSum = new int[numbers.length + 1]; // 前缀要+1size，这样方便使用（这样 preSum[j + 1] - preSum[i] 就是i和j之间的odd数量
        //   -->  其实根本没必要用preSum，每个i重新开一个counter记录从i开始的subarray的odd就行了。
        oddPreSum[0] = 0;
        for (int i = 1; i <= numbers.length; i++) {
            if (numbers[i - 1] % 2 != 0) {
                oddPreSum[i] = oddPreSum[i - 1] + 1;
            } else {
                oddPreSum[i] = oddPreSum[i - 1];
            }
        }

        // filters all sub-arrays
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i; j < numbers.length; j++) {
                // numbers [i,j] is a subarray.
                // 1 2 3 4 5
                // ^     <-- i, starting index
                // ^ ^ ^ <-- j, ending index
                // T T F <-- the boolean for the following if expression.
                if (oddPreSum[j + 1] - oddPreSum[i] <= k) {
                    set.add(new ArrayList<>(nums.subList(i, j + 1)));
                } else {
                    break; // we have get all subarrays starting from this i.
                }
            }
        }
        return set.size();
    }
}

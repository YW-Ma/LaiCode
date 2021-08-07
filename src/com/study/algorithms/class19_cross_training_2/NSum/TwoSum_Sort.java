package com.study.algorithms.class19_cross_training_2.NSum;

import java.util.Arrays;

public class TwoSum_Sort {
    // clarify:
    // 1. integer
    // 2. duplication? maybe
    // 3. true/false or the pair's indices
    // sorted or unsorted?

    // Unsorted 1: (also can be used in sorted)
    // step 1: sort the array
    // step 2: two pointers
    // TC: O(nlogn + n)
    // SC: O(1)
    public boolean existSum(int[] array, int target) {
        // step 1
        Arrays.sort(array); // ascending order, O(n log(n)) for primitive types
        // step 2
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            // i 向右移动是sum变大
            // j 向左移动是sum变小
            int sum = array[i] + array[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
                // continue; // 【1】如果不用if-else则一定要continue，不然不能再保证 i < j了。 可能会出现 i==j 即二者指向一个元素的情况， 那么 [2,2,3],6 就可能 ij都指向3，从而得到target 6
            } else if (sum == target) {
                return true;
            }
        }
        return false;
    }
}

// 另外，还可以优化：
// 如果优化空间，则直接用两个for循环。     和排序解法类似的，也可以原位置做heapify，然后pop（这样相当于排序了，但是不需要额外空间的开销）【是一点都不需要，而不是常量空间】
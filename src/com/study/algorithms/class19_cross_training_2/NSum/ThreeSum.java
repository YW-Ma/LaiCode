package com.study.algorithms.class19_cross_training_2.NSum;

import java.util.*;

public class ThreeSum {
    public List<List<Integer>> allTriples(int[] array, int target) {
        // find all triples that sum to target, no duplicate. only one time can be used.
        Arrays.sort(array);
        
        List<List<Integer>> res = new ArrayList<>();
        // arr[i] + arr[j] + arr[k] == target
        for (int i = 0; i < array.length; i++) {
            // arr[j] + arr[k] == target - arr[i] <-- sum should be unique
            if (i >= 1 && array[i] == array[i - 1]) {
                continue;
            }
            int sum = target - array[i];
            
            // two sum
            int j = i + 1;
            int k = array.length - 1;
            while (j < k) {
                if (array[j] + array[k] == sum) { // >>>>> should ignore all duplicate value using while loop, especially when equal (will be added into res)
                    res.add(Arrays.asList(array[i], array[j], array[k]));
                    // 加完了一次，也是需要移动的，不然会被卡住（即不断地进入 j+k == sum)
                    j++;
                    while (j < k && array[j] == array[j - 1]) {
                        j++;
                    }
                } else if (array[j] + array[k] > sum) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        
        return res;
    }
}

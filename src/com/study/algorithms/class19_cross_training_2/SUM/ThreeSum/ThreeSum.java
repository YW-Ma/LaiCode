package com.study.algorithms.class19_cross_training_2.SUM.ThreeSum;
/*Determine if there exists three elements in a given array that sum to the given target number. Return all the triple of values that sums to target.

Assumptions
The given array is not null and has length of at least 3
No duplicate triples should be returned, order of the values in the tuple does not matter

Examples
A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]*/

import java.util.*;

public class ThreeSum {
    // TC: O(n^2)
    // SC: O(1)
    public List<List<Integer>> allTriples(int[] array, int target) {
        // TODO: Find all pairs
        // Assumption: array is not null and array.length >= 3
        List<List<Integer>> res= new ArrayList<>();
        Arrays.sort(array);

        for (int i = 0; i < array.length; i++) {
            // Our goal is to find  i < j < k, arr[i] + arr[j] + arr[k] == target
            // To make sure there is no duplicate tuple,
            // We ignore all the duplicate possible i. ([i,j,k] 三元组，要保证 i,j都要unique才行)
            if (i > 0 && array[i] == array[i - 1]) { // 即保证unique
                continue;
            }

            int j = i + 1;
            int k = array.length - 1; // 准备进行 two sum
            while (j < k) {
                int tmp = array[j] + array[k];
                if (tmp + array[i] == target) { // [two sum case 1]
                    res.add(Arrays.asList(array[i], array[j], array[k]));
                    j++;
                    // ignore all possible duplicate j as well
                    while (j < k && array[j] == array[j - 1]) {
                        j++;
                    }
                } else if (tmp + array[i] < target) { // two sum case 2
                    j++;
                } else {
                    k--;
                }
            }
        }

        return res;
    }
}

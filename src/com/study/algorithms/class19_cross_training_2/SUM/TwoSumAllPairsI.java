package com.study.algorithms.class19_cross_training_2.SUM;

import java.util.*;

/*
Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.

Assumptions
The given array is not null and has length of at least 2.

Examples
A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
*/

// 只需要找到可能的组合

public class TwoSumAllPairsI {
    public List<List<Integer>> allPairs(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Map<Integer, List<Integer>> numToIndices = new HashMap<>(); // map记录同一个value的indices
        for (int i = 0; i < array.length; i++) {
            List<Integer> indices = numToIndices.get(target - array[i]);

            if (indices != null) {
                for (int index : indices) {
                    res.add(Arrays.asList(index, i)); // 看example，要相同的在后面。
                }
            }

            if (!numToIndices.containsKey(array[i])) {
                numToIndices.put(array[i], new ArrayList<Integer>());
            }
            numToIndices.get(array[i]).add(i);
        }
        return res;
    }
}

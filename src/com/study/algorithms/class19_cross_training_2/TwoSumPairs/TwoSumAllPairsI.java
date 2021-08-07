package com.study.algorithms.class19_cross_training_2.TwoSumPairs;

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

// 简单思路：每次遇到一个value，就在已经遇到的里面找 target-value 的index，全部凑一遍。
//          然后把这个value对应的index，加入到对应的hashMap里面，后面的元素就可以搜索到它了。

public class TwoSumAllPairsI {
    public List<List<Integer>> allPairs_Redo(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            // get the indices of the target-num value
            List<Integer> indices = map.get(target - array[i]);
            if (indices != null) {
                for (int index : indices) {
                    res.add(Arrays.asList(index, i));
                }
            }
            
            // register the current index to the target-num
            if (!map.containsKey(array[i])) {
                // 没有的话就建立一个，才可以register
                map.put(array[i], new ArrayList<Integer>());
            }
            map.get(array[i]).add(i);
        }
        return res;
    }
    
    
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

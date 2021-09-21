package com.study.algorithms.class08_DFS.DFSII;

import java.util.*;

public class AllPermutationsOfSubsets {
    // https://app.laicode.io/app/problem/643?plan=3
    // 主要是注意API的使用。
    public List<String> allPermutationsOfSubsets(String set) {
        List<String> results = new ArrayList<>();
        if (set == null) {
            return results;
        }
        char[] array = set.toCharArray();
        helper(array, 0, results);
        return results;
    }
    
    private void helper(char[] array, int idx, List<String> results) {
        // each layer represents a position.
        // base case: i == array.length, will omit the for loop and return.
        results.add(new String(array, 0, idx));
        for (int i = idx; i < array.length; i++) {
            swap(array, i, idx);
            helper(array, idx + 1, results); // try next posiiton
            swap(array, i, idx);
        }
    }
    
    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

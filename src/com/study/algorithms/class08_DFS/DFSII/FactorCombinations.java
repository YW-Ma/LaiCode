package com.study.algorithms.class08_DFS.DFSII;

import java.util.*;

public class FactorCombinations {
    public List<List<Integer>> combinations(int target) {
        // we need an order to avoid duplicated results.
        // all possible --> DFS
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(target, 2, cur, results);
        return results;
    }
    
    private void helper(int remain, int minFactor, List<Integer> cur, List<List<Integer>> results) {
        // base case --> remain == 1
        if (remain == 1 && cur.size() > 1) { // since i <= remain can be add into cur, so cur may contain remain itself as a result.
            results.add(new ArrayList<>(cur)); // a copy of cur. not the reference of cur.
            return;
        }
        
        // all branches: try all possible factor from minFactor to remain / 2 (because smallest factor is 2)
        for (int i = minFactor; i <= remain; i++) { // cannot be remian / 2, that will omit some factors. (remain == 2, factor == 2)
            if (remain % i == 0) { // the definition of a factor
                cur.add(i);
                helper(remain / i, i, cur, results);
                cur.remove(cur.size() - 1);
            }
        }
    }
}

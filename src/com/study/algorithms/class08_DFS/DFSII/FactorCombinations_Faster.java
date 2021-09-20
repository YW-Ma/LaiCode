package com.study.algorithms.class08_DFS.DFSII;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations_Faster {
    // 这个方案可以加速，事先把所有factor找出来，然后后续只在factor里面找解。
    public List<List<Integer>> combinations(int target) {
        // we need an order to avoid duplicated results.
        // all possible --> DFS
        List<List<Integer>> results = new ArrayList<>();
        if (target <= 1) {
            return results;
        }
        List<Integer> cur = new ArrayList<>();
        List<Integer> factors = new ArrayList<>();
        generateFactors(factors, target);
        helper(factors, target, 2, cur, results);
        return results;
    }
    
    private void generateFactors(List<Integer> factors, int target) {
        for (int i = 2; i < target; i++) {
            if (target % i == 0) {
                factors.add(i);
            }
        }
    }
    
    private void helper(List<Integer> factors, int remain, int minFactor, List<Integer> cur, List<List<Integer>> results) {
        // base case --> remain == 1
        if (remain == 1) {
            results.add(new ArrayList<>(cur)); // a copy of cur. not the reference of cur.
            return;
        }
        
        // all branches: try all possible factor from minFactor
        for (int i : factors) {
            if (i < minFactor) {
                continue;
            }
            if (remain % i == 0) {
                cur.add(i);
                helper(factors, remain / i, i, cur, results);
                cur.remove(cur.size() - 1);
            }
        }
    }
}

package com.study.algorithms.class08_DFS.DFSII;
import java.util.*;
public class AllSubsetsIIOfSizeK {
    public List<String> subSetsIIOfSizeK(String set, int k) {
        // Write your solution here
        // abbb
        //     ^
        List<String> results = new ArrayList<>();
        if (set == null || k > set.length()) {
            return results;
        }
        StringBuilder sb = new StringBuilder();
        char[] array = set.toCharArray();
        Arrays.sort(array);
        helper(results, array, sb, 0, k);
        return results;
    }
    
    private void helper(List<String> results, char[] array, StringBuilder sb, int idx, int k) {
        // base case:
        if (sb.length() == k) {
            results.add(sb.toString());
            return;
        }
        if (idx >= array.length) {
            return;
        }
        
        // branch 1 append
        sb.append(array[idx]);
        helper(results, array, sb, idx + 1, k);
        sb.deleteCharAt(sb.length() - 1);
        // branch 2 skip
        while (idx + 1 < array.length && array[idx] == array[idx + 1]) {
            idx++;
        }
        helper(results, array, sb, idx + 1, k);
    }
}

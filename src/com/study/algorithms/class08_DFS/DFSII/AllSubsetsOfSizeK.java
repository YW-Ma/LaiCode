package com.study.algorithms.class08_DFS.DFSII;
import java.util.*;
public class AllSubsetsOfSizeK {
    public List<String> subSetsOfSizeK(String set, int k) {
        // k layers, each layer try all char that hasn't been tried.
        // return a List of String, we also need a stringbuilder.
        // if k == 0, return [""] a list of an empty string
        // if k > length of set, return null.
        
        if (set == null || k > set.length()) {
            return null;
        }
        
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] array = set.toCharArray();
        helper(res, sb, array, 0, k);
        return res;
    }
    
    private void helper(List<String> res, StringBuilder sb, char[] array, int curIdx, int k) {
        if (sb.length() == k) {
            res.add(sb.toString());
            return;
        }
        
        for (int i = curIdx; i < array.length; i++) {
            sb.append(array[i]);
            helper(res, sb, array, i + 1, k);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

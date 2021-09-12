package com.leetcode;

import java.util.*;

// There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
// You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
// It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
// Return the original array nums. If there are multiple solutions, return any of them.
public class RestoreArrayFromAdjacentPairs {
    public int[] restoreArray(int[][] pairs) {
        // assume valid input
        // build map
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < pairs.length; i++) {
            if (!map.containsKey(pairs[i][0])) {
                map.put(pairs[i][0], new ArrayList<Integer>());
            }
            if (!map.containsKey(pairs[i][1])) {
                map.put(pairs[i][1], new ArrayList<Integer>());
            }
            map.get(pairs[i][0]).add(pairs[i][1]);
            map.get(pairs[i][1]).add(pairs[i][0]);
        }
        int[] res = new int[pairs.length + 1];
        int index = 0;
    
        // find head
        for (int key : map.keySet()) {
            if (map.get(key).size() == 1) {
                res[index++] = key;
                break;
            }
        }
    
        // interation
        while(index < res.length) { // index points to the place to be add
            ArrayList<Integer> neis = map.get(res[index - 1]);
            if (index - 2 >= 0 && neis.get(0) == res[index - 2]) {
                res[index++] = neis.get(1);
            } else {
                res[index++] = neis.get(0);
            }
        }
        return res;
    }
}

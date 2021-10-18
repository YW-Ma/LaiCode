package com.interview.walmartlabs.leetcode;

import java.util.*;

public class GroupAnagrams {
/*
给定一组words，把其中是anagram的合并到一个list里面，输出list of list
sol 1: O(n*klogk) 对每个word排序，把排序后相同的合并到一个list

sol 2: O(n*k) 对每个word做letter count，把count后相同的合并到同一个list
* */
    
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        
        // 用List或者String记录26个字母的cnt作为key，value则是List of String
        HashMap<List<Integer>, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // count
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            // save results
            List<Integer> key = new ArrayList<>();  // 也可以用StringBuilder，但是那样要在两个count之间插入一个#符号，防止1、2被认为是12.
            for (int i : count) {
                key.add(i);
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}

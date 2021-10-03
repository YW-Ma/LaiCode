package com.oa.codesignal;

import java.util.*;

public class LetterCombinationsOfPhoneNumber {
    public static void main(String[] args) {
    
    }
    
// Input: digits = "23"
// Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    
    public static List<String> letterCombinations(String digits) { // digits -> 2-9
        // corner cases:
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        
        char[] array = digits.toCharArray();
        String[] panel = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};// 可以用hashMap，也可以用String array来存储panel。
        
        // layers: # of digit
        // how many nodes: how many char can try
        // base case: all digit has been used
        StringBuilder cur = new StringBuilder();
        List<String> res = new ArrayList<String>();
        helper(array, panel, 0, cur, res);
        return res;
    }
    
    public static void helper(char[] array, String[] panel, int index, StringBuilder cur, List<String> res) {
        // base case
        if (index == array.length) {
            res.add(cur.toString());
            return;
        }
        
        // recursion rule
        for (char ch : panel[array[index] - '0'].toCharArray()) {
            cur.append(ch);
            helper(array, panel, index + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
    
    
    // 下面的方法用的是map. 重点在于：声明中要用Generics执行 + Map.of(K, V ...) 来初始化
    public static List<String> letterCombinations2(String digits) { // digits -> 2-9
        // corner cases:
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        
        char[] array = digits.toCharArray();
        Map<Character, String> panel = Map.of('2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
        // layers: # of digit
        // how many nodes: how many char can try
        // base case: all digit has been used
        StringBuilder cur = new StringBuilder();
        List<String> res = new ArrayList<String>();
        helper2(array, panel, 0, cur, res);
        return res;
    }
    
    public static void helper2(char[] array, Map<Character, String> panel, int index, StringBuilder cur, List<String> res) {
        // base case
        if (index == array.length) {
            res.add(cur.toString());
            return;
        }
        
        // recursion rule
        String letters = panel.get(array[index]);
        for (char ch : letters.toCharArray()) {
            cur.append(ch);
            helper(array, panel, index + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}

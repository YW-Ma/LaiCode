package com.study.algorithms.class08_DFS.DFSII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given a telephone keypad, and an int number,
// print all words which are possible by pressing these numbers,
// the output strings should be sorted.
// {0 : "", 1 : "", 2 : "abc", 3 : "def", 4 : "ghi", 5 : "jkl", 6 : "mno", 7 : "pqrs", 8 : "tuv", 9 : "wxyz"}
// Assumptions:
//      The given number >= 0
//      Examples:
//          if input number is 231, possible words which can be formed are:
//          [ad, ae, af, bd, be, bf, cd, ce, cf]
public class CombinationsForTelephonePad {
    public static void main(String[] args) {
        combinations(5002);
    }
    
    public static String[] combinations(int number) {
        List<String> res = new ArrayList<>(); // since we have to sort the res, so I use List here.
        String[] num2String = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder sb = new StringBuilder();
        char[] numArray = Integer.toString(number).toCharArray();  // 学习这个API
        helper(numArray, 0, num2String, sb, res);
        return res.toArray(new String[0]);  // 学习这个API
    }
    
    private static void helper(char[] arr, int idx, String[] num2String, StringBuilder sb, List<String> res) {
        // base case:
        if (idx == arr.length) {
            res.add(sb.toString());
            return;
        }
        
        // recursion rule: try each possible char for current index
        int val = arr[idx] - '0';
        char[] chars = num2String[val].toCharArray();
        
        if (chars.length == 0) { // 0 and 1 are special:
            // omit
            helper(arr, idx + 1, num2String, sb, res);
        } else { // 2 - 9
            for (char ch : chars) {
                sb.append(ch);
                helper(arr, idx + 1, num2String, sb, res);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
}

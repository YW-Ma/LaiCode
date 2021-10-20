package com.interview.walmartlabs.leetcode;

public class LongestPalindromeGenerating {
    public int longestPalindrome(String s) {
        // case sensitive
        // build longest palindrome
        // count, and for each even, length += even length
        //            for each odd, length += odd / 2 * 2,     if length is still even, can add only one from odd, (then become odd)
        int[] count = new int[128]; // ASCII range
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        
        int ans = 0;
        
        for (int v : count) {
            ans += v / 2 * 2;
            if (ans % 2 == 0 && v % 2 == 1) {
                ans++; // then become odd, can never enter this line.
            }
        }
        return ans;
    }
}

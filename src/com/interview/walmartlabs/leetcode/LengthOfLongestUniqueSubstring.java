package com.interview.walmartlabs.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestUniqueSubstring {
    public int lengthOfLongestSubstring(String s) {
        // sliding window
        int maxLength = 0;
        int curLength = 0;
        int startIndex = 0;
        Map<Character, Integer> map = new HashMap<>(); // char -> index
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                curLength++;
            } else {
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
                startIndex = Math.max(startIndex, map.get(s.charAt(i)) + 1);
                map.put(s.charAt(i), i);
                curLength = i - startIndex + 1;
            }
        }
        
        if (curLength > maxLength) {
            maxLength = curLength; // 防止整个都是unique，导致没有进入else， 也可以通过在if中每次都更新maxLength代替。
        }
        return maxLength;
    }
}

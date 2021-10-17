package com.interview.walmartlabs.leetcode;

import java.util.*;

public class AlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        // check first different char
        // if one ends earlier, then it's should be put before the other.
        
        // corner case:
        if (words.length <= 1) {
            return true;
        }
        
        // Solution: HashMap char -> index
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            // 可以套一个for循环, 比while高效
            for (int j = 0; j < words[i].length(); j++) {
                // 当前是撸左侧的word
                // 1. 如果右侧走完了，左侧还没走完，return false
                // 2. 如果左右相同才继续，不相同：如果合法顺序则break，非法顺序则return false
                
                if (j >= words[i + 1].length()) {
                    return false;
                }
                
                int leftIdx = map.get(words[i].charAt(j));
                int rightIdx = map.get(words[i + 1].charAt(j));
                if (leftIdx < rightIdx) {
                    break; // 合法顺序, check next pair of words
                }
                if (leftIdx > rightIdx) {
                    return false;
                }
            }
        }
        return true;
    }
}

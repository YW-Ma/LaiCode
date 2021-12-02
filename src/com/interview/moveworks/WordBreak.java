package com.interview.moveworks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    // input: string, wordDict
    // output: string can be segmented as words in dict or not
    // solution: DFS or DP
    // DP[i] --> first i letters can be segmented as words or not
    // Formula:
    //  DP[0] = T;
    //  DP[i] = {cut down last j letters, check first i-j in DP and last j letters in dict}
    
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        String str = "01234567";
        System.out.println(str.substring(4, 8));
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        // corner cases:
        if (wordDict.size() == 0) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }

// first i letters, i from 0 to length.
        boolean[] dp = new boolean[s.length() + 1];
// prepare the set for dictionary
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }

// DP base rule:
        dp[0] = true;

// DP induction rule:
// for first i letters.
        for (int i = 1; i <= s.length(); i++) {
            // try to “cut” the last j letters down
// leetcode, i=8, j=4, left:leet, right:code
// 01234567
            dp[i] = false; // default value
            for (int j = 1; j <= i; j++) {
                // left part: s[0, i-1-j]
                // right part: s[i-j, i-1]
                int leftRange = i - j; // first (i - j) letters
                String rightPart = s.substring(i - j, i);
                if (dp[leftRange] && set.contains(rightPart)) {
                    dp[i] = true;
                    break;
                }
            }
        }

// original question: dp[s.length()]
        return dp[s.length()];
    }
    
}

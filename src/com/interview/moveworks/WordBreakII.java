package com.interview.moveworks;

import java.util.*;

public class WordBreakII {
    // input string and a dictionary
    // output all possible segmented sentences.
    
    // boolean DP --> List of sub-sentences DP
    
    public static void main(String[] args) {
        WordBreakSolution wbs = new WordBreakSolution();
        List<String> res = wbs.wordBreak("catsanddog", Arrays.asList("cat","cats","and","sand","dog"));
        System.out.println(res);
    }
}

class WordBreakSolution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        // corner cases:
        if (wordDict.size() == 0) {
            return new ArrayList<>();
        }
        if (s.length() == 0) {
            return new ArrayList<>();
        }

        // first i letters, i from 0 to length.
        List<List<String>> dp = new ArrayList<>();
        // prepare the set for dictionary
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }

        // DP base rule:
        dp.add(Arrays.asList("")); // DP[0] has ""

        // DP induction rule:
        // for first i letters.
        for (int i = 1; i <= s.length(); i++) {
            // try to “cut” the last j letters down
            // leetcode, i=8, j=4, left:leet, right:code
            // 01234567
            List<String> curList = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                // left part: s[0, i-1-j]
                // right part: s[i-j, i-1]
                int leftRange = i - j; // first (i - j) letters
                String rightPart = s.substring(i - j, i);
                if (!dp.get(leftRange).isEmpty() && set.contains(rightPart)) {
                    List<String> prevList = dp.get(leftRange);
                    for (String sentence : prevList) {
                        
                        // 在每个单词前附加空格，但是对第一个单词不需要
                        if (sentence.length() == 0) {
                            curList.add(rightPart);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            curList.add(sb.append(sentence).append(" ").append(rightPart).toString());
                        }
                        
                    }
                    // no early return, we need all possible combo.
                }
            }
            dp.add(curList);
        }

        // original question:
        return dp.get(s.length());
    }
}

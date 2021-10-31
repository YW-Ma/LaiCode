package com.interview.tiktok;

import java.util.Arrays;

public class LongestStringChain {
    //  示例：
    //  输入：["a","b","ba","bca","bda","bdca"]
    //  输出：4
    //  解释：最长单词链之一为 "a","ba","bda","bdca"。
    //  链接：https://leetcode-cn.com/problems/longest-string-chain
    public int longestStrChain(String[] words) {
        // DP[i] --> the length of chain ends with ith word (sequence length)
        // 保存每个word结尾的串的长度
        // 找到所有可以来自的word，取其中最长的就行了。

        // preprocess: ascending sort

        // base rule: 1
        // induction rule: max(d[i], d[j] + 1), j is the word that can change to i.

        // sort using word length.
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int globalMax = Integer.MIN_VALUE;
        int[] dp = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            // base rule;
            dp[i] = 1;
            // induction rule:
            for (int j = 0; j < i; j++) {
                if (match(words, i, j)) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            globalMax = Math.max(globalMax, dp[i]);
        }
        return globalMax;
    }

    private boolean match(String[] words, int i, int j) {
        // what is match: size diff by 1, and all letters in pre can be found in cur in order
        String cur = words[i];
        String pre = words[j];
        if (cur.length() - pre.length() != 1) {
            return false;
        }

        // abcd
        //    i
        // abd
        //   j

        int pi = 0;
        int pj = 0;
        while (pi < cur.length() && pj < pre.length()) {
            if (cur.charAt(pi) == pre.charAt(pj)) {
                pj++;
            }
            pi++;
        }
        // 无论如何都让长的继续走，但是短的只有匹配了才走。 意思是长的可以跳过不匹配的部分。
        // 这样如果结尾的时候，发现短的走到头了，说明是二者匹配了短的里面的字符
        return pj == pre.length();
    }
}

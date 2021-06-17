package com.study.algorithms.class12_DP_2.重听;

import java.util.HashSet;
import java.util.Set;

public class DictionaryWord {
    // M[i] represents whether or not [the first i letters] can be a concatenation of dictionary words.

    // [1] [base rule]:
    // M[0] = T     -->    since empty string is the subset of an empty array.
    // 好处： 如果第一个字母就是单词，那么在它前切割，则切出来左大段M[0] 和右小段 它自己。 （即当右小段是单词的时候，依然可以切一刀）
    //      另一个定义方法是，每次切在i的后面。并且把不切的情况单独做一个分支判断。
    // how to cut: cut before i-th letter

    // [2] [induction rule]:
    // "bobcat"
    // i = 3 --> deal with first 3 letters "bob"
    // to cut bob, we have options bellow:
    // j [1,3], cut before j
    // (1) j == 1, "" && "bob",  M[0] && "bob" in dict
    // (2) j == 2, "b" && "ob",  M[1] && "ob" in dict
    // (3) j == 3, "bo" && "b",  M[2] && "b" in dict
    // M[4] = (1) || (2) || (3)
    // if one of these options is valid, then we can mark M[4] as true, since its a valid concatenation of dict words.

    public boolean canBreak(String input, String[] dictArray) {
        if (dictArray == null || input == null) {
            return false;
        }
        Set<String> dict = buildSet(dictArray);
        boolean[] M = new boolean[input.length() + 1];
        // base rule:
        M[0] = true;
        // induction rule:
        //                i [1, size]
        //                cut before j-th element, [0, i)
        // M[1] b        |b
        // M[2] bo       |bo   b|o
        // M[3] bob      |bob  b|ob   bo|b
        // ...
        // M[6] bobcat   ... bob|cat ...
        //                    /  \
        //                  M[3]  check in dict

        // Time: O(input.length ^ 3)
        // Space: O(input.length)

        for (int i = 1; i <= input.length(); i++) {
            boolean isDictWord = false;
            for (int j = 0; j < i; j++) {
                boolean left = M[j];
                boolean right = dict.contains(input.substring(j, i)); // 【1】 注意切割的范围是 [j,i),  别漏了i，只写个substring(j)
                if (left && right) {
                    isDictWord = true;
                }
            }
            M[i] = isDictWord;
        }
        return M[input.length()];
    }

    private Set<String> buildSet(String[] dictArray) {
        Set<String> dict = new HashSet<String>();
        for (String arr : dictArray) {
            dict.add(arr);
        }
        return dict;
    }
}

package com.study.algorithms.class12_DP_2.重听;

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

}

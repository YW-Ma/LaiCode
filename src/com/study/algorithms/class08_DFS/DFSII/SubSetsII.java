package com.study.algorithms.class08_DFS.DFSII;

import java.util.*;
public class SubSetsII {
// Set = "abc", all the subsets are ["", "a", "ab", "abc", "ac", "b", "bc", "c"]
// Set = "abb", all the subsets are ["", "a", "ab", "abb", "b", "bb"]
// Set = "abab", all the subsets are ["", "a", "aa","aab", "aabb", "ab","abb","b", "bb"]

    // 解法：

//     aabb
//      ()
//      / \
//     a   ""            try a
//     /\  /\
//    aa a a ""          try a
//  /\   /\ /\              try b notice the duplicate "a"
//aab aa   how to dedup (see below)

// if space "i" choose don't add a letter "a"
// then the following space can no longer add "a" --> enforce an order

    // so the "" cannot generate a
//     aabb
//      ()
//      / \
//     a   ""         try a, in the don't choose "a" branch jump over all "a"
//     /\    |
//    aa a   |         try a
//  /\    |  |\
//aab aa  ab b ""       try b
    public List<String> subSets(String set) {
        List<String> result =  new ArrayList<>();
        if (set == null) {
            return result;
        }

        char[] arraySet = set.toCharArray();
        // Make sure the multi-set is sorted so that we can dedup
        Arrays.sort(arraySet);
        StringBuilder sb = new StringBuilder();
        helper(arraySet, sb, 0, result);
        return result;
    }

    // index: at current level, determine if the element at "index" should be included in the subset or not.
    private void helper(char[] set, StringBuilder sb, int index, List<String> result) {
        // base case
        if (index == set.length) {
            result.add(sb.toString());
            return;
        }

        // recursion rule:
        // situation 1: add the letter in index
        sb.append(set[index]);
        helper(set, sb, index + 1, result);
        sb.deleteCharAt(sb.length() - 1);

        // situation 2: don't add "a" (and force all following space cannot add "a")
        while (index < set.length - 1 && set[index] == set[index + 1]) {
            index++;
        }
        helper(set, sb, index + 1, result);
    }
}

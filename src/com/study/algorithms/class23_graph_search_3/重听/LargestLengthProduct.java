package com.study.algorithms.class23_graph_search_3.重听;
//"191 Largest Product of Length
//
//
//Share
//FileEditViewToolsHelp
//To enable screen reader support, press ⌘+Option+Z To learn about keyboard shortcuts, press ⌘slash
//Largest Product of Length
//Given a dictionary containing many words, find the largest product of two words’ lengths, such that
//the two words do not share any common characters.
//https://app.laicode.io/app/problem/191

import java.util.*;

// Given a dictionary containing many words,
// find the largest product of two words' lengths,
// such that the two words do not share any common characters.
/**BFS-2做法如下
 * 先获得长度乘积最大的，看有没有共同字符。如果有就找次大的，否则返回。
 * 为了先获得长度乘积最大的，需要先对size排序。
 * 如何找次大的？ 也需要一个 for for loop, 在sorted 以后的 word list里面找。
 * 但是 s1*s2 --> s1 * s3 --> ...
 *         \---> s2 * s3 --> ...
 *     实际上是一个graph
 *     我们把第一个乘数认为是"行号"， 第二个乘数认为是"列号" 那么其实是一个matrix
 *     s1*s2,  s1*s3, s1*s4 ...
 *             s2*s3, s2*s4 ...
 *                    s3*s4 ...
 *
 * 时间复杂度是什么呢？
 *  - 预处理相同
 *  - 计算的时间 ?????
 * */

/**不用BFS-2做法如下
 *  for for loop 枚举所有对，并且用HashMap检查是否有共同字符
 *  然后update 一个globalMax
 * */


public class LargestLengthProduct {
    public int largestProduct(String[] dict) {
        // 1. get bit mask (and store it in HashMap as value)
        HashMap<String, Integer> bitMasks = new HashMap<>();
        for (String word : dict) {
            bitMasks.put(word, getBitMask(word));
        }
        
        // 2. for for loop, check each pair to get max product
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < dict.length; i++) {
            for (int j = i + 1; j < dict.length; j++) {
                // check dict[i] and dict[j] don't have common letter
                if ((bitMasks.get(dict[i]) & bitMasks.get(dict[j])) != 0) {
                    continue;
                }
                maxProduct = Math.max(maxProduct, dict[i].length() * dict[j].length());
            }
        }
        return maxProduct;
    }
    
    private int getBitMask(String word) {
        int mask = 0;
        for (int i = 0; i < word.length(); i++) {
            mask |= 1 << (word.charAt(i) - 'a');
        }
    }
    
    
    public int largestProduct2(String[] dict) {
        /** 预处理，获得bitMasks 即每个单词的 字母set。 开销是O(n*L)*/
        // Assumptions: dict is not null and has length >= 2,
        // there is no null String in the dict.
        // The words in the dict only use characters 'a' - 'z'.
        // Get the bit mask for each of the word in the dict,
        // the bit mask is represented by the lowest 26 bits of an integer.
        // each of the bit represents one of the characters in 'a' - 'z'.
        HashMap<String, Integer> bitMasks = getBitMasks(dict);              // int其实是一个set。有26bit的0、1代表是否出现字母
        // sort the dict by length of the words in descending order.
        Arrays.sort(dict, new Comparator<String>() {
            @Override
            public int compare(String s0, String s1) {
                if (s0.length() == s1.length()) {
                    return 0;
                }
                return s0.length() < s1.length() ? 1 : -1;
            }
        });
        int largest = 0;
        // note the order of constructing all the pairs,
        // we make our best to try largest product.
        for (int i = 1; i < dict.length; i++) {
            for (int j = 0; j < i; j++) {
                // early break if the product is already smaller than
                // the current largest one.
                int prod = dict[i].length() * dict[j].length();
                if (prod <= largest) {
                    break;
                }
                int iMask = bitMasks.get(dict[i]);
                int jMask = bitMasks.get(dict[j]);
                // if two words do not share any common characters,
                // the bit masks "and" result should be 0 since
                // there is not any position such that in the two bit masks
                // they are all 1.
                if ((iMask & jMask) == 0) {
                    largest = prod;
                }
            }
        }
        return largest;
    }
    
    // 预处理，用Int存储"set"，把dict中每个单词的set获得到。
    // Get the bit mask for each of the words.
    private HashMap<String, Integer> getBitMasks2(String[] dict) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String str : dict) {
            int bitMask = 0;
            for (int i = 0; i < str.length(); i++) {
                // the 26 characters 'a'-'z' are mapped to 0 - 25th bit.
                // to determine which bit it is for a character x,
                // use (x - 'a') since their values are in a consecutive range.
                // if character x exists in the word, we set the bit at
                // corresponding index to 1.
                bitMask |= 1 << (str.charAt(i) - 'a');
            }
            map.put(str, bitMask);
        }
        return map;
    }
}
//
//Time: O(n^2 + |sum of all words’ length|)
//Space: O(n)
//Largest Product of Length
//Turn on screen reader support"
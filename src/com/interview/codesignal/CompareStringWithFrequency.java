package com.interview.codesignal;
// compare两个string，只有小写字母。
// 每个stirng内部可以任意换位置，所以位置不重要。
// 每个string内部两个letter出现的频率也可以互换，
// 所以这题只需要两个string每个frequency出现的 次数要一样。比如“babzccc” 和 “bbazzcz” 就返回“true”，因为z和c可以互换频率。
// 但是 “babzcccm” 和 “bbazzczl” 就不一样，因为m在第一个里出现过，第二个里没有出现过。
//     “babzcccml” 和 “bbazzczlm” 就一样
//
// If two strings are close enough?
// Given two rules to define two strings are close enough.
//   1. you can swap neighbor char any times. Ex. "abb" -> "bba" (可以随意换位置)
//   2. If two strings have the same character, then you can change the character into another.
//
//  If both strings contain "a" and "b", you can change all "a"s in the first string or change all "b"s in the first string. same as the second string
//  Ex.  Input: S1 = "babzccc", S2 = "abbzczz" Output: True

import java.util.HashMap;

// Sol.
//  Use a dictionary to record the frequency of characters.
//  Remove the same part in dictionaries
//  try to find the pair that have different character but with the same frequency

// 【重点】：理解成 frequency的数量要相同即可。 所以先求frequency，然后求frequency的数量。 为了化简一些，char和frequency都一样的就直接抵消掉了。（也可以不抵消）

public class CompareStringWithFrequency {
    public boolean compareStrings(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false; // if null or different length --> return false;
        }
        // get frequency
        HashMap<Character, Integer> charToFreq1 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            charToFreq1.put(s1.charAt(i), charToFreq1.getOrDefault(s1.charAt(i), 0) + 1);
        }
        HashMap<Character, Integer> charToFreq2 = new HashMap<>();
        for (int i = 0; i < s2.length(); i++) {
            charToFreq2.put(s2.charAt(i), charToFreq2.getOrDefault(s2.charAt(i), 0) + 1);
        }
        // [a: 1, b: 2, c: 3, z: 1]
        // [a: 1, b: 2, c: 1, z: 3]

        // remove same char-frequency, build reverse map for the rest
        HashMap<Integer, Integer> freqToCounter1 = new HashMap<>(); // freq --> how many types of char with this freq.
        HashMap<Integer, Integer> freqToCounter2 = new HashMap<>();
        for (char ch : charToFreq1.keySet()) {
            if (!charToFreq2.containsKey(ch)) { // invalid
                return false;
            }
            int freq1 = charToFreq1.get(ch);
            int freq2 = charToFreq2.get(ch);
            if (freq1 != freq2) {  // 1 has an entry that has different freq in 2
                freqToCounter1.put(freq1, freqToCounter1.getOrDefault(freq1, 0) + 1);
                freqToCounter2.put(freq2, freqToCounter2.getOrDefault(freq2, 0) + 1);
            }
        }
        // [1:1,  3:1]  --> 出现了三次的字母是1种
        // [1:1,  3:1]  --> 出现了三次的字母是1种

        // check can we change char into another with same freq      frequency的出现数量一致即可。
        // i.e. check if nums of each freq is the same.
        for (int freq : freqToCounter1.keySet()) {
            if (freqToCounter1.get(freq) != freqToCounter2.get(freq)) {
                return false;
            }
        }
        return true;
    }
}

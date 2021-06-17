package com.study.algorithms.class12_DP_2.重听;

public class tester {
    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.editDistance("abc", "dbbabc"));

        DictionaryWord dictionaryWord = new DictionaryWord();
        System.out.println(dictionaryWord.canBreak("robcatd", new String[]{"rob", "cat", "d"}));
    }
}

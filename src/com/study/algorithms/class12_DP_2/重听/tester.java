package com.study.algorithms.class12_DP_2.重听;

public class tester {
    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.editDistance("abc", "dbbabc"));

        DictionaryWord dictionaryWord = new DictionaryWord();
        System.out.println(dictionaryWord.canBreak("robcatd", new String[]{"rob", "cat", "d"}));

        ArrayHopperII arrayHopperII = new ArrayHopperII();
        System.out.println(arrayHopperII.minJump(new int[]{5,6,0,0,0,10,0,0,0}));

        LargestSquareOfOnes largestSquareOfOnes = new LargestSquareOfOnes();
        System.out.println(largestSquareOfOnes.largest(new int[][]{{1,1,1,1},{1,1,1,0},{1,1,1,1},{1,1,0,1}}));
    }
}

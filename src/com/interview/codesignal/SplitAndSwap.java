package com.interview.codesignal;

public class SplitAndSwap {
    public static void main(String[] args) {
        System.out.println(splitAndSwap("descognail", new int[]{3,2,3,1,1}));
    }
    
    public static String splitAndSwap(String str, int[] seg) {
        // input the str and the length of each segments.
        // output the new str with each pair of seg swapped.
        char[] array = str.toCharArray();
        int segIndex = 0;
        int curIndex = 0;
        StringBuilder res = new StringBuilder();
        
        while (segIndex < seg.length) {
            StringBuilder part1 = new StringBuilder();
            StringBuilder part2 = new StringBuilder();
            
            if (segIndex < seg.length && segIndex % 2 == 0) {
                for (int i = 0; i < seg[segIndex]; i++) {
                    part1.append(array[curIndex + i]);
                }
                curIndex += seg[segIndex];
                segIndex++;
            }
            
            if (segIndex < seg.length && segIndex % 2 == 1) {
                for (int i = 0; i < seg[segIndex]; i++) {
                    part2.append(array[curIndex + i]);
                }
                curIndex += seg[segIndex];
                segIndex++;
            }
            
            res.append(part2.toString()).append(part1.toString());
        }
        return res.toString();
    }
}

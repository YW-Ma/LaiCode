package com.interview.codesignal;

import java.util.Arrays;

public class CheckStringHeadAndTail {
    public static void main(String[] args) {
        boolean[] res = isSame(new String[]{"abc", "cba", "add", "ddb", "a", "d"});
        System.out.println(Arrays.toString(res));
    }
    
    public static boolean[] isSame(String[] array) {
        // 返回长度为n-1的boolean array
        // 如果相邻的两个string的头和尾相同则return true。otherwise false
        
        boolean[] res = new boolean[array.length - 1];
        for (int i = 0; i < array.length - 2; i++) {
            // compare i's tail and i+1's head
            res[i] = array[i].charAt(array[i].length() - 1) == array[i + 1].charAt(0);
        }
        return res;
    }
}

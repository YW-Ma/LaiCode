package com.interview.codesignal;
// 给两串字符串，每个char就是一个digit，
// 然后从后往前加起来，把结果放到一 个字符串输出，挺简单的。
// e.g. '99' + '99' = '1818'
// 如果写Java的话最好用StringBuilder, String 会 TLE
public class SumOfTwoString {
    public static void main(String[] args) {
    
    }
    
    public static String sumOfString(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return s2;
        }
        if (s2 == null || s2.length() == 0) {
            return s1;
        }
        return null;
    }
}

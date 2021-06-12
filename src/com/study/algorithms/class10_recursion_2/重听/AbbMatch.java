package com.study.algorithms.class10_recursion_2.重听;

public class AbbMatch {
    // Abbreviation Matching，检查输入的pattern是不是输入的string的abbrev
    // https://docs.google.com/document/d/1VT9I1JbRP4_TkQJnxJrLXFhgqRrZZoPbShgYK5N0bME/edit

    // 可以用iterative写，特别简单（两个while循环套一起）
    // 这里为了锻炼recursion，就用recursion

    //  student
    //       |
    //  s2d2t
    //     |
    // 遇到数字就往后跳n次
    // 每次pattern先走，然后看如果是letter，就走1个input，并检查是否相同
    // 如果是num，就记录完这个num，停在num后方的letter。input走num个位置，走到letter上检查是否相等。
    public boolean match(String input, String pattern) {
        // 这是一节Recursion课，所以我只写recursion方法：
        return helper(input, pattern, 0, 0);
    }

    private boolean helper(String s, String t, int si, int ti) {
        // source, target, compare ith element

        // base case:
        if (si == s.length() && ti == t.length()) {
            return true;
        }
        if (si == s.length() || ti == t.length()) {
            return false;
        }

        // recursion:
        // case 1: if current char is not a digit
        if (t.charAt(ti) < '0' || t.charAt(ti) > '9') {
            if (t.charAt(ti) != s.charAt(si)) {
                return false;
            }
            return helper(s, t, si + 1, ti + 1);
        }
        // case 2: is a digit
        int count = 0;
        //  while(t.charAt(ti) >= '0' && t.charAt(ti) <= '9') {   // 错了，注意定义域优先
        while(ti < t.length() && t.charAt(ti) >= '0' && t.charAt(ti) <= '9') {
            count = count * 10 + (t.charAt(ti) - '0');
            ti++;
        }
        si += count;
        return helper(s, t, si, ti);
    }
    // time:  O(length of input)
    // space: O(length of input)
}

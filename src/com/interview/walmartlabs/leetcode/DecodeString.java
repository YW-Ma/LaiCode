package com.interview.walmartlabs.leetcode;

import java.util.*;

public class DecodeString {
    public static void main(String[] args) {
        decodeString("3[a2[c]]");
    }

    // 每次遇到[就入栈 当前数字和字符串，每次遇到]就弹栈，之前字符串 + 倍数 * 当前字符串， 作为当前新字符串。
    public static String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<String> strStack = new ArrayDeque<>();
        String res = "";
        int multi = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                res = res + ch;
            } else if (Character.isDigit(ch)) {
                multi = multi * 10 + ch - '0';
            } else if (ch == '[') {
                numStack.offerLast(multi);
                strStack.offerLast(res);
                multi = 0;
                res = "";
            } else { // ']'
                int m = numStack.pollLast();
                String prefix = strStack.pollLast();

                StringBuilder tmp = new StringBuilder();
                tmp.append(prefix);
                while (m > 0) {
                    tmp.append(res);
                    m--;
                }

                res = tmp.toString();

            }
        }
        return res;
    }
}

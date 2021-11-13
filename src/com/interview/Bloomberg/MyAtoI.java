package com.interview.bloomberg;
// https://leetcode.com/problems/string-to-integer-atoi/
public class MyAtoI {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        int i = 0;
        int sign = 1;
        int num = 0;
        // 1. skip all leading space
        while (i < s.length) {
            if (s[i] == ' ') {
                i++;
            } else {
                break;
            }
        }
        if (i >= s.length) { // early return branch
            return 0;
        }
        
        
        // 2. sign
        if (s[i] == '+' || s[i] == '-') {
            sign = (s[i] == '-') ? -1 : 1;
            i++;
        }
        if (i >= s.length) { // early return branch
            return 0;
        }
        
        
        // 3. digit
        while (i < s.length) { // non-digit will return -1.
            // we need digit now, also cannot exceed the length
            int digit = Character.digit(s[i], 10);
            if (digit == -1) { // +-  or +aadsf
                break;
            }
            // overflow preventing:
            if (sign == 1) {
                if (num > Integer.MAX_VALUE / 10 || num == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10) {
                    return Integer.MAX_VALUE;
                }
            }
            if (sign == -1) {
                if (num > 0 - Integer.MIN_VALUE / 10 || num == 0 - Integer.MIN_VALUE / 10 && digit > 0 - Integer.MIN_VALUE % 10) {
                    return Integer.MIN_VALUE;
                }
            }
            num = num * 10 + digit;
            i++;
        }
        return num * sign;
    }
}

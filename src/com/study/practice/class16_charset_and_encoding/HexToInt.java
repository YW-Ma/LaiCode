package com.study.practice.class16_charset_and_encoding;

public class HexToInt {
    // 10 11 12 13 14 15
    // A  B  C  D  E  F
    // a  b  c  d  e  f

    public static int parseHex(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10; // 记得加上base
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        return Integer.MAX_VALUE; // error
    }
}

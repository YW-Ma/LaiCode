package com.study.practice.class16_charset_and_encoding;

public class StringCornerCases_ATOI {
    // String的corner case特别多，主要是因为内容多样

    // Exercise 1: atoi    (convert a string to an integer) (assume it's a valid integral number)
    // [1] corner case:
    // - null or empty                  : null, "" --> 0
    // - leading spaces                 : "   123" --> 123
    // - sign + or -                    : "+1" --> "1", "-1" --> -1, 如果两个都有 "+-1" 是非法，返回0                                      【1】
    // - trailing spaces or other chars : 忽略trailing部分
    // - overflow an integer            : Integer.MAX_VALUE or MIN_VALUE
    // - overflow long                  : 必须要每次比较是否overflow int，而不是等最后才判断是否overflow。否则最后可能已经overflow long了。     【2】

    // [2] Backus-Naur form
    // 巴科斯范式，定义spc和数字，然后表达一个INT的定义：                                                                                     【3】
    //  SPC::=' '
    //  NUM::='0'|'1'| ... |'9'
    //  INT::=(SPC) ['+'|'-'] (NUM+) (SPC*)

    //     []是可有可无（0 or 1）
    //     +是至少一个
    //     *是至少0个

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) { // corner case 1
            return 0;
        }

        int n = str.length();
        int i = 0;
        while (i < n && str.charAt(i) == ' ') { // corner case 2: jump over leading spaces
            i++;
        }

        boolean positive = true;
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) { // corner case 3:
            positive = (str.charAt(i) == '+');
            i++;
        }

        long sum = 0; //corner case 5
        // 如果是positive num，并且  sum 超过integer最大值，那么overflow
        // 如果是negative num，并且 -sum 超过integer最小值，那么overflow
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '0') { // corner case 3 和 4，关于非数字的部分
            sum = sum * 10 + (str.charAt(i) - '0');
            if (positive && sum > (long)Integer.MAX_VALUE) { // 为什么每次都要检查，因为防止结果overflow long了。超过sum的比较范围。
                return Integer.MAX_VALUE;
            }
            if (!positive && -sum < (long)Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }
        sum = positive ? sum : -sum;
        return (int)sum;
    }
}
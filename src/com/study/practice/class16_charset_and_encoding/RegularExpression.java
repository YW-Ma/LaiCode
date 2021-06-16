package com.study.practice.class16_charset_and_encoding;

public class RegularExpression {
    // 可以用正则表达式  https://www.laioffer.com/en/news/2017-01-11-how-to-do-regex-matching/
    // 注意regular expression中，
    //  [] 代表匹配一次， （不包括0次）
    //  []* 代表匹配0或多次，
    //  []+代表1或多次，
    //  []? 代表0或1次
    // ^ 代表line的开头
    // $ 代表line的结尾
    // . 代表any char          包括empty    都不需要转义
    // [^...] ^是不在这个范围内的
    // [a-zA-Z] 用-来代表范围
    // [a]{5}    {n}代表   恰好n次
    // [a]{5,}   {n,}代表  n到无穷次
    // [a]{5,10} {n,m}代表 n到m次

    public static void main(String[] args) {
        String pattern = "[a]+.";
        String str = "aaaab";
        System.out.println(str.matches(pattern));
    }

    // 题目：如何自己写一个简单的regular expression
    // 只需要支持字母、*、. 这三个。
    // "aab"
    // "c*a*b*" 是match的，因为*是0到多个
}

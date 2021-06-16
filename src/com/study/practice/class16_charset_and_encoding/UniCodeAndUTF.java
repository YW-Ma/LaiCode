package com.study.practice.class16_charset_and_encoding;

public class UniCodeAndUTF {
    // Unicode是charset，字符集
    // UTF是表达方法，
    // 有变长编码，
    // 0xxxxxxx
    // 11xxxxxx 10xxxxxx
    // ...
    // xx部分就是Unicode的内容。

    // Java的char，是unicode，是16位的。
    // 所以和C不一样，255 + 100不会溢出S

    // 面试考察是否存在一个single digit，比如2：
    // 取出lowest digit： digit = a % 10
    // 移位：             a /= 10S
}

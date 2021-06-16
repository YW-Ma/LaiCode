package com.study.practice.class16_charset_and_encoding;

public class StringCornerCases_Validator {
    // Validate if a given string is numeric (包括小数）

    // 注意 ".", ".0", "" 认为是true， assume 无正负号

    // backus-naur form
    // SPC::=' '
    // NUM::='0'|'1'| ... |'9'
    // DOT::='.'
    // NUMBERIC::=  (SPC*)                  --> leading space
    //              (NUM*) [DOT(NUM*)]      --> integral part, decimal part    【1】注意小数点和小数部分是绑定的
    //              (SPC*)                  --> trailing space

    public boolean isNumber(String s) {
        String str = s.trim(); // corner case 0:  trim 删掉 leading和trailing spaces。
        boolean seenPoint = false; // 是否见到过DOT，有的话只能有一次
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '.') {     // corner case 1: found a dot, check if have seen it before.
                if (seenPoint) {
                    return false;
                }
                seenPoint = true;
            }

            else if (c < '0' || c > '9') {  // corner case 2: found a invalid char
                return false;
            }
        }
        return true;
    }

    // 想要提升难度的话，可能会要求"科学计数法" "正负号" e E + -
    // 要沟通，问问是不是要做。不要求的话就不要主动弄

    // 进阶版本：支持科学计数法
    // seenE: --> 和seenPoint一样只能出现一个，而且必须前后至少有一个digit。 后面因为是次方，所以可以有正有负，
    //          科学计数part ::= (NUM+) E ([+|-] NUM+)


    // 带sign：
    // NUMBERIC::=  (SPC*)
    //              [+|-] (NUM*) [DOT(NUM*)]  [科学计数part]
    //              (SPC*)


    // 代码主要的判断，一共有6个，都是boolean变量。
    // 1. seenNumber      --> 不能在seenNumber后出现seenSignBeforeE
    // 2. seenSignBeforeE --> E前面那个sign，只能有一个，代表正负数
    // 3. seenE           --> 只能一个，而且前后都要有数字
    // 4. seenPoint       --> 只能在E前有一个小数点  （和seenE、seenPoint矛盾）
    // 5. seenNumberAfterE  --> 不能在seenNumberAfterE后面出现Sign和Point。
    // 6. seenSignAfterE  -->  只能一个sign在E后面，代表次方的方式

    // 遇到 sign：
    // - 没seenE时，见到了Number或者Point后者Sign --> false
    // - seenE后，见到了Number或者Sign --> false
    // - 其他情况，根据seenE与否，设置seensignafter或者beforeE为true

    // 遇到 digit：
    // - seenNumber = true
    // - 如果seenE了，那么seenNumberAfterE为true

    // 遇到E了:
    // - seenE了就false
    // - 没见过Number则是false

    // 最终读完了，发现见过E但是没见过E后面的数字，也是false
    // 最后返回的是seenNumber与否，如果没有见过数字就是false
    // 代码我就不写了，


    // 另外，可以用正则表达式：看教案有内容。
    // 注意regular expression中，
    //  [] 代表匹配一次， （不包括0次）
    //  []* 代表匹配0或多次，
    //  []+代表1或多次，
    //  []? 代表0或1次
}

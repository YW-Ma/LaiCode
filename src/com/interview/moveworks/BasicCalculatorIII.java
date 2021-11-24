package com.interview.moveworks;

public class BasicCalculatorIII {
    // 带括号, 包括加减乘除
    // 思路：先写一个example
    //  1 + (4 + 3) * 5
    //  |   -------
    //  |      |
    //  |      7   *  5
    //  |      _________
    //  1   +     35
    //  ___________
    //    36
    
    // 我们先回顾一下：
    //  1 + 7 * 5   的做法：
    // 第一轮(遇到+)：  stack = [], pre = 0, cur = 1, sign = +
    // 第二轮(遇到*)：  stack = [], pre = 1, cur = 7, sign = *
    // 第三轮(遇到结尾)：stack = [1], pre = 7, cur = 5, sign = *
    //  pre * cur, 然后加到stack里面。 随后把stack里面的内容加起来。
    
    /***********************************************/
    
    // 所以如果遇到括号，当成一个子问题即可。（用递归）
    // 用一个value或者stack负责
    // 两个大branch：
    // 1. 数字
    //          遇到数字的时候，进行cur number的更新
    //          （坑） 注意不能直接continue。因为可能已经结尾，那样需要立即进行一次评估。
    
    // 2. 符号或者到结尾：  -->  非数字且非空格  or  已经到达最后一个字符
    //          遇到新的sign、结尾的时候，进行一次评价：
    //          [ a ] sign [ b ] this_sign
    //     根据sign，分四个小case：
    //     1.1  sign是优先级高的 * /, 那么a和b之间进行评估，更新到a中。b清空。sign变成this_sign
    //          相当于  [a*b] this_sign [ _ ]  __
    //     1.2  sign是 + -，那么b(-b)可能要先和后面结合，再加上a。所以a可以先加入结果。
    //          相当于  [b]  this_sign  [ _ ]  __
    //     （坑点：如果字符串已经结束，那么也要进来进行一次评估）
    
    // 3. 遇到空白符号，直接跳过（Character.isWhitespace())
    
    public int calculate(String str) {
        char[] s = str.toCharArray();
        return helper(s, 0)[0];
    }
    public int[] helper(char[] s, int i) {
        int accumulate = 0;
        int prev = 0; // a
        int cur = 0; // b
        char sign = '+'; // 默认第一个字符不是负数  -->  如果写成' ' 那么遇到第一个sign后，prev不会移动到accumulate。cur也不会移动到prev。 cur会继续和后面的累加。导致数值巨大。
        
        while (i < s.length) {
            char ch = s[i];
            
            // 左括号在符号后，计算结果相当于是更新cur。 父的cur一定已经是0了，所以如果遇到左括号，直接把返回值写到cur就行。
            //          --> 注意有可能以右括号结尾，即以一个子问题结尾。 此时value还在cur里面。所以需要把它写在处理的前面。
            // 右括号在数字后，返回accumulate + prev。需要把子问题的cur、prev处理好，准备返回accumulate + prev.  所以把它写在处理的后面。
            if (ch == '(') {
                int[] res = helper(s, i + 1);  // i+1 是为了跳过当前字符，否则会无限迭代。
                cur = res[0];
                i = res[1];  // 让子过程告诉我它走到哪里了
            }
            if (Character.isDigit(ch)) {
                cur = cur * 10 + ch - '0';
            } // end of digit
            if (i == s.length - 1 || isSymbol(ch) || ch == ')') { // 新增第三类情况
                if (sign == '+' || sign == '-') {
                    accumulate += prev; // prev已经带有符号。
                    prev = sign == '+' ? cur : -cur; // cur根据sign加入到prev中，注意带上符号，比如2-2*3 其实是 (-2) * 3  随后加入2
                    cur = 0;
                }
                if (sign == '*') {
                    prev = prev * cur;
                    cur = 0;
                }
                if (sign == '/') {
                    prev = prev / cur;
                    cur = 0;
                }
                if (isSymbol(ch)) {
                    sign = ch;
                }
            } // end of symbol
            // skip whitespace
            
            if (ch == ')') {  // ) 要等处理完了才返回，所以写在后面。
                break;
            }
            i++;
        }
        // 全部都跑完了，由于存在一个 i == s.length - 1的评估分支，所以现在只有 accumulate 和 prev 有值
        return new int[]{accumulate + prev, i};
    }
    
    private boolean isSymbol(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return true;
        }
        return false;
    }
}

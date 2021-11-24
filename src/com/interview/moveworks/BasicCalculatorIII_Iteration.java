package com.interview.moveworks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class BasicCalculatorIII_Iteration {
    public int calculate(String str) {
        // digit：全部获取，并加入num stack
        // op: 如果栈顶元素优先级>=我（加减 < 乘除），则进行运算，直到栈顶元素优先级比我小或者空了。
        // (: 加入op stack
        // ): 进行计算操作，直到op stack栈顶是 (, 然后把( pop了
        //   为了简化获取op后的while循环，给()赋予最低的优先级，这样遇到()就会暂停了

        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();
        Map<Character, Integer> priority = Map.of('(', 0, ')', 0, '+', 1, '-', 1, '*', 2, '/', 2);
        int i = 0;
        char[] s = str.toCharArray();
        while (i < s.length) {
            if (Character.isDigit(s[i])) {
                int num = 0;
                while (i < s.length && Character.isDigit(s[i])) {
                    num = num * 10 + s[i] - '0';
                    i++; // 这里已经加过了，不可以再走结尾的++
                }
                numStack.offerLast(num);
                continue;
            } else if (s[i] == '(') {
                opStack.offerLast(s[i]);
            } else if (s[i] == ')') {
                // ): 进行计算操作，直到op stack栈顶是 (, 然后把( pop了
                while (opStack.peekLast() != '(') {
                    // 进行压缩计算（注意，如同 2*2+2 这样的表达式，早已经变成了 4 + 2） 所以这里直接按照运算符弄起来就行。
                    // 实际上这个stack最多也就2步运算 （同一个括号内）
                    char op = opStack.pollLast();
                    int num1 = numStack.pollLast();
                    int num2 = numStack.pollLast();
                    numStack.offerLast(compute(num1, num2, op));
                }
                // 把(弹掉
                opStack.pollLast();
            } else { // 遇到了普通的运算符
                while (!opStack.isEmpty() && priority.get(opStack.peekLast()) >= priority.get(s[i])) {
                    char op = opStack.pollLast();
                    int num1 = numStack.pollLast();
                    int num2 = numStack.pollLast();
                    numStack.offerLast(compute(num1, num2, op)); // 这一步是当前为 +、-， 上一步为 *、\、+、-。  或者当前为 *、\，上一步为*、\ 的时候才会进入
                    // 相当于把上一步的结果先计算一下，然后再考虑把我这个op加入结果。
                    // 举例1：已有 3 5
                    //            +        然后来了个*。 那么肯定是*直接进栈。
                    // 举例2：已有 3 5
                    //            *        然后来了个+
                    //        那么会变成
                    //            15
                    //                    随后推入+
                }
                opStack.offerLast(s[i]);
            }
            i++;
        }

        // 后处理： --  里面只可能剩下加减法，根本不会出现乘除法（除非最后一步是乘除法）
        while (!opStack.isEmpty()) {
            char op = opStack.pollLast();
            int num1 = numStack.pollLast();
            int num2 = numStack.pollLast();
            numStack.offerLast(compute(num1, num2, op));
        }
        return numStack.pollLast();
    }

    private int compute(int n1, int n2, char op) {
        if (op == '+') {
            return n2 + n1;
        }
        if (op == '-') {
            return n2 - n1;
        }
        if (op == '*') {
            return n2 * n1;
        }
        if (op == '/') {
            return n2 / n1;
        }
        return 0;
    }
}

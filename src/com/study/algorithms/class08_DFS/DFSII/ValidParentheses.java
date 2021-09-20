package com.study.algorithms.class08_DFS.DFSII;

import java.util.*;

/*
*           []
*         /// \\\
*       ( ) < > { }
*      ///\\\
* */

public class ValidParentheses {
    // stack 使用方法 — 左括号入栈，右括号看到match的top后会把它弹出。（然后递归调用），随后恢复现场。
    // 栈里面不需要是具体的符号，可以是PS的index
    private static final char[] PS = new char[]{'(', ')', '<', '>', '{', '}'};
    
    public List<String> validParentheses(int l, int m, int n) {
        // how to add a left parenthese: we still has left paren. -- no order now.
        // how to add a right one: the top element in stack is the left of this one.
        Deque<Character> stack = new ArrayDeque<>(); // store unpaired parenthesis
        List<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int[] remains = new int[]{l, l, m, m, n, n};
        int total = 2 * (l + m + n);
        helper(remains, stack, sb, results, total);
        return results;
    }
    
    private void helper(int[] remains, Deque<Character> stack, StringBuilder sb, List<String> results, int total) {
        // base case: all parenthesis run out --> length() of sb is 2*(l+m+n)
        if (sb.length() == total) {
            results.add(sb.toString());
            return;
        }
        // each layer, try all possible parentheses:
        for (int i = 0; i < remains.length; i++) {
            if (i % 2 == 0) {
                // for left --> still have
                if (remains[i] > 0) {
                    sb.append(PS[i]);
                    stack.offerFirst(PS[i]);
                    remains[i]--;
                    helper(remains, stack, sb, results, total);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.pollFirst();
                    remains[i]++;
                }
            } else {
                // for right --> top matches
                if (!stack.isEmpty() && stack.peek() == PS[i - 1] && remains[i] > 0) { // condition two is unreachable, just add it to clarify the logic.
                    sb.append(PS[i]);
                    stack.pollFirst();
                    remains[i]--;
                    helper(remains, stack, sb, results, total);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.offerFirst(PS[i - 1]); // 恢复原状
                    remains[i]++;
                }
            }
        }
    }
}

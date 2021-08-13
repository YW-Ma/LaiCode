package com.study.algorithms.class08_DFS.DFSII;

import java.util.*;

public class ValidParenthesesIII {
    // All valid permutations of parentheses

    // L pairs of ()
    // M pairs fo <>
    // N pairs of {}

    // with priority restriction

    private static final char[] PS = new char[]{'(', ')', '<', '>', '{', '}'};

    public List<String> validParenthesesIII(int l, int m, int n) {
        // Assumption: l,m,n >= 0
        int[] remain = new int[]{l, l, m, m, n, n};
        int targetLen = 2 * l + 2 * m + 2 * n;
        StringBuilder cur = new StringBuilder();
        Deque<Integer> stack = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        helper(cur, stack, remain, targetLen, result);
        return result;
    }

    //        []
    //    / | | | | \
    //   (  ) < > { }   <-- some is invalid
    //  each node(a position) has maximum 6 branches
    //  2*(l+m+n) layers.

    private void helper(StringBuilder cur, Deque<Integer> stack, int[] remain, int targetLen, List<String> result) {
        if (cur.length() == targetLen) {
            result.add(cur.toString());
            return;
        }

        for (int i = 0; i < remain.length; i++) {
            if (i % 2 == 0) { // 左括号
                if (remain[i] > 0 && (stack.isEmpty() || stack.peekFirst() > i)) { // priority - 想加入左括号，栈顶元素必须比我优先级低，而且不能有我的右括号
                    cur.append(PS[i]);
                    stack.offerFirst(i);
                    remain[i]--;

                    helper(cur, stack, remain, targetLen, result);

                    cur.deleteCharAt(cur.length() - 1);
                    stack.pollFirst();
                    remain[i]++;
                }
            } else { // 右括号
                if (!stack.isEmpty() && stack.peekFirst() == i - 1) { // priority - 想加入右括号，栈顶元素必须是我的左括号
                    cur.append(PS[i]);
                    int top = stack.pollFirst(); // 把栈顶元素抵消掉
                    remain[i]--;

                    helper(cur, stack, remain, targetLen, result);

                    cur.deleteCharAt(cur.length() - 1);
                    stack.offerFirst(top); // 恢复栈顶元素为原先的那样 (top == i - 1 其实）
                    remain[i]++;
                }
            }
        }
    }
}

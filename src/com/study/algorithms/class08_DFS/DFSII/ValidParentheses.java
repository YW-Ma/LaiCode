package com.study.algorithms.class08_DFS.DFSII;

import java.util.*;

/*
*           []
*         /// \\\
*       ( ) < > { }
*      ///\\\
* */

public class ValidParentheses {
    public List<String> validParentheses(int l, int m, int n) {
        // l, m, n >= 0
        // l + m + n > 0
        List<String> results = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        Deque<Integer> stack = new ArrayDeque<>();
        int[] remains = new int[]{l, l, m, m, n, n};
        int targetLen = 2 * l + 2 * m + 2 * n;
        DFS(results, cur, remains, targetLen, 0);
        return results;
    }
    
    private void DFS(List<String> results, StringBuilder cur, int[] remains, int targetLen, int index) {
        // base case
        if (targetLen == cur.length()) {
            results.add(cur.toString());
            return;
        }
        
        // recursion rule:
        // try six branch
        for (int i = 0; i < remains.length; i++) {
        
        }
    }
}

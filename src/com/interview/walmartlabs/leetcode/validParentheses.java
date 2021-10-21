package com.interview.walmartlabs.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class validParentheses {
    // 校验一组括号是否合法（不考虑优先级）
    
    private HashMap<Character, Character> mappings;
    
    public boolean isValid(String s) {
        
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
        
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.offerLast(ch);
            } else {
                if (stack.peekLast() == mappings.get(ch)) {
                    stack.pollLast();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

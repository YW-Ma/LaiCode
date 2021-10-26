package com.interview.walmartlabs.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString_Better {
    int ptr = 0;
    char[] src;
    
    public String decodeString(String s) {
        // pattern:
        // string number [subproblem] string number [subproblem] string number [subproblem]
        // each component can be empty
        src = s.toCharArray();
        StringBuilder curStr = new StringBuilder();
        int curNum = 0;
        Deque<String> strStack = new ArrayDeque<>();
        Deque<Integer> numStack = new ArrayDeque<>();
        while (ptr < src.length) {
            if (Character.isLetter(src[ptr])) {
                curStr.append(getString());
            } else if (Character.isDigit(src[ptr])) {
                curNum = getInteger();
            } else if (src[ptr] == '[') {
                strStack.offerLast(curStr.toString());
                numStack.offerLast(curNum);
                curStr = new StringBuilder();
                curNum = 0;
                ptr++;
            } else if (src[ptr] == ']') {
                StringBuilder sb = new StringBuilder();
                sb.append(strStack.pollLast());
                int limit = numStack.pollLast();
                for (int i = 0; i < limit; i++) {
                    sb.append(curStr);
                }
                curStr = sb;
                ptr++;
            }
        }
        return curStr.toString();
    }
    // str Stack: [""
    // num Stack: [3
    // curStr:     a
    // curNum:     0
    // when meet ], multiply curStr by top of numStack, then prepend top of strStack.
    
    public String getString() {
        StringBuilder sb = new StringBuilder();
        while (ptr < src.length && Character.isLetter(src[ptr])) {
            sb.append(src[ptr]);
            ptr++;
        }
        return sb.toString();
    }
    
    public int getInteger() {
        int ret = 0;
        while (ptr < src.length && Character.isDigit(src[ptr])) {
            ret = ret * 10 + src[ptr] - '0';
            ptr++;
        }
        return ret;
    }
}

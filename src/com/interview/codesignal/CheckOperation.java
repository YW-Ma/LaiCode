package com.interview.codesignal;

import java.util.Arrays;

public class CheckOperation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(check(new int[]{1,5,3}, new char[]{'+','-','*'}, new int[]{1,5,3}, new int[]{2,0,9})));
    }
    public static boolean[] check(int[] val1, char[] ops, int[] val2, int[] target) {
        boolean[] res = new boolean[val1.length];
        for (int i = 0; i < val1.length; i++) {
            int v1 = val1[i];
            int v2 = val2[i];
            int tar = target[i];
            char op = ops[i];
            if (op == '+') {
                res[i] = tar == v1 + v2;
            } else if (op == '-') {
                res[i] = tar == v1 - v2;
            } else if (op == '*') {
                res[i] = tar == v1 * v2;
            } else if (op == '/') {
                res[i] = tar == v1 / v2;
            }
        }
        return res;
    }
}

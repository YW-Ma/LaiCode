package com.study.algorithms.class20_cross_training_3;

import java.util.*;

public class CommonElementsII {
    public List<Integer> common(int[] a, int[] b, int[] c) {
        List<Integer> common = new ArrayList<>();
        int ai = 0;
        int bi = 0;
        int ci = 0;
        while (ai < a.length && bi < b.length && ci < c.length) {
            // 谁小移谁
            if (a[ai] == b[bi] && b[bi] == c[ci]) {
                common.add(a[ai]);
                ai++;
                bi++;
                ci++;
            } else if (a[ai] <= b[bi] && a[ai] <= c[ci]) { // 注意是 <= 因为可能只是 a==b<c
                ai++;
            } else if (b[bi] <= a[ai] && b[bi] <= c[ci]) {
                bi++;
            } else {
                ci++;
            }
        }

        return common;

        // TC: max (a,b,c) length
        // SC: O(1) if print out.
    }
}

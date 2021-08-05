package com.study.algorithms.class19_cross_training_2.SUM.ThreeSum;
/*Determine if there exists a set of four elements in a given array that sum to the given target number.

Assumptions
The given array is not null and has length of at least 4

Examples
A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 9)
A = {1, 2, 2, 3, 4}, target = 12, return false*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 有三个做法：
public class FourSum {
    // TODO: find if exist

    // TODO: 方法1 Sort,  O(n^3).    i,j,m,n
    public boolean exist(int[] array, int target) {
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int m = j + 1;
                int n = array.length - 1;
                int curTarget = target - array[i] - array[j];
                // two sum:
                while (m < n) {
                    int sum = array[m] + array[n];
                    if (sum == curTarget) {
                        return true;
                    } else if (sum < curTarget) {
                        m++;
                    } else {
                        n--;
                    }
                }
            }
        }
        return false;
    }
}

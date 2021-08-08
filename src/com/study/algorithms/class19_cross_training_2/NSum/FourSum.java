package com.study.algorithms.class19_cross_training_2.NSum;

import java.util.*;

public class FourSum {
    public boolean exist(int[] array, int target) {
        Arrays.sort(array);
        // arr[i] + arr[j] + arr[m] + arr[n] = target
        for (int i = 0; i < array.length; i++) {
            if (i >= 1 && array[i] == array[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] == array[j - 1]) {
                    continue;
                }
                // non-duplicate i, j values to create non duplicate (target-arr[i]-arr[j])
                int sum = target - array[i] - array[j];


            }
        }
    }
}

package com.study.algorithms.class19_cross_training_2.NSum;

import java.util.*;

public class FourSum {
    
    // 版本1：有去重（但是实际上没有必要，因为只问ture、false，就算是一百个 {1,2,2,3} 那么也是true。）
    public boolean exist_not_good(int[] array, int target) {
        Arrays.sort(array);
        // arr[i] + arr[j] + arr[m] + arr[n] = target
        for (int i = 0; i < array.length; i++) {
            if (i > 0 && array[i] == array[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < array.length; j++) {
                if (j > i + 1 && array[j] == array[j - 1]) {
                    continue;
                }
                // non-duplicate i, j values to create non duplicate (target-arr[i]-arr[j])
                int subTarget = target - array[i] - array[j];
                // two subTarget:
                int m = j + 1;
                int n = array.length - 1;
                while (m < n) {
                    if (m > j + 1 && array[m] == array[m - 1]) {
                        m++;
                        continue;
                    }
                    
                    if (array[m] + array[n] == subTarget) {
                        return true;
                    } else if (array[m] + array[n] < subTarget) {
                        m++;
                    } else {
                        n--;
                    }
                }
            }
        }
        return false;
    }
    
    // 删掉冗余的去重代码，并且合并同类 sum。
    public boolean exist(int[] array, int target) {
        Arrays.sort(array);
        // arr[i] + arr[j] + arr[m] + arr[n] = target
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                // non-duplicate i, j values to create non duplicate (target-arr[i]-arr[j])
                int subTarget = target - array[i] - array[j];
                // two subTarget:
                int m = j + 1;
                int n = array.length - 1;
                while (m < n) {
                    int sum = array[m] + array[n];
                    if (sum == subTarget) {
                        return true;
                    } else if (sum < subTarget) {
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

package com.study.algorithms.class08_DFS.DFSII;
import java.util.*;
public class TwoSubsetsWithMinDifference {
    public int minDifference(int[] array) {
        // 1.   n/2 size
        // 2.   get the min diff
        int[] minDiff = new int[]{Integer.MAX_VALUE};
        int levelLeft = array.length / 2;
        int total = 0;
        for (int num : array) {
            total += num;
        }
        
        helper(array, levelLeft, 0, minDiff, total, 0);
        return minDiff[0];
    }
    
    private void helper(int[] array, int levelLeft, int idx, int[] minDiff, int total, int curSum) {
        if (levelLeft == 0) {
            minDiff[0] = Math.min(minDiff[0], Math.abs(total - 2 * curSum));
            return;
        }
        
        for (int i = idx + 1; i < array.length; i++) {
            curSum += array[i];
            helper(array, levelLeft - 1, i, minDiff, total, curSum);
            curSum -= array[i];
        }
    }
}

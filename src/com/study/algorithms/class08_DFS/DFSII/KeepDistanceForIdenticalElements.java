package com.study.algorithms.class08_DFS.DFSII;

import java.util.Arrays;

public class KeepDistanceForIdenticalElements {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(keepDistance(1)));
    }
    
    public static int[] keepDistance(int k) {
        // Write your solution here.
        int[] pad = new int[2 * k];
        return helper(pad, k) ? pad : null;
    }
    
    private static boolean helper(int[] pad, int num) {
        if (num == 0) {
            return true;
        }
        
        int left = 0;
        int right = left + num + 1;
        while (right < pad.length) {
            if (pad[left] == 0 && pad[right] == 0) {
                pad[left] = num;
                pad[right] = num;
                
                if (helper(pad, num - 1) == true) { // 找到了一个解
                    return true;
                }
                
                pad[left] = 0;
                pad[right] = 0;
            }
            left++;
            right = left + num + 1;
        }
        return false; // 当前层根本找不到，就返回false，让换一个上层元素的位置。
    }
}

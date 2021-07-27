package com.study.algorithms.class22_DP_4.重听.最长增序列;

import java.util.*;

public class Subsequence_GetSubsequence_S1 {
    // 现在希望不只是统计最长的长度，还希望知道路径
    public int[] longest(int[] array) {
        // 最基本的算法：look back to previous i-1 elements to find out the one to add after。 所以是O(n) * O(n) * O(1) = O(n^2)
        if (array == null || array.length == 0) {
            return new int[0];
        }
    
        int[] DP = new int[array.length];
        int[] Prev = new int[array.length];
        int globalMaxIndex = 0; // 要从0开始，不能从1开始。 不确定是否有2个
        DP[0] = 1;
        Prev[0] = 0; // Assume the prev of first element is itself.
    
        for (int i = 1; i < array.length; i++) {
            DP[i] = 1;
            Prev[i] = i;
            for (int j = 0; j < i; j++) { // 一定只能在前面找，才符合逻辑
                if (array[j] < array[i] && DP[j] + 1 > DP[i]) {
                    DP[i] = DP[j] + 1;
                    Prev[i] = j;
                    if (DP[globalMaxIndex] < DP[i]) {
                        globalMaxIndex = i;
                    }
                }
            }
        }
        
        // 从globalMaxIndex往回撸一遍，就知道顺序了
        List<Integer> result = new ArrayList<>();
        int curIndex = globalMaxIndex;
        int prevIndex = Prev[globalMaxIndex];
        // while (curIndex != prevIndex) {  // 这个循环的终止条件，会导致 == 的情况被漏掉。 要记得补上 【2】   （切勿把 == 的情况放到while里，会死循环）
        while (curIndex != prevIndex) {
            result.add(array[curIndex]);
            curIndex = prevIndex;
            prevIndex = Prev[curIndex];
        }
        result.add(array[curIndex]);
        
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(res.length - 1 - i); // 注意，result里的顺序是从后向前的，这里要反过来一下。   【1】
        }
        return res;
    }
}

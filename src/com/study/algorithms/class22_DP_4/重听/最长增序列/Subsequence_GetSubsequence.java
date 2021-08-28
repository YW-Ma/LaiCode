package com.study.algorithms.class22_DP_4.重听.最长增序列;

import java.util.*;

public class Subsequence_GetSubsequence {

    // review: globalMax 可以被 globalMaxIndex代替，每次需要用globalMax的时候去访问length即可。 所以只需要一个变量，而不需要两个。
    public int[] longest(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }

        int[] length = new int[array.length];
        int[] prev = new int[array.length];
        int globalMaxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            int curMax = 0;
            prev[i] = i; // review：这样初始化才行。
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    if (curMax <= length[j]) {
                        curMax = length[j];
                        prev[i] = j;
                    }
                }

                length[i] = curMax + 1;
                if (length[globalMaxIndex] <= length[i]) { // 这里能决定，相等时是取前面的还是后面的。这里选择是取后面，下面的方法是选择取后面。
                    globalMaxIndex = i;
                }
            }
        }
        Queue<Integer> container = new ArrayDeque<>();
        int curIndex = globalMaxIndex;
        int prevIndex = prev[curIndex];
        while (curIndex != prevIndex) { // 条件是prev和自己不相同。
            container.offer(array[curIndex]);
            curIndex = prevIndex;
            prevIndex = prev[curIndex];
        }
        // 出来的时候curIndex == 0
        container.add(array[curIndex]);

        // 存储到int[]里面
        int[] res = new int[container.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = container.poll();
        }
        return res;
    }
}

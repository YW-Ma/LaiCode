package com.study.algorithms.class19_cross_training_2.SUM.TwoSumPairs;

import java.util.*;

public class TwoSumAllPairsIII {
    // 在II的基础上，还要求返回值唯一，即不能出现 [1,1] [1,1] 这种结果 在 [1,1,1,1,1] 2 的输入下。
    public List<List<Integer>> allPairs(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> valueToCount = new HashMap<>();
        
        for (int num : array) {
            int count = valueToCount.getOrDefault(num, 0);
            if (num * 2 == target && count == 1) {
                // case 1
                res.add(Arrays.asList(num, num));
            } else if (valueToCount.containsKey(target - num) && count == 0) { // 保证有对象，且是第一次遇到array[i]
                // case 2
                res.add(Arrays.asList(target - num, num));
            }
            valueToCount.put(num, count + 1); // 记录遇到了多少次
        }
        
        return res;
    }
}

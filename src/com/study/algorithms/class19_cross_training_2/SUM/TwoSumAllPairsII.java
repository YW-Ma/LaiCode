package com.study.algorithms.class19_cross_training_2.SUM;

import java.util.*;

// 基本是和找所有可能性一样
// 区别在于一个元素只能用一次，也就是说如果是 [2,2,4] target=6, 那么只有一个结果，即[2,4]。而不是两个[2,4]
// 另外如果target = 4， [2,2,2] ，那么也应该只有一个 [2,2]

// 之前用了一个HashMap，记录一个value对应的index是哪些。
// 现在如果还用这个，那就代表"当前元素"被使用了多次，而且未来还打算继续使用。 这是不行的。

public class TwoSumAllPairsII {
    public List<List<Integer>> allPairs(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> valueToCount = new HashMap<>();
        for (int num : array) {
            Integer count = valueToCount.get(num);
            if (num * 2 == target && count != null && count == 1) {  // 注意这一行的写法，注意他必须在下方分支的前面。
            
            }
        }
        return res;
    }
}

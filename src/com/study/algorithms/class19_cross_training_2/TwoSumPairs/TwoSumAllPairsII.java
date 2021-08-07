package com.study.algorithms.class19_cross_training_2.TwoSumPairs;

import java.util.*;

// 基本是和找所有可能性一样
// 区别在于一个元素只能用一次，也就是说如果是 [2,2,4] target=6, 那么只有一个结果，即[2,4]。而不是两个[2,4]
// 另外如果target = 4， [2,2,2] ，那么也应该只有一个 [2,2]

// 之前用了一个HashMap，记录一个value对应的index是哪些。
// 现在如果还用这个，那就代表"当前元素"被使用了多次，而且未来还打算继续使用。 这是不行的。
// 所以现在应该用一个HashMap，记录一个value目前还有多少次可以被使用，如果是没有这个key，或者value是0，就代表不能用。

public class TwoSumAllPairsII {
    public List<List<Integer>> allPairs(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> valueToCount = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            Integer count = valueToCount.get(target - array[i]);
            if (count == null || count == 0) {
                valueToCount.put(array[i], valueToCount.getOrDefault(array[i], 0) + 1);
                continue;
            }
            
            if (count >= 1) {
                valueToCount.put(target - array[i], count - 1);
                res.add(Arrays.asList(target - array[i], array[i]));
            }
        }
        return res;
    }
}

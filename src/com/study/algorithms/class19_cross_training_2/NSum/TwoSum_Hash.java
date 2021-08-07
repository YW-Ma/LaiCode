package com.study.algorithms.class19_cross_training_2.NSum;

import java.util.*;

public class TwoSum_Hash {
    // Unsorted 2:
    // use hash set to record the previous traversed values.
    // TC: O(n)
    // SC: O(n)

    public boolean existSum(int[] array, int target) {
        // 原来：因为可能有重复，所以我用HashMap，这样如果是两个 target/2，那么也是可以获得解的

        // 订正：其实不需要Map，Set就行了。 原因是如果按顺序撸一遍，两个target/2会先后进入。 先进入的会被后进入的查出来。

        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (set.contains(target - num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

}

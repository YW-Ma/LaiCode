package com.study.algorithms.class20_cross_training_3;

import java.util.*;

public class CommonNumbersSetII {
    // 方法1：建造两个set，然后求交集， 开销O(n+m)。随后排序开销O(n+m log n+m)
    public List<Integer> common (int[] A, int[] B) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : A) { // O(n)
            set1.add(n);
        }
        
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : B) { // O(m)
            set2.add(n);
        }
        
        if (set1.size() < set2.size()) {
            return set_intersection(set1, set2);
        } else {
            return set_intersection(set2, set1);
        }
    }
    
    private List<Integer> set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        List<Integer> res = new ArrayList<>();
        int idx = 0;
        for (Integer n : set1) {
            if (set2.contains(n)) {
                res.add(n);
            }
        }
        
        res.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        return res;
    }
}

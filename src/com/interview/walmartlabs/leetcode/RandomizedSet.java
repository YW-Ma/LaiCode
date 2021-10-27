package com.interview.walmartlabs.leetcode;

import java.util.*;

public class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        // swap (in list and map)
        int idx = map.get(val);
        int tailElement = list.get(list.size() - 1);
        list.set(idx, tailElement);
        map.put(tailElement, idx);

        // delete (in list and map)
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}

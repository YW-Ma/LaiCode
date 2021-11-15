package com.interview.bloomberg;

import java.util.TreeMap;

public class LearnTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 1; i < 10; i++) {
            map.put(i, i + 100);
        }
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());
        System.out.println(map.pollFirstEntry().getValue());
    }
}

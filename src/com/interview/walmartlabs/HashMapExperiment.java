package com.interview.walmartlabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapExperiment {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                cur.add(j);
            }
            map.put(i, cur);
        }

        System.out.println("map: " + map);

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            list.remove(list.size() - 1);
        }

        System.out.println("map: " + map);
    }
}

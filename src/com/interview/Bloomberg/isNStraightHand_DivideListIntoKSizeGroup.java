package com.interview.bloomberg;

import java.util.*;

public class isNStraightHand_DivideListIntoKSizeGroup {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length == 0 || hand.length % groupSize != 0) {
            return false;
        }
        // num --> frequence
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int n : hand) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        // from smallest num, generate group
        while (!map.isEmpty()) {
            // get the starting element
            int start = map.firstKey();
            map.put(start, map.get(start) - 1);
            if (map.get(start) == 0) {
                map.remove(start);
            }
            // get the rest part of this group
            for (int i = 1; i < groupSize; i++) {
                int cur = start + i;
                if (!map.containsKey(cur)) {
                    return false;
                }
                map.put(cur, map.get(cur) - 1);
                if (map.get(cur) == 0) {
                    map.remove(cur);
                }
            }
        }
        return true;
    }
}
